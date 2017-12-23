package com.example.jesus95.truequedegarage.Articulos;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.jesus95.truequedegarage.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

public class ProcesoCreacionEd22 extends AppCompatActivity {
    Intent intent;
    ImageView imageView0, imageView1, imageView2, imageView3;
    EditText editText_titulo, editText_descripcion, editText_rango;
    Button Seleccionrango, Guardarycontinuar, mExit, mTomarFoto, mSeleccionarFoto;
    TextView contador;
    String mUid, mTamano, mTitulo, mDescripcion, mRango, mDia, mMes, mYear, mCatRango, newRef, keyRef;
    int mCategoria, mImageViewClick=0, mViews, valor, mCargaFotos,
            mMostrarBotonTitulo=0, mMostrarImagen=0, mMostrarDescripcion=0, mMostrarNumberP=0;
    private static final int PICK_IMAGE = 100;
    Uri uri0, uri1, uri2, uri3;
    Bitmap bmp;
    ProgressDialog mProgress;
    FirebaseReferences firebaseReferences;
    Calendar calendar;
    Articulo articulo;
    StringTokenizer stringTokenizer;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://trueque-de-garage.appspot.com/");
    Uri uriSavedImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_proceso_creacion_ed22);
        getSupportActionBar().hide();

        imageView0 = (ImageView) findViewById(R.id.image_view_ed2_0);
        imageView1 = (ImageView) findViewById(R.id.image_view_ed2_1);
        imageView2 = (ImageView) findViewById(R.id.image_view_ed2_2);
        imageView3 = (ImageView) findViewById(R.id.image_view_ed2_3);
        contador = (TextView) findViewById(R.id.TextViewContador_ed2);
        editText_titulo = (EditText) findViewById(R.id.Edit_Text_Titulo_ed2_2);
        editText_descripcion = (EditText) findViewById(R.id.edit_text_descripcion_articulo_ed2);
        editText_rango = (EditText) findViewById(R.id.Edit_Text_Seleccion_Rango_ed2);
        Seleccionrango = (Button) findViewById(R.id.btn_seleccionar_rango_ed2);
        Guardarycontinuar = (Button) findViewById(R.id.Boton_continuar_ed2);
        mExit = (Button) findViewById(R.id.btn_exit_proceso_ed2_2);
        mProgress = new ProgressDialog(this);
        firebaseReferences = new FirebaseReferences();







        mUid = getIntent().getStringExtra("mUid");
        Log.e("Mylog","Ahora mUsuario es: "+mUid);
        mCategoria = getIntent().getIntExtra("mCategoria", 0);
        Log.e("Mylog","mCategoria es: "+mCategoria);

        imageView1.setVisibility(View.INVISIBLE);
        imageView2.setVisibility(View.INVISIBLE);
        imageView3.setVisibility(View.INVISIBLE);
        Guardarycontinuar.setVisibility(View.INVISIBLE);

        mExit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                finish();
            }

        });

        imageView0.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                mImageViewClick = 0;
                ImageSelector();
            }

        });

        imageView1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                mImageViewClick = 1;
                ImageSelector();

            }

        });

        imageView2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                mImageViewClick = 2;
                ImageSelector();

            }

        });

        imageView3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                mImageViewClick = 3;
                ImageSelector();

            }

        });

        editText_descripcion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTamano = "" + String.valueOf(s.length());
                contador.setText(""+mTamano+"/150");
                if( Integer.parseInt(mTamano) !=0){
                    mMostrarDescripcion=1;
                    Log.e("Mylog","Paso descripcion esta completo, Mostrar descripcion es: "+mMostrarDescripcion);
                    DecisionBoton(mMostrarBotonTitulo, mMostrarDescripcion, mMostrarImagen, mMostrarNumberP);
                }else{
                    mMostrarDescripcion=0;
                    Log.e("Mylog","No hay descripcion, Mostrar descripcion es: "+mMostrarDescripcion);
                    DecisionBoton(mMostrarBotonTitulo, mMostrarDescripcion, mMostrarImagen, mMostrarNumberP);
                }
            }
        });

        Seleccionrango.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                MejorNumberPicker();
            }

        });

        editText_titulo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(editText_titulo.length()!=0){
                    mMostrarBotonTitulo=1;
                    Log.e("Mylog","Paso titulo esta completo, Mostrar Boton es: "+mMostrarBotonTitulo);
                    DecisionBoton(mMostrarBotonTitulo, mMostrarDescripcion, mMostrarImagen, mMostrarNumberP);
                }else{
                    mMostrarBotonTitulo=0;
                    Log.e("Mylog","Sin titulo, ahora Mostrar Boton es: "+mMostrarBotonTitulo);
                    DecisionBoton(mMostrarBotonTitulo, mMostrarDescripcion, mMostrarImagen, mMostrarNumberP);
                }
            }
        });

        Guardarycontinuar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                fprogressdialog("Espere, cargando articulo");
                mTitulo = editText_titulo.getText().toString().trim();
                Log.e("Mylog","mTitulo es: "+mTitulo);
                mDescripcion = editText_descripcion.getText().toString().trim();
                Log.e("Mylog","mDescripcion es: "+mDescripcion);
                mRango = editText_rango.getText().toString().trim();
                Log.e("Mylog","mRango es: "+mRango);
                Log.e("Mylog","mViews es: "+mViews);

                crearCategoriasDiayMes();
                Log.e("Mylog","mDia es: "+mDia+" y mMes es: "+mMes);
                firebaseUpload();
            }

        });


    }

    public void ImageSelector(){

        final Dialog alerta;
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        final LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.image_select, null);

        mTomarFoto = (Button) v.findViewById(R.id.Boton_Tomar_Foto_ImageSelect);
        mSeleccionarFoto = (Button) v.findViewById(R.id.Boton_Seleccionar_Foto_ImageSelect);



        mBuilder.setTitle("Elige una opcion:");
        mBuilder.setView(v);
        alerta = mBuilder.show();
        mTomarFoto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                try {
                    Log.e("Mylog", "Hizo click en tomar foto");

                    //Creamos una carpeta en la memeria del terminal
                    File imagesFolder = new File(
                            Environment.getExternalStorageDirectory(), "TruequedeGarage");
                    imagesFolder.mkdirs();

                    Log.e("Mylog", "Se creo la carpeta");

                    //añadimos el nombre de la imagen
                    File image = new File(imagesFolder, "articulo" + mImageViewClick + ".jpg");
                    uriSavedImage = Uri.fromFile(image);

                    //Le decimos al Intent que queremos grabar la imagen

                    Intent cameraIntent = new Intent(
                            android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                    startActivityForResult(cameraIntent, 1);

                    alerta.dismiss();
                    if (mMostrarImagen == 0) {
                        mMostrarImagen = 1;

                    }
                }catch (Exception e){
                    Log.e("Mylog","Excepcion: "+e);
                }

            }

        });

        mSeleccionarFoto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Log.e("Mylog", "Hizo click en seleccionar foto");
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);

                alerta.dismiss();
                if(mMostrarImagen==0){
                    mMostrarImagen=1;

                }
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK){

            //GALLERY_INTENT
            DecisionBoton(mMostrarBotonTitulo, mMostrarDescripcion, mMostrarImagen, mMostrarNumberP);
            switch (mImageViewClick){
                case 0:

                    uri0 = data.getData();
                    imageView0.setImageURI(uri0);
                    if(imageView1.getVisibility()==View.INVISIBLE){
                        imageView1.setVisibility(View.VISIBLE);
                    }
                    Log.e("Mylog","Deberia verse la foto 1");
                    if (mViews==0){
                        mViews = 1;
                    }
                    break;
                case 1:
                    uri1 = data.getData();
                    imageView1.setImageURI(uri1);
                    if(imageView2.getVisibility()==View.INVISIBLE){
                        imageView2.setVisibility(View.VISIBLE);
                    }
                    Log.e("Mylog","Deberia verse la foto 2");
                    if (mViews==1){
                        mViews = 2;
                    }
                    break;
                case 2:
                    uri2 = data.getData();
                    imageView2.setImageURI(uri2);
                    if(imageView3.getVisibility()==View.INVISIBLE){
                        imageView3.setVisibility(View.VISIBLE);
                    }
                    Log.e("Mylog","Deberia verse la foto 3");
                    if (mViews==2){
                        mViews = 3;
                    }
                    break;
               /* case 3:
                    uri3 = data.getData();
                    imageView3.setImageURI(uri3);
                    Log.e("Mylog","Deberia verse la foto 4");
                    if (mViews==3){
                        mViews = 4;
                    }
                    break;*/
            }
        }


        if (requestCode == 1 && resultCode == RESULT_OK) {
            switch (mImageViewClick) {
                case 0:
                    uri0 = uriSavedImage;
                    Log.e("Mylog",""+uri0);
                    Log.e("Mylog",""+uriSavedImage);
                    bmp = BitmapFactory.decodeFile(
                            Environment.getExternalStorageDirectory()+
                                    "/TruequedeGarage/"+"Articulo"+mImageViewClick+".jpg");

                    imageView0.setImageBitmap(bmp);
                    if(imageView1.getVisibility()==View.INVISIBLE){
                        imageView1.setVisibility(View.VISIBLE);



                    }
                    Log.e("Mylog","Metodo 3, Deberia verse la foto");
                    if (mViews==0){
                        mViews = 1;
                    }
                    break;
                case 1:
                    uri1 = uriSavedImage;
                    Log.e("Mylog",""+uri1);
                    Log.e("Mylog",""+uriSavedImage);
                    bmp = BitmapFactory.decodeFile(
                            Environment.getExternalStorageDirectory()+
                                    "/TruequedeGarage/"+"Articulo"+mImageViewClick+".jpg");
                    imageView1.setImageBitmap(bmp);
                    if(imageView2.getVisibility()==View.INVISIBLE){
                        imageView2.setVisibility(View.VISIBLE);
                    }
                    Log.e("Mylog","Metodo 3, Deberia verse la foto");
                    if (mViews==1){
                        mViews = 2;
                    }
                    break;
                case 2:
                    uri2 = uriSavedImage;
                    Log.e("Mylog",""+uri2);
                    Log.e("Mylog",""+uriSavedImage);
                    //uri1 = uriSavedImage;
                    bmp = BitmapFactory.decodeFile(
                            Environment.getExternalStorageDirectory()+
                                    "/TruequedeGarage/"+"Articulo"+mImageViewClick+".jpg");
                    imageView2.setImageBitmap(bmp);

                    if (mViews==2){
                        mViews = 3;
                    }
                    break;
                /*case 3:
                    uri3 = uriSavedImage;
                    Log.e("Mylog",""+uri3);
                    Log.e("Mylog",""+uriSavedImage);
                    //uri1 = uriSavedImage;
                    bmp = BitmapFactory.decodeFile(
                            Environment.getExternalStorageDirectory()+
                                    "/TruequedeGarage/"+"Articulo"+mImageViewClick+".jpg");
                    imageView3.setImageBitmap(bmp);
                    Log.e("Mylog", "Metodo 3, Deberia verse la foto");
                    if (mViews == 3) {
                        mViews = 4;
                    }

                    break;*/
            }

        }


    }



    public void MejorNumberPicker(){
        AlertDialog.Builder mbuilder = new AlertDialog.Builder(this);

        final NumberPicker mNumberPicker0, mNumberPicker1, mNumberPicker2, mNumberPickerpesos;
        final String[] value = {"Pesos"};


        LayoutInflater inflater = this.getLayoutInflater();

        View v = inflater.inflate(R.layout.multiple_number_picker, null);

        mbuilder.setView(v);
        mbuilder.setTitle("APROXIMADO EN PRECIO");

        mNumberPicker0 = (NumberPicker) v.findViewById(R.id.NumberPickerdiezmil);
        mNumberPicker1 = (NumberPicker) v.findViewById(R.id.NumberPickermil);
        mNumberPicker2 = (NumberPicker) v.findViewById(R.id.NumberPickercien);
        mNumberPickerpesos = (NumberPicker) v.findViewById(R.id.NumberPickerpesos);

        mNumberPicker0.setMaxValue(9);
        mNumberPicker0.setMinValue(0);
        mNumberPicker1.setMaxValue(9);
        mNumberPicker1.setMinValue(0);
        mNumberPicker2.setMaxValue(9);
        mNumberPicker2.setMinValue(0);

        mNumberPickerpesos.setDisplayedValues(value);
        mbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int wich){
                valor = (mNumberPicker0.getValue()*10000)+(mNumberPicker1.getValue()*1000)+(mNumberPicker2.getValue()*100);
                editText_rango.setText(" $"+valor+".00");
                mMostrarNumberP=1;
                DecisionBoton(mMostrarBotonTitulo, mMostrarDescripcion, mMostrarImagen, mMostrarNumberP);
                Log.e("Mylog", "El valor tomado es: "+valor);
            }

        });

        mbuilder.show();

    }

    public void fprogressdialog(String mensaje){
        mProgress.setMessage(mensaje);
        mProgress.setCancelable(false);
        mProgress.show();
    }

    public void DecisionBoton(int condicion1, int condicion2, int condicion3, int condicion4){
        if (condicion1 == 1 && condicion2 == 1 && condicion3 == 1 && condicion4 == 1){
            Guardarycontinuar.setVisibility(View.VISIBLE);
        }else{
            Guardarycontinuar.setVisibility(View.INVISIBLE);
        }
    }

    public String HorayFecha(){
        calendar = Calendar.getInstance();
        Log.e("Mylog","mFecha es: "+calendar);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy HH mm ss");
        Log.e("Mylog","Formato: "+simpleDateFormat.format(calendar.getTime()));
        String retorno = ""+simpleDateFormat.format(calendar.getTime());
        return retorno;
    }

    public void firebaseUpload(){
        String mHorayFecha = ""+ HorayFecha();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(firebaseReferences.ARTICULO);
        newRef = ""+myRef.push().getKey();
        Log.e("Mylog","la Ref es: "+newRef);
        keyRef = newRef.substring(1,newRef.length());
        Log.e("Mylog","la key es: "+keyRef);
        articulo = new Articulo(mTitulo,mDescripcion,mUid,keyRef,mRango,mViews,mHorayFecha,mCategoria,0);

        //Articulos por Categoria
        myRef.child("Categoria").child(""+mCategoria).child(""+keyRef).setValue(articulo);


        //Articulos por titulo
        stringTokenizer = new StringTokenizer(mTitulo);
        Log.e("Mylog","Cuantos string lleva: "+stringTokenizer.countTokens());
        while (stringTokenizer.hasMoreTokens()){
            myRef.child("Strings").child(""+stringTokenizer.nextToken()).child(""+keyRef).setValue(articulo);
        }
        //Articulos por Dia y Mes
        String FechaCompleta = ""+mDia+mMes+mYear;
        String MesCompleto = ""+mMes+mYear;
        Log.e("Mylog","Fecha completa es :"+FechaCompleta);
        myRef.child("Dia").child(""+FechaCompleta).child(""+keyRef).setValue(articulo);
        myRef.child("Mes").child(""+MesCompleto).child(""+keyRef).setValue(articulo);

        //Articulos por Rango
        crearCategoriaRango();
        myRef.child("Rango").child(""+mCatRango).child(""+keyRef).setValue(articulo);
        subirFotos();

        //Articulos por mID
        myRef.child("mUid").child(""+mUid).child(""+keyRef).setValue(articulo);
    }

    public void crearCategoriasDiayMes(){
        String mHorayFecha = ""+ HorayFecha();
        stringTokenizer = new StringTokenizer(mHorayFecha);
        Log.e("Mylog","Cuantos string lleva: "+stringTokenizer.countTokens());
        for(int i=0;i<3;i++){
            switch (i){
                case 0:
                    mDia = ""+stringTokenizer.nextToken();
                    break;
                case 1:
                    mMes = ""+stringTokenizer.nextToken();
                    break;
                case 2:
                    mYear = ""+stringTokenizer.nextToken();
                    break;
            }
        }
    }

    public void crearCategoriaRango(){
        mCatRango = mRango.substring(1,(mRango.length()-3));
        Log.e("Mylog","mCatRango es :"+mCatRango);
    }

    public void subirFotos() {
        mProgress.dismiss();
        mCargaFotos = 0;
        fprogressdialog("Espere, subiendo fotos");
        switch (mViews){
            case 1:
                subirfoto0();
                break;
            case 2:
                subirfoto0();
                subirfoto1();
                break;
            case 3:
                subirfoto0();
                subirfoto1();
                subirfoto2();
                break;
            case 4:
                break;
        }




    }

    public void subirfoto0(){

        StorageReference image0 = storageRef.child("Articulos/" + keyRef + "/" + 0 + ".jpg");
        UploadTask uploadTask = image0.putFile(uri0);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            mCargaFotos++;
                finalizarcarga();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                Log.e("Mylog","Progreso: "+taskSnapshot.getBytesTransferred());
            }
        });

    }

    public void subirfoto1(){

        StorageReference image1 = storageRef.child("Articulos/" + keyRef + "/" + 1 + ".jpg");
        UploadTask uploadTask = image1.putFile(uri1);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                mCargaFotos++;
                finalizarcarga();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                Log.e("Mylog","Progreso: "+taskSnapshot.getBytesTransferred());
            }
        });

    }

    public void subirfoto2(){

        StorageReference image2 = storageRef.child("Articulos/" + keyRef + "/" + 2 + ".jpg");
        UploadTask uploadTask = image2.putFile(uri2);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            mCargaFotos++;
                finalizarcarga();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                Log.e("Mylog","Progreso: "+taskSnapshot.getBytesTransferred());
            }
        });

    }

    public void subirfoto3(){

        StorageReference image3 = storageRef.child("Articulos/" + keyRef + "/" + 2 + ".jpg");
        UploadTask uploadTask = image3.putFile(uri3);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                Log.e("Mylog","Progreso: "+taskSnapshot.getBytesTransferred());
            }
        });

    }


    public void finalizarcarga(){
        if (mCargaFotos == mViews){
            mProgress.dismiss();
            pasarafinalizar();
        }
    }

    public void pasarafinalizar(){
        intent = new Intent(ProcesoCreacionEd22.this, Finalizar.class);
        intent.putExtra("mId",keyRef);
        intent.putExtra("mCategoria",mCategoria);
        startActivity(intent);
        finish();
    }

}
