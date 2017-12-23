package com.example.jesus95.truequedegarage.Articulos;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jesus95.truequedegarage.MenuPrincipal;
import com.example.jesus95.truequedegarage.R;

public class MenuArticulos extends AppCompatActivity {
    Button btn_exit, btn_categoria, btn_titulo, btn_rango, btn_fecha;
    Intent intent;
    String mMimUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_menu_articulos);
        getSupportActionBar().hide();

        btn_exit = (Button) findViewById(R.id.btn_exit_menu_articulos);
        btn_categoria = (Button) findViewById(R.id.btn_categoria_menu_articulos);
        btn_fecha = (Button) findViewById(R.id.btn_fecha_menu_articulos);
        btn_titulo = (Button) findViewById(R.id.btn_titulo_menu_articulos);
        btn_rango = (Button) findViewById(R.id.btn_precio_menu_articulos);

        mMimUid = getIntent().getStringExtra("mMimUid");
        Log.e("Mylog", "mMimUid es: " + mMimUid);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_categoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MenuArticulos.this, BuscarCategoria.class);
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);
            }
        });

        btn_rango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MenuArticulos.this, BuscarRango.class);
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);
            }
        });

        btn_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MenuArticulos.this, BuscarFecha.class);
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);
            }
        });

        btn_titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MenuArticulos.this, BuscarString.class);
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);
            }
        });
    }
}
