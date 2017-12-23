package com.example.jesus95.truequedegarage.Articulos;

import android.app.Activity;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jesus95.truequedegarage.R;

public class TouchPractice extends Activity implements View.OnTouchListener {

    //Definimos el marco por el cual podemos arrastrar la imagen
    private ViewGroup marco;
    //Definimos la imagen que vasmo arrastrar
    private ImageView imagen;
    //Variables para centrar la imagen bajo el dedo
    private int xDelta;
    private int yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_practice);
        //Relacionamos
        marco = (ViewGroup)findViewById(R.id.marco);
        //Creamos la imagen
        imagen = new ImageView(this);
        //Señalamos la imagen a mostrar
        imagen.setImageResource(R.drawable.etiqueta);
        //Añadimos el Listener de la clase
        imagen.setOnTouchListener(this);
        //Añadimos la imagen al marco
        marco.addView(imagen);
    }
    //Al tocar la pantalla...
    public boolean onTouch(View view, MotionEvent event) {
        //Recogemos las coordenadas del dedo
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        //Dependiendo de la accion recogida..
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            //Al tocar la pantalla
            case MotionEvent.ACTION_DOWN:
                //Recogemos los parametros de la imagen que hemo tocado
                RelativeLayout.LayoutParams Params =
                        (RelativeLayout.LayoutParams) view.getLayoutParams();
                xDelta = X - Params.leftMargin;
                yDelta = Y - Params.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                //Al levantar el dedo simplemento mostramos un mensaje
                Toast.makeText(this, "Soltamos", Toast.LENGTH_LONG).show();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                //No hace falta utilizarlo
                break;
            case MotionEvent.ACTION_POINTER_UP:
                //No hace falta utilizarlo
                break;
            case MotionEvent.ACTION_MOVE:
                //Al mover el dedo vamos actualizando
                //los margenes de la imagen para
                //crear efecto de arrastrado
                RelativeLayout.LayoutParams layoutParams =
                        (RelativeLayout.LayoutParams) view.getLayoutParams();
                layoutParams.leftMargin = X - xDelta;
                layoutParams.topMargin = Y - yDelta;
                //Qutamos un poco de margen para
                //que la imagen no se deforme
                //al llegar al final de la pantalla y pueda ir más allá
                //probar también el codigo omitiendo estas dos líneas
                //le añadimos los nuevos
                //parametros para mover la imagen
                view.setLayoutParams(layoutParams);
                break;
        }
        //Se podría decir que 'dibujamos'
        //la posición de la imagen en el marco.
        marco.invalidate();
        return true;
    }}