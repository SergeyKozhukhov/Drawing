package ru.sergeykozhukhov.drawing.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/*
 * Класс фигуры "Кривая линия"
 * */
public class PathLine extends Shape {

    /*
     * Путь "Кривой линии"
     * */
    Path pathLine;

    public PathLine() {
        pathLine = new Path();
    }

    public PathLine(float x, float y) {
        pathLine = new Path();
        pathLine.moveTo(x, y);
    }

    public void setPathLine(Path pathLine){
        this.pathLine.set(pathLine);
    }

    @Override
    public void setOrigin(float x, float y) {
        pathLine.moveTo(x, y);
    }

    @Override
    public void setCurrent(float x, float y) {
        pathLine.lineTo(x, y);
    }

    @Override
    public void show(Canvas canvas, Paint paint) {
        super.show(canvas, paint);
        canvas.drawPath(pathLine, paint);
    }

}
