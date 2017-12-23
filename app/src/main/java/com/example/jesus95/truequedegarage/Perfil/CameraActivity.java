package com.example.jesus95.truequedegarage.Perfil;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;


import com.example.jesus95.truequedegarage.Manifest;
import com.example.jesus95.truequedegarage.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.bitmap;

public class CameraActivity extends AppCompatActivity implements OnClickListener{
    ImageView mfoto;
    Button btntomarfoto;
    Button btnguardarfoto;
    Intent intent;
    final static int cons = 0;
    Bitmap bmp;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://trueque-de-garage.appspot.com/");
    String mUid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        mUid = getIntent().getStringExtra("mUid");
        Log.e("Mylog","Ahora mUsuario es: "+mUid);



        mfoto = (ImageView) findViewById(R.id.img_contenedor_camera);
        btntomarfoto = (Button) findViewById(R.id.btn_camera_activity);
        btnguardarfoto = (Button) findViewById(R.id.btn_guardar_foto_camera_activity);


        btntomarfoto.setOnClickListener(this);
        btnguardarfoto.setOnClickListener(this);

        mfoto.setBackgroundResource(R.drawable.iconousuario);
        init();
        btnguardarfoto.setBackgroundResource(R.color.naranja);

    }

    public void init(){

    }

    @Override
    public void onClick(View v) {
        int id;
        id = v.getId();
        switch (id){
            case R.id.btn_camera_activity:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,cons);
                break;
            case R.id.btn_guardar_foto_camera_activity:

                Log.e("Mylog","Hizo click en btn guardar");

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                StorageReference imagemUidRef = storageRef.child(""+mUid+".jpg");
                StorageReference imagesRef = storageRef.child("profile_photos/"+mUid+".jpg");

                UploadTask uploadTask = imagesRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Log.e("Mylog","Se logro guardar la foto");
                        finish();
                    }
                });

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            Bundle ext = data.getExtras();
            bmp = (Bitmap)ext.get("data");
            mfoto.setImageBitmap(bmp);
            mfoto.setBackgroundResource(R.color.naranja);
            btnguardarfoto.setBackgroundResource(R.color.colorAccent);
            btnguardarfoto.setText("Haz click aqui para guardar foto");
        }
    }

}
