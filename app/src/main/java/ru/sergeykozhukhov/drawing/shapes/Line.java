package ru.sergeykozhukhov.drawing.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/*
 * Класс фигуры "Линия"
 * */
public class Line extends Shape {

    /*
    * origin - начальная точка
    * ending - конечная точка
    * */
    PointF origin;
    PointF ending;



    public Line(float x, float y) {
        origin = new PointF(x, y);
        ending = new PointF(x, y);
    }

    @Override
    public void setOrigin(float x, float y) {
        origin.x = x;
        origin.y = y;
        ending.x = x;
        ending.y = y;
    }

    @Override
    public void setCurrent(float x, float y) {
        ending.x = x;
        ending.y = y;
    }

    @Override
    public void show(Canvas canvas, Paint paint) {
        super.show(canvas, paint);
        canvas.drawLine(origin.x, origin.y, ending.x, ending.y, paint);
    }
}
