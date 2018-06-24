package com.example.darnok.juniortest3;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Класс для выбора процентов для отдельной строки List View

public class ChosenSettings extends MainActivity {

    Button okButton;
    EditText editRow, editPer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        //Смена заголовка.
        setTitle("Settings");

        //Добавление кнопки "назад" в левом верхнем углу
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editRow = (EditText)findViewById(R.id.editRow);
        editPer = (EditText)findViewById(R.id.editPer);

        //Обработчик нажатия на кнопку "ОК"
        okButton = (Button)findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int row = 0;
                float per = 0;

                //Проверка на числа.
                if((TextUtils.isDigitsOnly(editRow.getText().toString())) && (TextUtils.isDigitsOnly(editRow.getText().toString()))){
                    row = Integer.parseInt(editRow.getText().toString());
                    per = Float.parseFloat(editPer.getText().toString());

                    //Обработка интервала.
                    if ((row >= 100) || (row < 0) || (per > 1) || (per < 0)) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Wrong interval, row is int [0 ; 99], percent is float [0 ; 1].", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        //Условие замены строки в list view, это check = true.
                        boolean check = false;

                        check = true;
                        Intent intent = new Intent(ChosenSettings.this, MainActivity.class);
                        intent.putExtra("row_number", row);
                        intent.putExtra("percent", per);
                        intent.putExtra("check", check);
                        startActivity(intent);
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Wrong symbol, try again.", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }
}
