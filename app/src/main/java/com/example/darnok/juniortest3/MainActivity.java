package com.example.darnok.juniortest3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.darnok.juniortest3.MESSAGE";
    int n = 100;

    ListView listView;

    //Массив для List View.
    ArrayList<Item> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Открытие базы данных.
        openData();

        //Получение сообщения из MainActivity
        Intent intent = getIntent();
        int rowNumber = intent.getIntExtra("row_number", 0);
        float percent = intent.getFloatExtra("percent", 0);
        boolean check = intent.getBooleanExtra("check", false);
        //Если было проведена замена, то она сохраняется в базе данных.
        if(check == true){
            data.set(rowNumber, new Item(String.valueOf(rowNumber), String.valueOf(percent)));
            saveItem(rowNumber);
        }

        listView = (ListView)findViewById(R.id.lv);
        listView.setItemsCanFocus(false);
        listView.setAdapter(new MyAdapter(this,data));

        //Обработчик нажатия на элемент List View
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ChosenItem.class);

                //Передача сообщения о нажатой позиции в ChosenItem
                int message = position;
                intent.putExtra(EXTRA_MESSAGE, message);

                //Открытие экрана ChosenItem
                startActivity(intent);
            }
        });
    }

    //Сохранение изменённой строки
    public void saveItem(int rowNumber){
        SQLiteDatabase myDB = openOrCreateDatabase("ListView.db", MODE_PRIVATE, null);
        myDB = openOrCreateDatabase("ListView.db", MODE_PRIVATE, null);
        ContentValues updateRowI = new ContentValues();
        updateRowI.put("rowNumber", data.get(rowNumber).col1);
        updateRowI.put("percent", data.get(rowNumber).col2);

        //Замена строки String[]{String.valueOf(rowNumber)} в таблице "table2" в столбце "rowNumber".
        myDB.update("table2", updateRowI, "rowNumber = ?", new String[]{String.valueOf(rowNumber)});
        myDB.close();
    }

    //Метод для открытия базы данных SQLite
    public void openData(){
        //Открытие и создание базы даннх, если её нет
        SQLiteDatabase myDB = openOrCreateDatabase("ListView.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS table2 (rowNumber INT(100), percent FLOAT(100))");
        Cursor myCursor = myDB.rawQuery("select rowNumber, percent from table2", null);

        //Проверка, пустая ли таблица, и если да, заполнение числами от 0 до 99
        if(myCursor.getCount() == 0){
            for (int i = 0; i < n; i++) {
                ContentValues rowI = new ContentValues();
                rowI.put("rowNumber", i);
                rowI.put("percent", 0.0f);
                myDB.insert("table2", null, rowI);
            }
        }

        //Распаковка таблицы в list view
        myCursor = myDB.rawQuery("select rowNumber, percent from table2", null);
        while (myCursor.moveToNext()) {
            int i = myCursor.getInt(0);
            float f = myCursor.getFloat(1);
            data.add(new Item(String.valueOf(i), String.valueOf(f)));
        }
        myDB.close();
    }

    //Создание всплывающего меню сверху справа
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Обработчик нажатия кнопки назад и "settings"
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
            this.finish();

        if(id == R.id.action_settings){
            Intent intent = new Intent(MainActivity.this, ChosenSettings.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
