package com.example.jesus95.truequedegarage.Articulos;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jesus95.truequedegarage.R;

public class BuscarString extends AppCompatActivity {
    Button btn_exit, btn_buscar;
    String mBase, mMimUid;
    EditText mTitulo;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_buscar_string);
        getSupportActionBar().hide();

        btn_exit = (Button) findViewById(R.id.btn_exit_buscar_string);
        btn_buscar = (Button) findViewById(R.id.btn_buscar_buscar_string);
        mTitulo = (EditText) findViewById(R.id.edit_text_buscar_string);

        mMimUid = getIntent().getStringExtra("mMimUid");
        Log.e("Mylog", "mMimUid es: " + mMimUid);

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBase = mTitulo.getText().toString();
                Log.e("Mylog",""+mBase);
                intent = new Intent(BuscarString.this, ArticulosString.class);
                intent.putExtra("mBase",mBase);
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);
            }
        });

    }
}
