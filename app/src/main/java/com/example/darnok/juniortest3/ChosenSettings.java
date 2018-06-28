package com.example.darnok.juniortest3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChosenSettings extends MainActivity {

    private  EditText editRow, editPer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        //Добавление кнопки "назад" в левом верхнем углу
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        editRow = findViewById(R.id.editRow);
        editPer = findViewById(R.id.editPer);

        //Обработчик нажатия на кнопку "ОК"
        Button okButton = findViewById(R.id.okButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editRow.getText().toString().equals("") || editPer.getText().toString().equals("")
                        ||  editRow.getText().toString().length() > 2 || editPer.getText().toString().length() > 10) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Input Error. Number probably too short or to long.", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    int rowNumber = Integer.parseInt(editRow.getText().toString());
                    float percent = Float.parseFloat(editPer.getText().toString());

                    if((rowNumber < n) && (rowNumber >= 0)
                            && (percent >= 0) && (percent <= 1)) {
                        data.set(rowNumber, new Item(rowNumber, percent));
                        saveItem(rowNumber);
                        Intent intent = new Intent(ChosenSettings.this, MainActivity.class);
                        //Очистка стека переходов назад.
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Input Error. Allowable values for 'Row' are int [0 ; " + (n - 1) + "]. For '%' are float [0 ; 1].", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });
    }
}
