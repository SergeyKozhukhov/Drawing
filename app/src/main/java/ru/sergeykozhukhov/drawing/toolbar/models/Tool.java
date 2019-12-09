package ru.sergeykozhukhov.drawing.toolbar.models;

/*
* Элемент ToolBar
* */
public class Tool {

    /*
    * idTool - id элемента
    * idImage - id иконки
    * */

    private int idTool;
    private int idImage;

    public Tool(int idTool, int idImage) {
        this.idTool = idTool;
        this.idImage = idImage;
    }

    public int getIdTool() {
        return idTool;
    }

    public void setIdTool(int idTool) {
        this.idTool = idTool;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }





}
