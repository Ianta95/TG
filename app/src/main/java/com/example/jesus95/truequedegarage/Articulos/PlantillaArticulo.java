package com.example.jesus95.truequedegarage.Articulos;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jesus95.truequedegarage.Perfil.ImageHelper;
import com.example.jesus95.truequedegarage.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class PlantillaArticulo extends AppCompatActivity {
    Intent intent;
    ImageView mPrincipal, m0, m1, m2, mCategoriaicono, mZoom, mOpcion1, mOpcion2;
    TextView mTitulo, mRango, mDescripcion;
    View mExtraoptions;
    String mId, mUid, mSTitulo, mSRango, mSDescripcion, mMimUid;
    byte [] bytearray0, bytearray1, bytearray2, bytearray3;
    Uri uri0, uri1, uri2;
    int mViewCounter=0, mContador = 0;
    File localFile;
    Button btn_opcion1, btn_opcion2;


    Bitmap bmp0, bmp1, bmp2, bmpPrincipal;
    int mViews, mCategoria;
    final static int cons = 0;
    final long ONE_MEGABYTE = 1024 * 1024;
    final long THREE_MEGABYTES = 3 * ONE_MEGABYTE;
    ImageHelper imageHelper;
    Button btn_exit;
    ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_plantilla_articulo);
        getSupportActionBar().hide();

        mPrincipal = (ImageView) findViewById(R.id.image_view_principal_plantilla);
        m0 = (ImageView) findViewById(R.id.image_view_0_plantilla);
        m1 = (ImageView) findViewById(R.id.image_view_1_plantilla);
        m2 = (ImageView) findViewById(R.id.image_view_2_plantilla);
        mCategoriaicono = (ImageView) findViewById(R.id.image_view_categoria_plantilla);
        mTitulo = (TextView) findViewById(R.id.txt_view_titulo_plantilla);
        mRango = (TextView) findViewById(R.id.txt_view_rango_plantilla);
        mDescripcion = (TextView) findViewById(R.id.txt_view_descripcion_plantilla);
        btn_exit = (Button) findViewById(R.id.btn_exit_plantilla_articulo);
        btn_opcion1 = (Button) findViewById(R.id.btn_opcion_1_plantilla_articulo);
        btn_opcion2 = (Button) findViewById(R.id.btn_opcion_2_plantilla_articulo);
        mOpcion1 = (ImageView) findViewById(R.id.img_view_opcion_1_platilla_articulo);
        mOpcion2 = (ImageView) findViewById(R.id.img_view_opcion_2_platilla_articulo);
        mProgress = new ProgressDialog(this);
        try {
            localFile = File.createTempFile("ImagenP", "jpg");
        }catch (Exception e){
            Log.e("Mylog",""+e);
        }

        mId = getIntent().getStringExtra("mId");
        Log.e("Mylog","Tomo el valor del intent, mId es: "+mId);
        mMimUid = getIntent().getStringExtra("mMimUid");
        Log.e("Mylog","Tomo el valor del intent, mMimUid es: "+mMimUid);
        mUid = getIntent().getStringExtra("mUid");
        Log.e("Mylog","Tomo el valor del intent, mUid es: "+mUid);
        mSTitulo = getIntent().getStringExtra("mTitulo");
        Log.e("Mylog","Tomo el valor del intent, mSTitulo es: "+mSTitulo);
        mSRango = getIntent().getStringExtra("mRango");
        Log.e("Mylog","Tomo el valor del intent, mSRango es: "+mSRango);
        mSDescripcion = getIntent().getStringExtra("mDescripcion");
        Log.e("Mylog","Tomo el valor del intent, mSDescripcion es: "+mSDescripcion);
        mViews = getIntent().getIntExtra("mViews",0);
        Log.e("Mylog","Tomo el valor del intent, mViews es: "+mViews);
        mCategoria= getIntent().getIntExtra("mCategoria",0);
        Log.e("Mylog","Tomo el valor del intent, mCategoria es: "+mCategoria);

        mTitulo.setText(""+mSTitulo);
        mRango.setText("Rango Precio +/-: "+mSRango);
        mDescripcion.setText(""+mSDescripcion);
        mDescripcion.setMovementMethod(ScrollingMovementMethod.getInstance());

        categoriaselector();

        try {

            btn_exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

            m0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Glide.with(PlantillaArticulo.this).load(uri0).placeholder(R.drawable.loader4).fallback(R.drawable.failtask).fitCenter().centerCrop().into(mPrincipal);
                    mViewCounter = 0;
                }
            });

            m1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Glide.with(PlantillaArticulo.this).load(uri1).placeholder(R.drawable.loader4).fallback(R.drawable.failtask).fitCenter().centerCrop().into(mPrincipal);
                    //mPrincipal.setImageBitmap(bmp1);
                    mViewCounter = 1;
                }
            });

            m2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Glide.with(PlantillaArticulo.this).load(uri2).placeholder(R.drawable.loader4).fallback(R.drawable.failtask).fitCenter().centerCrop().into(mPrincipal);
                    mViewCounter = 2;
                }
            });

            mPrincipal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (mViewCounter) {
                        case 0:
                            ImagenAumentada(uri0);
                            break;
                        case 1:
                            ImagenAumentada(uri1);
                            break;
                        case 2:
                            ImagenAumentada(uri2);
                            break;
                    }
                }
            });

            btn_opcion1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CompararStrings(mUid, mMimUid) == true){

                    }else{
                        intent = new Intent(PlantillaArticulo.this, CreacionIntercambios.class);
                        intent.putExtra("mUid", mUid);
                        intent.putExtra("mMimUid", mMimUid);
                        startActivity(intent);
                    }
                }
            });

            SelectordeFotos();

            if (CompararStrings(mUid, mMimUid) == true ){
                Log.e("Mylog","mUid y mUid2 son iguales");
                btn_opcion1.setText("EDITAR");
                btn_opcion1.setBackgroundResource(R.color.naranjaclaro);
                mOpcion1.setBackgroundResource(R.color.naranjaclaro);
                mOpcion1.setImageResource(R.drawable.editpencyl);
                btn_opcion2.setText("ELIMINAR");
                btn_opcion2.setBackgroundResource(R.color.rojoclaro);
                mOpcion2.setBackgroundResource(R.color.rojoclaro);
                mOpcion2.setImageResource(R.drawable.delete);

            }else{
                Log.e("Mylog",""+mId+" y "+mMimUid+" No son iguales");
                btn_opcion1.setText("INTERCAMBIAR");
                btn_opcion1.setBackgroundResource(R.color.azulclaro);
                mOpcion1.setBackgroundResource(R.color.azulclaro);
                mOpcion1.setImageResource(R.drawable.change);
                btn_opcion2.setText("FAVORITO");
                btn_opcion2.setBackgroundResource(R.color.amarillo);
                mOpcion2.setBackgroundResource(R.color.amarillo);
                mOpcion2.setImageResource(R.drawable.star);
            }
        }catch (Exception e){
            Log.e("Mylog","Excepcion: "+e);
        }
    }

    public void categoriaselector(){
        switch (mCategoria){
            case 0:
                mCategoriaicono.setImageResource(R.drawable.phone);
                break;
            case 1:
                mCategoriaicono.setImageResource(R.drawable.laptop);
                Log.e("Mylog","Cambio el icono");
                mCategoriaicono.setVisibility(View.VISIBLE);
                break;
            case 2:
                mCategoriaicono.setImageResource(R.drawable.clothes);
                break;
            case 3:
                mCategoriaicono.setImageResource(R.drawable.watch);
                break;
            case 4:
                mCategoriaicono.setImageResource(R.drawable.nintendo);
                break;
            case 5:
                mCategoriaicono.setImageResource(R.drawable.car);
                break;
            case 6:
                mCategoriaicono.setImageResource(R.drawable.baby);
                break;
            case 7:mCategoriaicono.setImageResource(R.drawable.soccerball);
                break;
            case 8:mCategoriaicono.setImageResource(R.drawable.microwave);
                break;
            case 9:
                mCategoriaicono.setImageResource(R.drawable.chip);
                break;
            case 10:
                mCategoriaicono.setImageResource(R.drawable.home);
                break;
            case 11:
                mCategoriaicono.setImageResource(R.drawable.guitarr);
                break;
            case 12:
                mCategoriaicono.setImageResource(R.drawable.toy);
                break;
            case 13:
                mCategoriaicono.setImageResource(R.drawable.book);
                break;
            case 14:
                mCategoriaicono.setImageResource(R.drawable.beauty);
                break;
            case 15:
                mCategoriaicono.setImageResource(R.drawable.cd);
                break;

        }
    }


    public void SelectordeFotos(){
        fprogressdialog("Cargando fotos, espere...");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://trueque-de-garage.appspot.com/");


        storageRef.child("Articulos/"+mId+"/0.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.e("Mylog","Completo");
                uri0 = uri;
                Log.e("Mylog","uri: "+uri0);
                Glide.with(PlantillaArticulo.this).load(uri).placeholder(R.drawable.loader4).fitCenter().centerCrop().into(mPrincipal);
                Glide.with(PlantillaArticulo.this).load(uri).placeholder(R.drawable.loader4).fitCenter().centerCrop().into(m0);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Mylog","Fallo");

                Glide.with(PlantillaArticulo.this).load(R.drawable.failtask).fitCenter().centerCrop().into(m1);
            }
        });

        storageRef.child("Articulos/"+mId+"/1.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.e("Mylog","Completo");
                uri1 = uri;
                Glide.with(PlantillaArticulo.this).load(uri).placeholder(R.drawable.loader4).fitCenter().centerCrop().into(m1);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Mylog","No cargo la foto");
                Glide.with(PlantillaArticulo.this).load(R.drawable.failtask).fitCenter().centerCrop().into(m1);
            }
        });

        storageRef.child("Articulos/"+mId+"/2.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.e("Mylog","Completo");
                uri2 = uri;
                Glide.with(PlantillaArticulo.this).load(uri).placeholder(R.drawable.loader4).fitCenter().centerCrop().into(m2);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Mylog","No cargo la foto");
                Glide.with(PlantillaArticulo.this).load(R.drawable.failtask).fitCenter().centerCrop().into(m2);
            }
        });

                    /*storageRef.child("Articulos/"+mId+"/0.jpg").getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            mPrincipal.setImageResource(R.drawable.failtask);
                        }
                    });
      */










        /*try {
            //Imagen Principal
            StorageReference imagePrincipalRef = storageRef.child("Articulos/"+mId+"/0.jpg");
            final File localFilePrincipal = File.createTempFile("ImagenP","jpg");
            imagePrincipalRef.getFile(localFilePrincipal).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    bmpPrincipal = BitmapFactory.decodeFile(localFilePrincipal.getAbsolutePath());
                    mPrincipal.setImageBitmap(bmpPrincipal);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    m2.setImageResource(R.drawable.failtask);
                }
            });

            //Imagen 0
            StorageReference imagen0Ref = storageRef.child("Articulos/"+mId+"/0.jpg");
            final File localFile0 = File.createTempFile("Imagen0","jpg");
            imagen0Ref.getFile(localFile0).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    bmp0 = BitmapFactory.decodeFile(localFile0.getAbsolutePath());
                    m0.setImageBitmap(bmp0);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    m2.setImageResource(R.drawable.failtask);
                }
            });

            //Image 1
            StorageReference imagen1Ref = storageRef.child("Articulos/"+mId+"/1.jpg");
            final  File localFile1 = File.createTempFile("Imagen1","jpg");
            imagen1Ref.getFile(localFile1).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    bmp1 = BitmapFactory.decodeFile(localFile1.getAbsolutePath());
                    m1.setImageBitmap(bmp1);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    m2.setImageResource(R.drawable.failtask);
                }
            });

            //Image 2
            StorageReference imagen2Ref = storageRef.child("Articulos/"+mId+"/2.jpg");
            final File localFile2 = File.createTempFile("Imagen2","jpg");
            imagen2Ref.getFile(localFile2).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    bmp2 = BitmapFactory.decodeFile(localFile2.getAbsolutePath());
                    m2.setImageBitmap(bmp2);
                    mProgress.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    m2.setImageResource(R.drawable.failtask);
                    mProgress.dismiss();
                }
            });

        }catch (Exception e){
            Log.e("Mylog","Excepcion: "+e);
        }*/

        mProgress.dismiss();




    }
    public void fprogressdialog(String mensaje){
        mProgress.setMessage(mensaje);
        mProgress.setCancelable(false);
        mProgress.show();
    }

    public void ImagenAumentada(Uri uri){
        final Dialog alerta;
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        final LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.image_zoom, null);

        mZoom = (ImageView) v.findViewById(R.id.image_view_zoom);
        Glide.with(PlantillaArticulo.this).load(uri).placeholder(R.drawable.loader4).fallback(R.drawable.failtask).fitCenter().centerCrop().into(mZoom);
        mBuilder.setView(v);
        alerta = mBuilder.show();
        mZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alerta.dismiss();
            }
        });
    }

    @Override
    protected void onDestroy(){
        Glide.clear(mPrincipal);
        super.onDestroy();
    }

    public boolean CompararStrings(String a, String b){
        if (a.equalsIgnoreCase(b)) {
            return true;
        }else {
            boolean resultado = b.contains(a);
            return false;
        }
    }

}




