package ru.sergeykozhukhov.drawing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import ru.sergeykozhukhov.drawing.draw_view.DrawShapes;
import ru.sergeykozhukhov.drawing.toolbar.adapters.AdapterTools;
import ru.sergeykozhukhov.drawing.toolbar.adapters.OnToolBarClickListener;
import ru.sergeykozhukhov.drawing.toolbar.models.Tool;
import ru.sergeykozhukhov.drawing.toolbar.providerdata.ProviderTools;

public class MainActivity extends AppCompatActivity {

    /*
    * CODE_COLOR_ACTIVITY - код запуска окна выбора цвета
    * */
    private static final  int CODE_COLOR_ACTIVITY = 0;

    private ProviderTools providerTools;
    private AdapterTools adapterTools;
    private OnToolBarClickListener onToolBarClickListener;

    private DrawShapes ds_drawing;

    private RecyclerView rv_toolbar;
    private SeekBar sb_width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        providerTools = new ProviderTools();

        ds_drawing = findViewById(R.id.draw_shapes);
        rv_toolbar = findViewById(R.id.rv_toolbar);
        sb_width = findViewById(R.id.seek_bar_width);

        initWidthListener();
        initToolBarListener();
        initToolbar();
    }

    /*
    * Инициализация ToolBar
    * */
    private void initToolbar() {
        adapterTools = new AdapterTools(providerTools.getTools(), onToolBarClickListener);
        LinearLayoutManager layoutManagerHorizontal = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rv_toolbar.setLayoutManager(layoutManagerHorizontal);
        rv_toolbar.setAdapter(adapterTools);
    }

    /*
    * Обработка нажатия на элемент ToolBar
    * */
    private void initToolBarListener() {

        onToolBarClickListener = new OnToolBarClickListener() {
            @Override
            public void itemClick(Tool tool) {

                int idTool = tool.getIdTool();

                switch (idTool) {

                    /*
                     * Включение/отключения режима "multi-touch"
                     * */
                    case ProviderTools.TOOL_MULTITOUCH:
                        if (ds_drawing.getModeMultiTouch() == true) {
                            ds_drawing.setModeMultiTouch(false);
                            Toast.makeText(getApplicationContext(), R.string.help_text_multitouch_off, Toast.LENGTH_SHORT).show();
                        } else {
                            ds_drawing.setModeMultiTouch(true);
                            Toast.makeText(getApplicationContext(), R.string.help_text_multitouch_on, Toast.LENGTH_SHORT).show();
                        }
                        break;

                    /*
                     * Переход в окно выбора нового цвета
                     * */
                    case ProviderTools.TOOL_COLORS:
                        Intent i = new Intent(getApplicationContext(), ColorActivity.class);
                        startActivityForResult(i, CODE_COLOR_ACTIVITY);
                        break;

                    /*
                     * Включение/отключение seek bar для настройки ширины гриницы (контура)"
                     * */
                    case ProviderTools.TOOL_WIDTH:
                        if (sb_width.getVisibility() == View.GONE) {
                            sb_width.setVisibility(View.VISIBLE);
                        } else {
                            sb_width.setVisibility(View.GONE);
                        }
                        break;

                    /*
                     * Настройка стиля (формата заполения) для текушей и последующих фигур
                     * */
                    case ProviderTools.TOOL_STYLE:
                        switch (ds_drawing.getStyle()) {
                            case STROKE:
                                ds_drawing.setStyle(Paint.Style.FILL_AND_STROKE);
                                Toast.makeText(getApplicationContext(), R.string.help_text_fill_stroke, Toast.LENGTH_SHORT).show();
                                break;
                            case FILL_AND_STROKE:
                                ds_drawing.setStyle(Paint.Style.FILL);
                                Toast.makeText(getApplicationContext(), R.string.help_text_fill, Toast.LENGTH_SHORT).show();
                                break;
                            case FILL:
                                ds_drawing.setStyle(Paint.Style.STROKE);
                                Toast.makeText(getApplicationContext(), R.string.help_text_stroke, Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;

                    /*
                     * Очистка экрана/истории
                     * */
                    case ProviderTools.TOOL_CLEAR:
                        ds_drawing.clear();
                        break;

                    /*
                     * Выбор фигуры для рисования
                     * */
                    default:
                        ds_drawing.setShapeCurrent(idTool);
                }
            }
        };
    }

    /*
     * Обработка изменения значения ширины контура
     * */
    private void initWidthListener() {
        sb_width.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ds_drawing.setWidth((float) progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /*
     * Получение значения нового цвета от окна выбора цвета
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_COLOR_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                int new_color = data.getIntExtra(ColorActivity.COLOR_NEW, ds_drawing.getColor());
                ds_drawing.setColor(new_color);
            }
        }
    }
}
