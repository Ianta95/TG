package com.example.jesus95.truequedegarage.Articulos;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.jesus95.truequedegarage.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ArticulosString extends AppCompatActivity {
    List<Articulo> Articulos;
    RecyclerView Rview;
    Button btn_exit;
    String mUid, mBase, mPivote, mMimUid;
    String[] mDivisiones;
    ArticuloAdapter articuloAdapter;
    Intent intent;
    Boolean mClasificador;
    int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_articulos_string);
        getSupportActionBar().hide();


        mMimUid = getIntent().getStringExtra("mMimUid");
        Log.e("Mylog", "mMimUid es: " + mMimUid);
        mUid = getIntent().getStringExtra("mUid");
        Log.e("Mylog","Ahora mUsuario es: "+mUid);
        mBase = getIntent().getStringExtra("mBase");
        Log.e("Mylog","Ahora mBase es: "+mBase);

        mDivisiones = mBase.split(" ");
        j = mDivisiones.length;
        Log.e("Mylog","j es: "+j);
        for (int i=0;i<j;i++){
            Log.e("Mylog","Division "+i+" es "+mDivisiones[i]);
        }


        Rview = (RecyclerView) findViewById(R.id.recycler_view_string);
        btn_exit = (Button) findViewById(R.id.btn_exit_articulos_string);

        Rview.setLayoutManager(new LinearLayoutManager(this));
        Articulos = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        articuloAdapter = new ArticuloAdapter(this, Articulos);
        Rview.setAdapter(articuloAdapter);
        final GestureDetector mGestureDetector = new GestureDetector(ArticulosString.this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        database.getReference().child("Articulos").child("Strings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Articulos.removeAll(Articulos);
                for (DataSnapshot snapshot :
                        dataSnapshot.getChildren()) {
                    for (int i=0;i<j;i++){
                        mClasificador = false;
                        if(CompararStrings(mDivisiones[i],snapshot.getKey()) == true){
                            Log.e("Mylog",""+mDivisiones[i] +" y "+ snapshot.getKey()+ " son iguales");
                            mClasificador = true;
                            mPivote = ""+snapshot.getKey();
                            Log.e("Mylog","mPivote es: "+mPivote);

                        }else {
                            Log.e("Mylog",""+mDivisiones[i] +" y "+ snapshot.getKey()+ " son diferentes");
                        }
                        if (mClasificador == true) {
                            for (DataSnapshot subsnapshot : snapshot.getChildren()) {
                                Articulo articulo = subsnapshot.getValue(Articulo.class);
                                Articulos.add(articulo);
                            }
                        }
                    }

                }
                articuloAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Rview.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                try {
                    View child = rv.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mGestureDetector.onTouchEvent(e)) {
                        int position = rv.getChildAdapterPosition(child);
                        Log.e("Mylog","The Item Clicked is: "+position);
                        Log.e("Mylog","Articulo: "+Articulos.get(position).getmTitulo());
                        Log.e("Mylog","mId: "+Articulos.get(position).getmId());
                        Log.e("Mylog","Categoria: "+Articulos.get(position).getmCategoria());
                        intent = new Intent(ArticulosString.this,CargandoDatos.class);
                        intent.putExtra("mId",Articulos.get(position).getmId());
                        intent.putExtra("mMimUid", mMimUid);
                        intent.putExtra("mCategoria",Articulos.get(position).getmCategoria());
                        startActivity(intent);
                        return true;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                return false;
            }
            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            }
            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


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
