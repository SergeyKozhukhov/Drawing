package ru.sergeykozhukhov.drawing.shapes;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

/*
 * Класс фигуры "Ломанная фигура"
 * */
public class PolyShape extends PathLine {

    /*
    * points - набор точек, по которым строится ломанная фигура
    * */
    protected List<PointF> points;
    public PolyShape(float x, float y) {
        super();
        points = new ArrayList<>(16);
        points.add(new PointF(x, y));
    }

    @Override
    public void setPathLine(Path pathLine) {
        super.setPathLine(pathLine);
    }

    @Override
    public void setOrigin(float x, float y) {
        if (points.isEmpty())
            points.add(new PointF(x, y));
        else {
            points.get(0).x = x;
            points.get(0).y = y;
        }
    }

    public void setOrigin(int pointerId, float x, float y) {

        while (pointerId >= points.size()) {
            points.add(new PointF());
        }
        points.get(pointerId).x = x;
        points.get(pointerId).y = y;
        configPathLine();
    }


    @Override
    public void setCurrent(float x, float y) {
        points.add(new PointF(x, y));
        configPathLine();
    }

    public void setCurrent(int pointerId, float x, float y) {
        points.get(pointerId).x = x;
        points.get(pointerId).y = y;
        configPathLine();
    }

    /*
    * Настройка "Пути" по обозначенному набору точек
    * */
    private void configPathLine() {
        if (points.size() > 2) {
            pathLine.reset();
            for (PointF point : points) {
                if (pathLine.isEmpty()) {
                    pathLine.moveTo(point.x, point.y);
                } else {
                    pathLine.lineTo(point.x, point.y);
                }
            }
            pathLine.close();
        }
    }

    @Override
    public void show(Canvas canvas, Paint paint) {
        switch (points.size()) {
            case 1:
                canvas.drawPoint(points.get(0).x, points.get(0).y, paint);
                break;
            case 2:
                canvas.drawLine(points.get(0).x, points.get(0).y, points.get(1).x, points.get(1).y, paint);
                break;
            default:
                super.show(canvas, paint);
        }
    }

    /*
    * Приведение ломанной фигуры к точке
    * */
    public Point toPoint() {
        Point point = new Point(points.get(0).x, points.get(0).y);
        point.setColor(color);
        point.setStyle(style);
        point.setWidth(width);
        return point;
    }


    /*
     * Приведение ломанной фигуры к линии
     * */
    public Line toLine() {
        Line line = new Line(points.get(0).x, points.get(0).y);
        line.setCurrent(points.get(1).x, points.get(1).y);
        line.setColor(color);
        line.setStyle(style);
        line.setWidth(width);
        return line;
    }


    /*
     * Приведение ломанной фигуры к кривой линии
     * */
    private PathLine toPathLine() {
        PathLine pathLine = new PathLine();
        pathLine.setPathLine(this.pathLine);
        pathLine.setColor(color);
        pathLine.setStyle(style);
        pathLine.setWidth(width);
        return pathLine;
    }

    /*
     * Приведение ломанной фигуры к соответствующей более простой фигуре
     * */
    public Shape toSimpleShape() {
        int size = points.size();
        switch (size) {
            case 1:
                return toPoint();
            case 2:
                return toLine();
            default:
                return toPathLine();
        }
    }
}
