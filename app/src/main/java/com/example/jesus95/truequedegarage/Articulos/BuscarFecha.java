package com.example.jesus95.truequedegarage.Articulos;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jesus95.truequedegarage.R;

import java.util.Calendar;

public class BuscarFecha extends AppCompatActivity {
    Calendar calendar;
    String mMes, mFecha, mMimUid;
    int pivotemes;
    Button btn_exit, btn_fecha, btn_mes;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_buscar_fecha);
        getSupportActionBar().hide();

        btn_exit = (Button) findViewById(R.id.btn_exit_buscar_fecha);
        btn_fecha = (Button) findViewById(R.id.btn_fecha_actual_buscar_fecha);
        btn_mes = (Button) findViewById(R.id.btn_mes_actual_buscar_fecha);

        mMimUid = getIntent().getStringExtra("mMimUid");
        Log.e("Mylog", "mMimUid es: " + mMimUid);

        calendar = Calendar.getInstance();
        pivotemes = calendar.get(Calendar.MONTH)+1;
        if(pivotemes<10){
            mMes = "0"+pivotemes+calendar.get(Calendar.YEAR);
            btn_mes.setText("0"+calendar.get(Calendar.MONTH)+" (MES ACTUAL");
        }else {
            mMes = ""+pivotemes+calendar.get(Calendar.YEAR);
            btn_mes.setText(""+calendar.get(Calendar.MONTH)+" (MES ACTUAL");
        }
        if(calendar.get(Calendar.DATE)<10){
            btn_fecha.setText("0"+calendar.get(Calendar.DATE)+" (FECHA ACTUAL)");
            mFecha = "0"+calendar.get(Calendar.DATE)+mMes;
        }else {
            mFecha = ""+calendar.get(Calendar.DATE)+mMes;
            btn_fecha.setText(""+calendar.get(Calendar.DATE)+" (FECHA ACTUAL)");
        }

        Log.e("Mylog",""+mMes);
        Log.e("Mylog",""+mFecha);

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_mes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(BuscarFecha.this, ArticulosFecha.class);
                intent.putExtra("mFecha", mMes);
                intent.putExtra("mCategoria", "Mes");
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);
            }
        });

        btn_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(BuscarFecha.this, ArticulosFecha.class);
                intent.putExtra("mFecha", mFecha);
                intent.putExtra("mCategoria", "Dia");
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);
            }
        });
    }


}
