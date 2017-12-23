package com.example.jesus95.truequedegarage.Articulos;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jesus95.truequedegarage.MenuPrincipal;
import com.example.jesus95.truequedegarage.R;

import java.io.ByteArrayOutputStream;

import static android.view.View.VISIBLE;

public class ProcesoCreacionArticulo2 extends AppCompatActivity {
    Intent intent;
    Button btncontinuar, btnexit;
    EditText mcampotitulo;
    Bitmap bmp0, bmp1, bmp2, bmp3;
    ImageView m0, m1, m2, m3;
    int mview = 0, mCategoria;
    int mseleccion = 0, magregar = 0;
    final static int cons = 0;
    String mUid, mtitulo;
    byte[] byteArray0, byteArray1, byteArray2, byteArray3;

    ByteArrayOutputStream stream = new ByteArrayOutputStream();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceso_creacion_articulo2);
        getSupportActionBar().hide();


        mUid = getIntent().getStringExtra("mUid");
        Log.e("Mylog","Ahora mUsuario es: "+mUid);
        mCategoria = getIntent().getIntExtra("mCategoria", 0);
        Log.e("Mylog","mCategoria es: "+mCategoria);


        btnexit = (Button) findViewById(R.id.btn_exit_proceso2);
        mcampotitulo = (EditText) findViewById(R.id.txt_publicacion_proceso2);
        btncontinuar = (Button) findViewById(R.id.btn_continuar_etapa2);
        m0 = (ImageView) findViewById(R.id.imageView0);
        m1 = (ImageView) findViewById(R.id.imageView1);
        m2 = (ImageView) findViewById(R.id.imageView2);
        m3 = (ImageView) findViewById(R.id.imageView3);

        btncontinuar.setVisibility(View.INVISIBLE);
        m0.setBackgroundResource(R.drawable.agregarfoto);

        btnexit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                finish();
            }

        });

        m0.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if(mview==0){

                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,cons);
                    mseleccion=0;
                    magregar=1;
                }else{
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,cons);
                    mseleccion=0;
                    magregar=0;
                }
            }

        });

        m1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if(mview==1){
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,cons);
                    mseleccion=1;
                    magregar=1;
                }else{
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,cons);
                    mseleccion=1;
                    magregar=0;
                }
            }

        });

        m2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if(mview==2){
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,cons);
                    mseleccion=2;
                    magregar=1;
                }else{
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,cons);
                    mseleccion=2;
                    magregar=0;
                }
            }

        });

        m3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if(mview==3){
                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,cons);
                    mseleccion=3;
                }
            }

        });

        btncontinuar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                mtitulo = mcampotitulo.getText().toString();
                if (mtitulo.length()==0){
                    Toast toast = Toast.makeText(getApplicationContext(),"Debe escribir un titulo para la publicacion", Toast.LENGTH_SHORT);
                    toast.show();
                }
                if (mtitulo.length()!=0){
                    intent = new Intent(ProcesoCreacionArticulo2.this, ProcesoCreacionArticulo3.class);
                    intent.putExtra("mCategoria", mCategoria);
                    intent.putExtra("mUid", mUid);
                    intent.putExtra("mTitulo", mtitulo);
                    intent.putExtra("mView", mview);
                    switch (mview){
                        case 1:
                            bmp0.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byteArray0 = stream.toByteArray();
                            intent.putExtra("bytearray0", byteArray0);
                            break;
                        case 2:
                            bmp0.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byteArray0 = stream.toByteArray();
                            intent.putExtra("bytearray0", byteArray0);
                            bmp1.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byteArray1 = stream.toByteArray();
                            intent.putExtra("bytearray1", byteArray1);
                            break;
                        case 3:
                            bmp0.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byteArray0 = stream.toByteArray();
                            intent.putExtra("bytearray0", byteArray0);
                            bmp1.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byteArray1 = stream.toByteArray();
                            intent.putExtra("bytearray1", byteArray1);
                            bmp2.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byteArray2 = stream.toByteArray();
                            intent.putExtra("bytearray2", byteArray2);
                            break;
                        case 4:
                            bmp0.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byteArray0 = stream.toByteArray();
                            intent.putExtra("bytearray0", byteArray0);
                            bmp1.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byteArray1 = stream.toByteArray();
                            intent.putExtra("bytearray1", byteArray1);
                            bmp2.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byteArray2 = stream.toByteArray();
                            intent.putExtra("bytearray2", byteArray2);
                            bmp3.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byteArray3 = stream.toByteArray();
                            intent.putExtra("bytearray3", byteArray3);
                            break;
                        //TODO investigar poque no me permite a veces con 4 fotos o 3 fotos
                    }
                    startActivity(intent);
                    finish();
                }
            }

        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            switch (mseleccion){
                case 0:
                    Bundle ext0 = data.getExtras();
                    bmp0 = (Bitmap)ext0.get("data");
                    
                    m0.setImageBitmap(bmp0);
                    m0.setBackgroundResource(R.color.naranja);
                    if (magregar==1){
                        m1.setBackgroundResource(R.drawable.agregarfoto);
                        mview++;
                    }
                    if(mview!=0 ){
                        btncontinuar.setVisibility(VISIBLE);
                    }
                    break;
                case 1:
                    Bundle ext1 = data.getExtras();
                    bmp1 = (Bitmap)ext1.get("data");
                    m1.setImageBitmap(bmp1);
                    m1.setBackgroundResource(R.color.naranja);
                    if (magregar==1){
                        m2.setBackgroundResource(R.drawable.agregarfoto);
                        mview++;
                    }
                    break;
                case 2:
                    Bundle ext2 = data.getExtras();
                    bmp2 = (Bitmap)ext2.get("data");
                    m2.setImageBitmap(bmp2);
                    m2.setBackgroundResource(R.color.naranja);
                    if (magregar==1){
                        m3.setBackgroundResource(R.drawable.agregarfoto);
                        mview++;
                    }
                    break;
                case 3:
                    Bundle ext3 = data.getExtras();
                    bmp3 = (Bitmap)ext3.get("data");
                    m3.setImageBitmap(bmp3);
                    m3.setBackgroundResource(R.color.naranja);
                    mview++;
                    break;
            }

        }
    }
}