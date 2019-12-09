package ru.sergeykozhukhov.drawing.toolbar.adapters;

import ru.sergeykozhukhov.drawing.toolbar.models.Tool;

/*
* Интерфейс для обработки нажатий на элементы ToolBar
* */
public interface OnToolBarClickListener {
    void itemClick(Tool tool);
}
