package com.example.jesus95.truequedegarage.Articulos;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.jesus95.truequedegarage.R;

public class TouchPractice2 extends AppCompatActivity {
    ImageView mEtiqueta;
    private float xDelta;
    private float yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_practice2);


        mEtiqueta = (ImageView) findViewById(R.id.img_view_touch_practice2);

        mEtiqueta.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);

                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        Log.e("Mylog", "La accion ha sido ABAJO");
                        xDelta = X - event.getX();
                        yDelta = Y - event.getY();
                        return true;
                    case (MotionEvent.ACTION_MOVE):
                        mEtiqueta.setX(X);
                        mEtiqueta.setY(Y);

                        Log.e("Mylog", "La acción ha sido MOVER");
                        return true;
                    case (MotionEvent.ACTION_UP):
                        Log.e("Mylog", "La acción ha sido ARRIBA");
                        return true;
                    case (MotionEvent.ACTION_CANCEL):
                        Log.e("Mylog", "La accion ha sido CANCEL");
                        return true;
                    case (MotionEvent.ACTION_OUTSIDE):
                        Log.e("Mylog",
                                "La accion ha sido fuera del elemento de la pantalla");
                        return true;
                    default:
                        return true;
                }
            }
        });
    }
}
