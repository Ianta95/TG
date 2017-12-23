package com.example.jesus95.truequedegarage.Articulos;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jesus95.truequedegarage.R;

    public class TouchPractice3 extends AppCompatActivity {

    private ViewGroup marco;
    private float xDelta;
    private float yDelta;
    ImageView mImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_practice3);

        mImagen = (ImageView) findViewById(R.id.img_view_touch_practice3);
        mImagen.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        xDelta = view.getX() - event.getRawX();
                        yDelta = view.getY() - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.animate().x(event.getRawX() + xDelta).y(event.getRawY() + yDelta).setDuration(0).start();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });


    }

    }
