package com.example.jesus95.truequedegarage.Articulos;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.jesus95.truequedegarage.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ProcesoCreacionArticulo4 extends AppCompatActivity {
    Intent intent;
    DatabaseReference mDatabase;
    TextView mSeleccion;
    Button mBotonSeleccion;
    int valor;
    int ma, mb, mc, mTotal, mpov;
    String mUid, mtitulo, mDescripcion, mId;
    int mViews, mCategoria;
    Button mViewBoton;
    ProgressDialog mProgress;


    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://trueque-de-garage.appspot.com/");

    byte[] byteArray0, byteArray1, byteArray2, byteArray3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proceso_creacion_articulo4);

        mSeleccion = (TextView) findViewById(R.id.Edit_Text_Seleccion_Rango);
        mBotonSeleccion = (Button) findViewById(R.id.btn_seleccionar_rango);
        mViewBoton = (Button) findViewById(R.id.view_boton_guardar_fase4);
        mProgress = new ProgressDialog(this);

        mUid = getIntent().getStringExtra("mUid");
        Log.e("Mylog","Ahora mUsuario es: "+mUid);
        mCategoria = getIntent().getIntExtra("mCategoria", 0);
        Log.e("Mylog","mCategoria es: "+mCategoria);
        mtitulo = getIntent().getStringExtra("mTitulo");
        Log.e("Mylog","Se paso el titulo de la publicacion: "+mtitulo);
        mViews = getIntent().getIntExtra("mView",0);
        Log.e("Mylog","Se paso mViews, el cual ahora es: "+mViews);
        mDescripcion = getIntent().getStringExtra("mDescripcion");
        Log.e("Mylog","La descripcion es: "+mDescripcion);

        mViewBoton.setVisibility(View.INVISIBLE);




        switch (mViews){
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



        mViewBoton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                fprogressdialog("Espere por favor");
                mDatabase = FirebaseDatabase.getInstance().getReference()
                        .child("Articulos");

                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        ma = Integer.parseInt(dataSnapshot.child("a").getValue().toString());
                        Log.e("Mylog","ma es :"+ma);
                        mb = Integer.parseInt(dataSnapshot.child("b").getValue().toString());
                        Log.e("Mylog","mb es :"+mb);
                        mc = Integer.parseInt(dataSnapshot.child("c").getValue().toString());
                        Log.e("Mylog","mc es :"+mc);
                        mTotal = Integer.parseInt(dataSnapshot.child("Total").getValue().toString());
                        Log.e("Mylog","mTotal es :"+mTotal);
                        mProgress.dismiss();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
                fprogressdialog("Casi terminamos, espere por favor");
                decidir();
                mupdate();
                mProgress.dismiss();
                intent = new Intent(ProcesoCreacionArticulo4.this, Finalizar.class);
                intent.putExtra("mId", mId);
                startActivity(intent);
                finish();

            }

        });

        mBotonSeleccion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                MejorNumberPicker();
            }

        });

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
                mSeleccion.setText(" $"+valor+".00");
                Log.e("Mylog", "El valor tomado es: "+valor);
                mViewBoton.setVisibility(View.VISIBLE);
            }

        });

        mbuilder.show();

    }

    public String setmComplemento(int mCategoriausada, String mLetra, int x){

        String mComp;

        if (x>=1000){
            mComp = mCategoriausada+mLetra+""+x;
        }else{
            if(x>=100){
                mComp = mCategoriausada+mLetra+"0"+x;
            }else{
                if(x>=10){
                    mComp = mCategoriausada+mLetra+"00"+x;
                }else{
                    mComp = mCategoriausada+mLetra+"000"+x;
                }
            }
        }
        return mComp;
    }

    public void decidir() {

        if (ma<10000){
            mpov = ma;
            mId = ""+setmComplemento(mCategoria,"a", mpov);
        }else {
            if (mb<10000){
                mpov = mb;
                mId = ""+setmComplemento(mCategoria,"b", mpov);
            }else {
                if (mc<10000){
                    mpov = mc;
                    mId = ""+setmComplemento(mCategoria,"c", mpov);
                }
            }
        }

    }

    public void mupdate(){
        decidir2();
        mDatabase.child(""+mCategoria).child(mId).child("Titulo").setValue(mtitulo);
        mDatabase.child(""+mCategoria).child(mId).child("mUid").setValue(mUid);
        mDatabase.child(""+mCategoria).child(mId).child("Categoria").setValue(mCategoria);
        mDatabase.child(""+mCategoria).child(mId).child("Rango").setValue(valor);
        mDatabase.child(""+mCategoria).child(mId).child("Views").setValue(mViews);
        mDatabase.child(""+mCategoria).child(mId).child("Descripcion").setValue(mDescripcion);

        uploadfotosbuild();
    }

    public void decidir2() {

        if (ma<10000){
            ma++;
            mDatabase.child("a").setValue(ma);
        }else {
            if (mb<10000){
                mb++;
                mDatabase.child("b").setValue(mb);
            }else {
                if (mc<10000){
                    mc++;
                    mDatabase.child("c").setValue(mc);
                }
            }
        }
        mTotal++;
        mDatabase.child("Total").setValue(mTotal);

    }

    public void uploadfotos(int j, byte[] byteArrayfoto) {

        StorageReference imagemUidRef = storageRef.child("" + mId + ".jpg");
                StorageReference imagesup = storageRef.child("articulos/" + mId + "/"+j+".jpg");
                UploadTask uploadTaskup = imagesup.putBytes(byteArrayfoto);
                uploadTaskup.addOnFailureListener(new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }

                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Log.e("Mylog", "Se logro guardar la foto");
                    }

                });

    }

    public void fprogressdialog(String mensaje){
        mProgress.setMessage(mensaje);
        mProgress.setCancelable(false);
        mProgress.show();
    }

    public void uploadfotosbuild(){

        switch (mViews){
            case 1:
                uploadfotos(0,byteArray0);
                break;
            case 2:
                uploadfotos(0,byteArray0);
                uploadfotos(1,byteArray1);
                break;
            case 3:
                uploadfotos(0,byteArray0);
                uploadfotos(1,byteArray1);
                uploadfotos(2,byteArray2);
                break;
            case 4:
                uploadfotos(0,byteArray0);
                uploadfotos(1,byteArray1);
                uploadfotos(2,byteArray2);
                uploadfotos(3,byteArray3);
                break;
        }

    }

}

//TODO aun falta datos por actualizar en la base de datos