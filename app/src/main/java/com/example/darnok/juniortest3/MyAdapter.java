package com.example.darnok.juniortest3;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//Адаптер для List View

class MyAdapter extends BaseAdapter {

    private ArrayList<Item> data = new ArrayList<>();
    private final Context context;

    MyAdapter(Context context, ArrayList<Item> arr) {
        if (arr != null) {
            data = arr;
        }
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int num) {
        return data.get(num);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int i, View someView, ViewGroup arg2) {
        //Получение объекта inflater из контекста
        LayoutInflater inflater = LayoutInflater.from(context);
        //Если someView из ListView равен null то происходит его загрузка с помошью inflater
        if (someView == null) {
            someView = inflater.inflate(R.layout.item_for_listview, arg2, false);
        }

        TextView header = someView.findViewById(R.id.text_view_name);
        ProgressButton subHeader = someView.findViewById(R.id.progress_button);

        //Обявление текста в Text View и Progress Button
        header.setText(String.valueOf(data.get(i).col1));
        subHeader.setText(String.valueOf(data.get(i).col2));
        subHeader.setRatio(data.get(i).col2);
        return someView;
    }
}