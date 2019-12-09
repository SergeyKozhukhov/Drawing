package ru.sergeykozhukhov.drawing.toolbar.providerdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.sergeykozhukhov.drawing.R;
import ru.sergeykozhukhov.drawing.toolbar.models.Tool;

/*
* Набор элементов ToolBar
* */
public class ProviderTools {

    /*
    * TOOL_POINT - рисование точкой
    * TOOL_LINE - рисование линией
    * TOOL_PATHLINE - рисование кривой линией
    * TOOL_OVAL - рисование овалом
    * TOOL_MULTITOUCH - включение/отключение режима "multi-touch"
    * TOOL_COLORS - выбор нового цвета
    * TOOL_WIDTH - выбор ширины контура фигуры
    * TOOL_STYLE - выбор стиля фигуры (заливка, контура)
    * TOOL_CLEAR - очиста экрана/истории
    * */

    public static final int TOOL_POINT = 0;
    public static final int TOOL_LINE = 1;
    public static final int TOOL_PATHLINE = 2;
    public static final int TOOL_RECTANGLE = 3;
    public static final int TOOL_OVAL = 4;
    public static final int TOOL_MULTITOUCH = 5;
    public static final int TOOL_COLORS = 6;
    public static final int TOOL_WIDTH = 7;
    public static final int TOOL_STYLE = 8;
    public static final int TOOL_CLEAR = 9;


    private List<Tool> mTools = Arrays.asList(
            new Tool(TOOL_POINT, R.drawable.tool_point),
            new Tool(TOOL_LINE, R.drawable.tool_line),
            new Tool(TOOL_PATHLINE, R.drawable.tool_pathline),
            new Tool(TOOL_RECTANGLE, R.drawable.tool_rectangle),
            new Tool(TOOL_OVAL, R.drawable.tool_oval),
            new Tool(TOOL_MULTITOUCH, R.drawable.tool_multitouch),
            new Tool(TOOL_COLORS, R.drawable.tool_colors),
            new Tool(TOOL_WIDTH, R.drawable.tool_width),
            new Tool(TOOL_STYLE, R.drawable.tool_style),
            new Tool(TOOL_CLEAR, R.drawable.tool_clear)
    );

    public List<Tool> getTools()
    {
        return new ArrayList<>(mTools);
    }

}
