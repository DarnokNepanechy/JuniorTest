package com.example.darnok.juniortest3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

//Класс для отдельно открытого Item элемента

public class ChosenItem extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_for_listview);

        //Добавление кнопки "назад" в левом верхнем углу
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Получение сообщения из MainActivity
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", 0);

        //Смена заголовка.
        setTitle("Row N " + position);

        ProgressButton progressButton = findViewById(R.id.progress_button);
        TextView textViewName = findViewById(R.id.text_view_name);

        //Выставленме нужного номера Text View
        textViewName.setText(String.valueOf(position));

        //Добавление краски в кнопку с индикатором прогресса
        progressButton.setRatio(data.get(position).col2);
        progressButton.setText(String.valueOf(data.get(position).col2));
    }
}
