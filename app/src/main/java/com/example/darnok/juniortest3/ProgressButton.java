package com.example.darnok.juniortest3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

//Класс кнопки с индикатором прогресса

public class ProgressButton extends AppCompatButton {
    private float mRatio;

    public ProgressButton(Context context) {
        super(context);
    }

    public ProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //Выбор процента заливки [0 ; 1]
    public void setRatio (float ratio){
        mRatio = ratio;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Drawable fill = getResources().getDrawable(R.drawable.abc_btn_default_mtrl_shape);
        fill.setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        fill.setAlpha(100);
        fill.setBounds(-8, -12, (int) (getWidth()*mRatio)+8,  getHeight()+12);
        fill.draw(canvas);
        super.onDraw(canvas);
    }
}