package com.example.jesus95.truequedegarage.Articulos;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.jesus95.truequedegarage.Perfil.ImageHelper;
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

public class CargandoDatos extends AppCompatActivity {
    Intent intent;
    ProgressDialog mProgress;
    String mId, mUid, mTitulo, mRango, mDescripcion, mCategoriaParaDatabase, mMimUid;
    int mViews, mCategoria, mpivote;
    final static int cons = 0;
    final long ONE_MEGABYTE = 1024 * 1024;
    FirebaseReferences firebaseReferences;
    Articulo articulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargando_datos);
        getSupportActionBar().hide();

        mId = getIntent().getStringExtra("mId");
        Log.e("Mylog", "mId es: " + mId);
        mMimUid = getIntent().getStringExtra("mMimUid");
        Log.e("Mylog", "mMimUid es: " + mMimUid);
        mCategoria = getIntent().getIntExtra("mCategoria", 1);
        Log.e("Mylog", "La categoria es: " + mCategoria);
        mCategoriaParaDatabase = ""+mCategoria;
        Log.e("Mylog", "La categoria EN STRING es: " + mCategoriaParaDatabase);
        firebaseReferences = new FirebaseReferences();
        articulo = new Articulo();


        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference(FirebaseReferences.ARTICULO);
            myRef.child("Categoria").child(mCategoriaParaDatabase).child(mId).addListenerForSingleValueEvent(new ValueEventListener() {
                //myRef.child("Categoria").child(""+mCategoria).child(""+keyRef).setValue(articulo);
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    articulo = dataSnapshot.getValue(Articulo.class);
                    Log.e("Mylog", dataSnapshot.toString());
                    intent = new Intent(CargandoDatos.this, PlantillaArticulo.class);
                    intent.putExtra("mTitulo",articulo.mTitulo);
                    intent.putExtra("mDescripcion",articulo.mDescripcion);
                    intent.putExtra("mId",articulo.mId);
                    intent.putExtra("mRango",articulo.mRango);
                    intent.putExtra("mCategoria",articulo.mCategoria);
                    intent.putExtra("mFechayHora",articulo.mFechayHora);
                    intent.putExtra("mNumeroArticulo",articulo.mNumeroArticulo);
                    intent.putExtra("mUid",articulo.mUid);
                    intent.putExtra("mViews",articulo.mViews);
                    intent.putExtra("mMimUid", mMimUid);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }catch (Exception e){
            Log.e("Mylog","Excepcion: "+e);
        };



    }
}
