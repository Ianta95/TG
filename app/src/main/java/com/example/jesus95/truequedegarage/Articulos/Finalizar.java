package com.example.jesus95.truequedegarage.Articulos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jesus95.truequedegarage.R;

public class Finalizar extends AppCompatActivity {
    Button mExit, mVer;
    String mId, mMimUid;
    Intent intent;
    int mCategoria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar);
        getSupportActionBar().hide();

        mExit = (Button) findViewById(R.id.btn_salir_finalizar);
        mVer = (Button) findViewById(R.id.btn_ver_publicacion_finalizar);
        mMimUid = getIntent().getStringExtra("mMimUid");
        Log.e("Mylog", "mMimUid es: " + mMimUid);
        mId = getIntent().getStringExtra("mId");
        Log.e("Mylog","Ahora mId es: "+mId);
        mCategoria = getIntent().getIntExtra("mCategoria",20);
        Log.e("Mylog","Ahora mCategoria es: "+mId);

        mExit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                finish();

            }

        });

        mVer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                intent = new Intent(Finalizar.this, CargandoDatos.class);
                intent.putExtra("mId", mId);
                intent.putExtra("mCategoria", mCategoria);
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);
                finish();

            }

        });

    }
}
