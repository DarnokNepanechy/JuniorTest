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

    final ArrayList<Item> data = new ArrayList<>();
    final int n = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openData();

        ListView listView = findViewById(R.id.lv);
        listView.setItemsCanFocus(false);
        listView.setAdapter(new MyAdapter(this,data));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ChosenItem.class);

                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    void saveItem(int rowNumber){
        SQLiteDatabase myDB = openOrCreateDatabase("ListView.db", MODE_PRIVATE, null);
        ContentValues updateRow = new ContentValues();
        updateRow.put("rowNumber", data.get(rowNumber).col1);
        updateRow.put("percent", data.get(rowNumber).col2);

        //Замена строки String[]{String.valueOf(rowNumber)} в таблице "table2" в столбце "rowNumber".
        myDB.update("table2", updateRow, "rowNumber = ?", new String[]{String.valueOf(rowNumber)});
        myDB.close();
    }

    //Метод для открытия базы данных SQLite
    private void openData(){
        //Открытие и создание базы даннх, если её нет
        SQLiteDatabase myDB = openOrCreateDatabase("ListView.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS table2 (rowNumber INT(100), percent FLOAT(100))");
        Cursor myCursor = myDB.rawQuery("select rowNumber, percent from table2", null);

        //Проверка, пустая ли таблица, и если да, заполнение числами от 0 до 99
        if(myCursor.getCount() == 0){
            for (int i = 0; i < n; i++) {
                ContentValues newRow = new ContentValues();
                newRow.put("rowNumber", i);
                newRow.put("percent", 0.0f);
                myDB.insert("table2", null, newRow);
            }
        }

        //Распаковка таблицы в list view
        myCursor = myDB.rawQuery("select rowNumber, percent from table2", null);
        while (myCursor.moveToNext()) {
            int rowNumber = myCursor.getInt(0);
            float percent = myCursor.getFloat(1);
            data.add(new Item(rowNumber, percent));
        }
        myCursor.close();
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
