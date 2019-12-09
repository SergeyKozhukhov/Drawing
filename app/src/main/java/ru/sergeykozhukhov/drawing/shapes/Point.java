package ru.sergeykozhukhov.drawing.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

/*
* Класс фигуры "Точка"
* */
public class Point extends Shape {

    /*
    * Координаты по x и y
    * */

    private float x;
    private float y;

    public Point(float x, float y){
        this.x = x;
        this.y = y;

    }

    @Override
    public void setOrigin(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setCurrent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void show(Canvas canvas, Paint paint) {
        super.show(canvas, paint);
        canvas.drawPoint(x, y, paint);
    }

}
