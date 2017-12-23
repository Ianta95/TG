package com.example.jesus95.truequedegarage.Articulos;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.jesus95.truequedegarage.R;

public class CreacionIntercambios extends AppCompatActivity {
    ProgressBar mBarraprecio;
    Button btn_exit;
    String mMimUid, mUid;
    ImageView mView0, mView1, mView2;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_creacion_intercambios);
        getSupportActionBar().hide();

        mUid = getIntent().getStringExtra("mUid");
        Log.e("Mylog","Ahora mUsuario es: "+mUid);
        mMimUid = getIntent().getStringExtra("mMimUid");
        Log.e("Mylog", "mMimUid es: " + mMimUid);

        mBarraprecio = (ProgressBar) findViewById(R.id.vertical_progressbar_creacion_intercambios);
        mView0 = (ImageView) findViewById(R.id.image_view_0_creacion_intercambios);
        mView1 = (ImageView) findViewById(R.id.image_view_1_creacion_intercambios);
        mView2 = (ImageView) findViewById(R.id.image_view_2_creacion_intercambios);
        btn_exit = (Button) findViewById(R.id.btn_exit_creacion_intercambios);

        mBarraprecio.setProgress(10);

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        mView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CreacionIntercambios.this, SeleccionarArticulos.class);
                intent.putExtra("mUid", mUid);
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);
            }
        });

    }
}
