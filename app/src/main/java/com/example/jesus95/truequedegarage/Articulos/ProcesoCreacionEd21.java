package com.example.jesus95.truequedegarage.Articulos;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.jesus95.truequedegarage.R;

import java.util.ArrayList;
import java.util.List;

public class ProcesoCreacionEd21 extends AppCompatActivity {
    Button btnexit;
    ListView listView;
    String mUid, mMimUid;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_proceso_creacion_ed21);
        getSupportActionBar().hide();

        mUid = getIntent().getStringExtra("mUid");
        Log.e("Mylog", "Ahora mUsuario es: " + mUid);
        mMimUid = getIntent().getStringExtra("mMimUid");
        Log.e("Mylog", "mMimUid es: " + mMimUid);


        btnexit = (Button) findViewById(R.id.btn_exit_proceso_ed2);
        this.listView = (ListView) findViewById(R.id.List_view_proceso);

        List items = new ArrayList();
        items.add(new Item(R.drawable.phone, "Celulares y Smartphones"));
        items.add(new Item(R.drawable.laptop, "Computadoras, Laptops y Accesorios"));
        items.add(new Item(R.drawable.clothes, "Ropa, Bolsas y Clazado"));
        items.add(new Item(R.drawable.watch, "Joyas y Relojes"));
        items.add(new Item(R.drawable.nintendo, "Videojuegos, Consolas y Accesorios"));
        items.add(new Item(R.drawable.car, "Vehículos y Accesorios"));
        items.add(new Item(R.drawable.baby, "Bebés"));
        items.add(new Item(R.drawable.soccerball, "Deportes y Fitness"));
        items.add(new Item(R.drawable.microwave, "Electrodomésticos"));
        items.add(new Item(R.drawable.chip, "Electrónica, Audio y Video"));
        items.add(new Item(R.drawable.home, "Hogar, Muebles y Jarín"));
        items.add(new Item(R.drawable.guitarr, "Instrumentos Musicales"));
        items.add(new Item(R.drawable.toy, "Juegos y Juguetes"));
        items.add(new Item(R.drawable.book, "Libros, Revistas y Comics"));
        items.add(new Item(R.drawable.beauty, "Salud y Belleza"));
        items.add(new Item(R.drawable.cd, "Musica, Películas y Series"));

        this.listView.setAdapter(new ItemAdapter(this, items));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {

                Log.e("Mylog", "Hizo click en " + position);
                intent = new Intent(ProcesoCreacionEd21.this, ProcesoCreacionEd22.class);
                intent.putExtra("mCategoria", position);
                intent.putExtra("mUid", mUid);
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);
                finish();

            }

        });

        btnexit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();

            }

        });


    }
}
