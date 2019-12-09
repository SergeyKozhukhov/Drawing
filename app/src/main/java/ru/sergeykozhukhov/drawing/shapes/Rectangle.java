package ru.sergeykozhukhov.drawing.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;


/*
 * Класс фигуры "Прямоугольник"
 * */
public class Rectangle extends Shape {

    /*
     * Координаты прямоугольника задаются двумя точками (верхним левым углом и нижним правым)
     * */
    RectF rectF;

    public Rectangle(float x, float y) {
        rectF = new RectF(x, y, x , y);
    }

    @Override
    public void setOrigin(float x, float y) {
        rectF.left = x;
        rectF.top = y;
        rectF.right = x;
        rectF.bottom = y;
    }

    @Override
    public void setCurrent(float x, float y) {
        rectF.right = x;
        rectF.bottom = y;
    }

    @Override
    public void show(Canvas canvas, Paint paint) {
        super.show(canvas, paint);

        if ((rectF.left > rectF.right) && (rectF.top > rectF.bottom))
            canvas.drawRect(rectF.right, rectF.bottom, rectF.left, rectF.top, paint);
        else if ((rectF.left > rectF.right) && (rectF.top <= rectF.bottom))
            canvas.drawRect(rectF.right, rectF.top, rectF.left, rectF.bottom, paint);
        else if ((rectF.left <= rectF.right) && (rectF.top > rectF.bottom))
            canvas.drawRect(rectF.left, rectF.bottom, rectF.right, rectF.top, paint);
        else
            canvas.drawRect(rectF.left, rectF.top, rectF.right, rectF.bottom, paint);

    }

}
