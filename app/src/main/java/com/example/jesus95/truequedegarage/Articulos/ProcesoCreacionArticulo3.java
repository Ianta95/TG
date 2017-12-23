package com.example.jesus95.truequedegarage.Articulos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jesus95.truequedegarage.R;

public class ProcesoCreacionArticulo3 extends AppCompatActivity {
    Intent intent;
    Button mContinuar, mExit;
    EditText mDescripcion;
    TextView mContador;
    String mTamano;
    int mDecide = 0;
    String mUid, mtitulo;
    int mView, mCategoria;
    byte[] byteArray0, byteArray1, byteArray2, byteArray3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceso_creacion_articulo3);
        getSupportActionBar().hide();


        mUid = getIntent().getStringExtra("mUid");
        Log.e("Mylog","Ahora mUsuario es: "+mUid);
        mCategoria = getIntent().getIntExtra("mCategoria", 0);
        Log.e("Mylog","mCategoria es: "+mCategoria);
        mtitulo = getIntent().getStringExtra("mTitulo");
        Log.e("Mylog","Se paso el titulo de la publicacion: "+mtitulo);
        mView = getIntent().getIntExtra("mView",0);
        Log.e("Mylog","Se paso mView, el cual ahora es: "+mView);
        switch (mView){
            case 1:
                byteArray0 = getIntent().getByteArrayExtra("bytearray0");
                Log.e("Mylog","Se tomo bytearray 0");
                break;
            case 2:
                byteArray0 = getIntent().getByteArrayExtra("bytearray0");
                Log.e("Mylog","Se tomo bytearray 0");
                byteArray1 = getIntent().getByteArrayExtra("bytearray1");
                Log.e("Mylog","Se tomo bytearray 1");
                break;
            case 3:
                byteArray0 = getIntent().getByteArrayExtra("bytearray0");
                Log.e("Mylog","Se tomo bytearray 0");
                byteArray1 = getIntent().getByteArrayExtra("bytearray1");
                Log.e("Mylog","Se tomo bytearray 1");
                byteArray2 = getIntent().getByteArrayExtra("bytearray2");
                Log.e("Mylog","Se tomo bytearray 2");
                break;
            case 4:
                byteArray0 = getIntent().getByteArrayExtra("bytearray0");
                Log.e("Mylog","Se tomo bytearray 0");
                byteArray1 = getIntent().getByteArrayExtra("bytearray1");
                Log.e("Mylog","Se tomo bytearray 1");
                byteArray2 = getIntent().getByteArrayExtra("bytearray2");
                Log.e("Mylog","Se tomo bytearray 2");
                byteArray3 = getIntent().getByteArrayExtra("bytearray3");
                Log.e("Mylog","Se tomo bytearray 3");
                break;
        }

        mDescripcion = (EditText) findViewById(R.id.edit_text_descripcion_articulo);
        mContador = (TextView) findViewById(R.id.TextViewContador);
        mContinuar = (Button) findViewById(R.id.btn_continuar_etapa3);
        mExit = (Button) findViewById(R.id.btn_exit_proceso3);


        mExit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                finish();
            }

        });

        mDescripcion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mDescripcion.getText().length()!=0){
                    mContinuar.setBackgroundResource(R.color.azulclaro);
                    mContinuar.setText("CONTINUAR");
                    mDecide=1;
                }else{
                    mContinuar.setBackgroundResource(R.color.colorPrimary);
                    mContinuar.setText("DEBES ESCRIBIR UNA DESCRIPCION");
                    mDecide=0;
                }
                mTamano = "" + String.valueOf(s.length());
                mContador.setText(""+mTamano+"/150");
            }
        });

        mContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mDecide==1){
                    intent = new Intent(ProcesoCreacionArticulo3.this, ProcesoCreacionArticulo4.class);
                    intent.putExtra("mUid", mUid);
                    intent.putExtra("mTitulo", mtitulo);
                    intent.putExtra("mView", mView);
                    intent.putExtra("mCategoria", mCategoria);
                    intent.putExtra("mDescripcion",mDescripcion.getText().toString().trim());
                    switch (mView){
                        case 1:
                            intent.putExtra("bytearray0", byteArray0);
                            break;
                        case 2:
                            intent.putExtra("bytearray0", byteArray0);
                            intent.putExtra("bytearray1", byteArray1);
                            break;
                        case 3:
                            intent.putExtra("bytearray0", byteArray0);
                            intent.putExtra("bytearray1", byteArray1);
                            intent.putExtra("bytearray2", byteArray2);
                            break;
                        case 4:
                            intent.putExtra("bytearray0", byteArray0);
                            intent.putExtra("bytearray1", byteArray1);
                            intent.putExtra("bytearray2", byteArray2);
                            intent.putExtra("bytearray3", byteArray3);
                            break;
                        //TODO investigar poque no me permite a veces con 4 fotos o 3 fotos
                    }
                    startActivity(intent);
                    finish();
                }else{

                }

            }
        });

    }
}
