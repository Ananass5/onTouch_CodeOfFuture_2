package com.ananas.ontouch_codeoffuture_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // создание полей для вывода на экран нужных значений
    private TextView coordinatesOut; // окно вывода значений координат
    private float x; // задание поля для координаты X
    private float y; // задание поля для координаты Y
    private String sDown; // строка для записи координат нажатия
    private String sMove; // строка для записи координат движения
    private String sUp; // строка для записи координат отпускания

    // задание дополнительных полей координат кота Шрёдингера
    private float xCat = 500; // задание поля для координаты X
    private float yCat = 500; // задание поля для координаты Y
    private final float deltaCat = 75; // допустимая погрешность в нахождении кота

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // присваивание переменной активити элемента представления activity_main
        coordinatesOut = findViewById(R.id.coordinatesOut);

        // выполнение действий при касании экрана
        coordinatesOut.setOnTouchListener(listener);
    }

    // объект обработки касания экрана (слушатель)
    private View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) { // в motionEvent пишутся координаты
            x = (int) motionEvent.getX(); // инициализация координат X
            y = (int) motionEvent.getY(); // инициализация координат Y

            switch (motionEvent.getAction()) { // метод getAction() считывает состояние касания (нажатие/движение/отпускание)
                case MotionEvent.ACTION_DOWN: // нажатие
                    sDown = "Нажатие: координата X = " + x + ", координата y = " + y;
                    sMove = "";
                    sUp = "";
                    break;
                case MotionEvent.ACTION_MOVE: // движение
                    sMove = "Движение: координата X = " + x + ", координата y = " + y;
                    // задание условия нахождения кота Шрёдингера
                    if (x < (xCat + deltaCat) && x > (xCat - deltaCat) && y < (yCat + deltaCat) && y > (yCat - deltaCat)) { // если пользователь коснулся места нахождения кота
                        // размещаем тост (контекст, сообщение, длительность сообщения)
                        Toast toast = new Toast(getApplicationContext()); // инициализация
                        toast.setGravity(Gravity.CENTER, (int) xCat, (int) yCat); // задание позиции на экране (положение, смещение по оси Х, смещение по оси Y)

                        // добавление в тост картинки
                        ImageView cat = new ImageView(getApplicationContext()); // создание объекта картинки (контекст)
                        cat.setImageResource(R.drawable.img); // добавление картинки из ресурсов

                        toast.setView(cat);
                        toast.show(); // демонстрация тоста на экране


                    }
                    break;
                case MotionEvent.ACTION_UP: // отпускание
                case MotionEvent.ACTION_CANCEL: // внутрений сбой (аналогичен ACTION_UP)
                    sMove = "";
                    sUp = "Отпускание: координата X = " + x + ", координата y = " + y;
                    break;
            }

            // вывод на экран в три строки считанных значений координат
            coordinatesOut.setText(sDown + "\n" + sMove + "\n" + sUp);

            // рандомизируем значение xCat и yCat
         //   xCat = (float) Math.random() * 900;
         //   yCat = (float) Math.random() * 1600;

            return true; // подтверждение нашей обработки событий
        }
    };
}