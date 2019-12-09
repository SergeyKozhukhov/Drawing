package ru.sergeykozhukhov.drawing.draw_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ru.sergeykozhukhov.drawing.shapes.Line;
import ru.sergeykozhukhov.drawing.shapes.Oval;
import ru.sergeykozhukhov.drawing.shapes.PathLine;
import ru.sergeykozhukhov.drawing.shapes.Point;
import ru.sergeykozhukhov.drawing.shapes.PolyShape;
import ru.sergeykozhukhov.drawing.shapes.Shape;
import ru.sergeykozhukhov.drawing.shapes.Rectangle;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_POINTER_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;
import static android.view.MotionEvent.ACTION_CANCEL;

public class DrawShapes extends View {

    /*
     * Список фигур
     * */
    public static final int POINT = 0;
    public static final int LINE = 1;
    public static final int PATHLINE = 2;
    public static final int RECTANGLE = 3;
    public static final int OVAL = 4;

    /*
     * modeMultiTouch - режим "multi-touch" (true - включен / false - отключен)
     * */
    boolean modeMultiTouch = false;

    /*
     * Параметры отображения фигур
     * paintCurrent - текущие параметры
     * paintHistory - настраиваемые параметры для набора фигур/истории
     * */
    private Paint paintCurrent;
    private Paint paintHistory;

    /*
    * Начальные параметры отображения фигур
    * */
    private static final int COLOR = Color.BLACK;
    private static final Paint.Style STYLE = Paint.Style.STROKE;
    private static final float STROKE_WIDTH = 10f;

    /*
     * Текушая фигура в обычном режиме (касание одним пальцем)
     * */
    private int shape_current = POINT;

    /*
     * Список всех фигур/история рисования
     * */
    private List<Shape> shapes = new ArrayList<>();

    /*
     * "Фигура" для обычного режима
     * */
    private Shape shape;

    /*
     * "Многоугольник" для "multi-touch" режима
     * */
    private PolyShape polyShape;

    public DrawShapes(Context context) {
        this(context, null);
    }

    public DrawShapes(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    /*
     * Настройка текущей "Фигуры" в обычном режиме (касание одним пальцем) для рисования
     * */
    public void setShapeCurrent(int shape_current) {
        this.shape_current = shape_current;
    }

    /*
     * Включение/отключение режима "multi-touch"
     * */
    public void setModeMultiTouch(boolean mode) {
        modeMultiTouch = mode;
    }

    public boolean getModeMultiTouch() {
        return modeMultiTouch;
    }

    /*
     * Настройка цвета для текушей и последующих фигур
     * */
    public void setColor(int color) {
        paintCurrent.setColor(color);
    }

    public int getColor() {
        return paintCurrent.getColor();
    }

    /*
     * Настройка стиля (формата заполения) для текушей и последующих фигур
     * */
    public void setStyle(Paint.Style style) {
        paintCurrent.setStyle(style);
    }

    public Paint.Style getStyle() {
        return paintCurrent.getStyle();
    }

    /*
     * Настройка ширины контура для текушей и последующих фигур
     * */
    public void setWidth(float width) {
        paintCurrent.setStrokeWidth(width);
    }

    /*
     * Очистка всего набора фигур, удаление истории
     * */
    public void clear() {
        shapes.clear();
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        // отображаем набор фигур/историю рисования
        if (!shapes.isEmpty())
            showStory(canvas, paintHistory);
        // отображаем новую рисуемую фигуру
        if (shape != null) {
            shape.show(canvas, paintCurrent);
        }
        // отображаем новый рисуемый "Многоугольник" в режиме "multi-touch"
        if (polyShape != null)
            polyShape.show(canvas, paintCurrent);
    }

    /*
     * Отображение всех ранее нарисованных фигур
     * */
    private void showStory(Canvas canvas, Paint paint) {
        for (Shape shape : shapes) {
            shape.show(canvas, paint);
        }
    }

    /*
     * Создание новой "Фигуры" по заданному значению
     * */
    private void createNewShape(int shape_current, float originX, float originY) {
        switch (shape_current) {

            case POINT:
                shape = new Point(originX, originY);
                break;
            case LINE:
                shape = new Line(originX, originY);
                break;
            case PATHLINE:
                shape = new PathLine(originX, originY);
                break;
            case RECTANGLE:
                shape = new Rectangle(originX, originY);
                break;
            case OVAL:
                shape = new Oval(originX, originY);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + shape_current);
        }
        shape.setColor(paintCurrent.getColor());
        shape.setWidth(paintCurrent.getStrokeWidth());
        shape.setStyle(paintCurrent.getStyle());
    }

    /*
     * Создание нового "Многоугольника"
     * */
    private void createNewPolyShape(float originX, float originY) {
        polyShape = new PolyShape(originX, originY);
        polyShape.setColor(paintCurrent.getColor());
        polyShape.setStyle(paintCurrent.getStyle());
        polyShape.setWidth(paintCurrent.getStrokeWidth());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (modeMultiTouch == true) {
            return setPolyShapeMultiTouch(event);
        } else {
            return setShape(event);
        }
    }

    /*
     * Получение новой "Фигуры" в обычном режиме
     * */
    private boolean setShape(MotionEvent event) {
        // получаем координаты касания
        float eventX = event.getX();
        float eventY = event.getY();
        switch (event.getAction()) {
            case ACTION_DOWN:
                // создам новую "Фигуру" и устанавливаем начальную точку
                createNewShape(shape_current, eventX, eventY);
                break;
            case ACTION_MOVE:
                // фиксируем текущую точку
                shape.setCurrent(eventX, eventY);
                break;
            case ACTION_UP:
                // добавляем фигуру в набор фигур/историю
                shapes.add(shape);
                // создаем новую фигуру
                shape = null;
                break;
            case ACTION_CANCEL:
                shape = null;
                break;
        }
        invalidate();
        return true;
    }

    /*
     * Получение нового "Многоугольника" в режиме "multi-touch"
     * */
    private boolean setPolyShapeMultiTouch(MotionEvent event) {

        switch (event.getActionMasked()) {
            case ACTION_DOWN:
                // создам новый "Многоугольник" и устанавливаем начальную точку для первого пальца
                createNewPolyShape(event.getX(), event.getY());
                break;
            case ACTION_POINTER_DOWN:
                int pointerId = event.getPointerId(event.getActionIndex());
                // устанавливаем начальные точки для остальных пальцев
                polyShape.setOrigin(pointerId, event.getX(event.getActionIndex()), event.getY(event.getActionIndex()));
                break;
            case ACTION_MOVE:
                // изменяем координаты точек по движению пальцев
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int pId = event.getPointerId(i);
                    polyShape.setCurrent(pId, event.getX(i), event.getY(i));
                }
                break;
            case ACTION_UP:
                // добавялем более простую версию "Многоугольник" в набор фигур/историю
                shapes.add(polyShape.toSimpleShape());
                polyShape = null;
                break;
            case ACTION_CANCEL:
                polyShape = null;
                break;
        }
        invalidate();
        return true;
    }

    /*
     * Настройка начальных параметров отображения фигур
     * */
    private void initPaint() {
        paintCurrent = new Paint();
        paintCurrent.setAntiAlias(true);
        paintCurrent.setStrokeWidth(STROKE_WIDTH);
        paintCurrent.setColor(COLOR);
        paintCurrent.setStyle(STYLE);
        paintHistory = new Paint(paintCurrent);
    }

}
