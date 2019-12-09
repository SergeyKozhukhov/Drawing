# Drawing

### [Drawing]

Программа, предоставляющая простейшие инструменты для рисования.

### [ФУНКЦИИ]

Возможности:

1. Выбор фигур из имеющегося списка (точка, линия, кривая линия, прямоугольник, овал, многоугольник);
2. выбор параметров фигуры для отображения (цвет, наличие контура и/или сплошной заливки, ширина контура);
3. рисования многоугольников в режиме 'multi-touch";
4. очиста экрана.

### [ТЕХНИЧЕСКАЯ ЧАСТЬ]

Использование:

- наследование фигур от абстрактного базового класса "Shape";
- обработка касания экрана одним и несколькими пальцами;
- рисование с применением Canvas;
- элемент выбора цвета "Pikolo" (https://github.com/Madrapps/Pikolo).

### [ИНТЕРФЕЙС]

<p><img src="app/src/main/res/drawable/tool_point.png" width="35" height="35" align="middle" /> 
- использование фигуры "Точка"; </p>
<p><img src="app/src/main/res/drawable/tool_line.png" width="35" height="35" align="middle" /> 
- использование фигуры "Линия"; </p>
<p><img src="app/src/main/res/drawable/tool_pathline.png" width="35" height="35" align="middle" /> 
- использование фигуры "Кривая линия"; </p>
<p><img src="app/src/main/res/drawable/tool_rectangle.png" width="35" height="35" align="middle" /> 
- использование фигуры "Прямоугольник"; </p>
<p><img src="app/src/main/res/drawable/tool_oval.png" width="35" height="35" align="middle" /> 
- использование фигуры "Овал"; </p>
<p><img src="app/src/main/res/drawable/tool_multitouch.png" width="35" height="35" align="middle" /> 
- включение/отключение режима "multi-touch" для использования фигуры "Многоугольник" ; </p>
<p><img src="app/src/main/res/drawable/tool_colors.png" width="35" height="35" align="middle" /> 
- выбор цвета; </p>
<p><img src="app/src/main/res/drawable/tool_width.png" width="35" height="35" align="middle" /> 
- выбор ширины контура; </p>
<p><img src="app/src/main/res/drawable/tool_style.png" width="35" height="35" align="middle" /> 
- выбор стиля отображения фигуры (заливка цветом и/или отрисовка контура); </p>
<p><img src="app/src/main/res/drawable/tool_clear.png" width="35" height="35" align="middle" /> 
- очистка экрана. </p>

### [ПРИМЕР РАБОТЫ ПРОГРАММЫ]

1. Рисование фигур в режиме обработки касаний экрана одним пальцем.

![Image alt](/scr/01_01.jpg)

2. Рисование фигур в режиме обработки касаний экрана несколькими пальцами.

![Image alt](/scr/01_02.jpg)

3. Экран выбора цвета.

![Image alt](/scr/01_03.jpg)
