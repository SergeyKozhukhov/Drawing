package ru.sergeykozhukhov.drawing.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;

/*
* Основной класс "Фигура", от которого наследуются остальные фигуры
* */

public abstract class Shape {

    /*
    * color - цвет фигуры
    * style - стиль отображения фигуры (с границей, закрашенная, с границей и закрашенная)
    * width - ширина фигуры
    * */
    int color;
    Paint.Style style;
    float width;

    /*
    * Установка начальной точки фигуры
    * */
    public abstract void setOrigin(float x, float y);

    /*
    * Установка текущей точки фигуры
    * */
    public abstract void setCurrent(float x, float y);

    /*
    * Отображение фигуры на холсте
    * */
    public void show(Canvas canvas, Paint paint){
        paint.setColor(color);
        paint.setStrokeWidth(width);
        paint.setStyle(style);
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setWidth(float width)
    {
        this.width = width;
    }

    public void setStyle(Paint.Style style) {
        this.style = style;
    }

    public Paint.Style getStyle() {
        return style;
    }
}
