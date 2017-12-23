package com.example.jesus95.truequedegarage.Perfil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jesus95.truequedegarage.Articulos.ArticulosMimUid;
import com.example.jesus95.truequedegarage.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import static android.R.attr.bitmap;

public class TuPerfil extends AppCompatActivity {
    Intent intent;
    Button exit, photoperfil, misarticulos;
    DatabaseReference mDatabase;
    TextView lblNombre, lblcorreo, lblmovil, lblfijo;
    String mUid, mMimUid;
    ImageView mUsuario;
    final static int cons = 0;
    final long ONE_MEGABYTE = 1024 * 1024;
    ImageHelper imageHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_perfil);
        getSupportActionBar().hide();

        mUid = getIntent().getStringExtra("mUid");
        Log.e("Mylog","Ahora mUsuario es: "+mUid);
        mMimUid = getIntent().getStringExtra("mMimUid");
        Log.e("Mylog", "mMimUid es: " + mUid);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app
        StorageReference storageRef = storage.getReferenceFromUrl("gs://trueque-de-garage.appspot.com/");
        StorageReference imagesRef = storageRef.child("profile_photos/"+mUid+".jpg");



        misarticulos = (Button) findViewById(R.id.btn_misarticulos);
        exit = (Button) findViewById(R.id.btn_return_perfil);
        lblNombre = (TextView) findViewById(R.id.txt_nombre_usuario_perfil);
        lblcorreo = (TextView) findViewById(R.id.txt_correo_perfil);
        lblmovil = (TextView) findViewById(R.id.txt_movil_perfil);
        lblfijo = (TextView) findViewById(R.id.txt_fijo_perfil);
        photoperfil = (Button) findViewById(R.id.btn_editphoto_perfil);
        mUsuario = (ImageView) findViewById(R.id.img_foto_usuario_perfil);
        imageHelper = new ImageHelper();


        imagesRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imagenredonda(bmp);
                /*mUsuario.setImageBitmap(Bitmap.createScaledBitmap(bmp, mUsuario.getWidth(),
                        mUsuario.getHeight(), false));*/
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                mUsuario.setBackgroundResource(R.drawable.usuariostilo1);
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("Usuario");


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lblNombre.setText(dataSnapshot.child("Basedatos").child(mUid).child("nombre").getValue().toString());
                lblcorreo.setText(dataSnapshot.child("Basedatos").child(mUid).child("correo").getValue().toString());
                lblmovil.setText(dataSnapshot.child("Basedatos").child(mUid).child("movil").getValue().toString());
                lblfijo.setText(dataSnapshot.child("Basedatos").child(mUid).child("fijo").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        exit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                finish();
            }

        });

        photoperfil.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                intent = new Intent(TuPerfil.this, CameraActivity.class);
                intent.putExtra("mUid", mUid);
                startActivityForResult(intent,cons);
            }

        });

        misarticulos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TuPerfil.this, ArticulosMimUid.class);
                intent.putExtra("mUid", mUid);
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){

        }
    }

    public void imagenredonda(Bitmap bmp){

        // Llama al método encargado de cortar en forma cuadrada a la imagen.
        Bitmap croppedImage = ImageHelper.cropBitmapToSquare(bmp);

        // Llama al método encargado de redondear las esquinas de la imagen
        // previamente cortada. Recibe como parámetros el mapa de bits y el tamaño // de sus lados en pixeles.
        Bitmap roundedCornersImage = ImageHelper.getRoundedCornerBitmap(
                croppedImage, 120);
        mUsuario.setImageBitmap(roundedCornersImage);
    }

}
