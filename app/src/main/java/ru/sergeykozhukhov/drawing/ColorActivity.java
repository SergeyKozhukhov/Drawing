package ru.sergeykozhukhov.drawing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.madrapps.pikolo.HSLColorPicker;
import com.madrapps.pikolo.listeners.SimpleColorSelectionListener;


/*
* Окно выбор цвета
* */
public class ColorActivity extends AppCompatActivity {

    /*
    * COLOR_NEW - значение для передачи/получения нового цвета
    * colorNew - текущее значение, выбранное на цветовой схеме
    * */

    public final static String COLOR_NEW = "COLOR_NEW";
    private int colorNew;

    private Button btn_setColor;
    private ImageView imv_indicatorColor;
    private HSLColorPicker hslColorPicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        btn_setColor = findViewById(R.id.btn_setColor);
        imv_indicatorColor = findViewById(R.id.imv_indicatorColor);
        hslColorPicker = findViewById(R.id.colorPiker);

        initBtnSetColorListener();
        initHslColorPickerListener();

    }
    /*
    * Инициализация обработки изменений значения нового цвета
    * */
    private void initHslColorPickerListener()
    {
        hslColorPicker.setColorSelectionListener(new SimpleColorSelectionListener() {
            @Override
            public void onColorSelected(int color) {
                colorNew = color;
                imv_indicatorColor.setBackgroundColor(color);
            }
        });
    }

    /*
    * Инициализация обработки подтверждения значения нового цвета
    * */
    private void initBtnSetColorListener()
    {
        btn_setColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra(COLOR_NEW, colorNew);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
