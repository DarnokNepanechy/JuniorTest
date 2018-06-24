package com.example.darnok.juniortest3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

//Класс для отдельно открытого Item элемента

public class ChosenItem extends MainActivity {

    TextView textViewName;
    ProgressButton progressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_for_listview);

        //Добавление кнопки "назад" в левом верхнем углу
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Получение сообщения из MainActivity
        Intent intent = getIntent();
        int message = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);

        //Смена заголовка.
        setTitle("Row N " + message);

        progressButton = (ProgressButton)findViewById(R.id.progress_button);
        textViewName = (TextView)findViewById(R.id.textViewName);

        //Выставленме нужного номера Text View
        textViewName.setText(String.valueOf(message));

        //Добавление краски в кнопку с индикатором прогресса
        progressButton.setRatio(Float.parseFloat(data.get(message).col2));
        progressButton.setText(String.valueOf(data.get(message).col2));
    }
}
