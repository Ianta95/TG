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

public class ArticulosRangoMezclado extends AppCompatActivity {
    RecyclerView Rview;

    List<Articulo> Articulos;
    Button btn_exit;
    String mRango;
    String mUid, mMimUid;
    int mComparador, mMin, mMax;

    ArticuloAdapter articuloAdapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_articulos_rango_mezclado);
        getSupportActionBar().hide();

        mMimUid = getIntent().getStringExtra("mMimUid");
        Log.e("Mylog", "mMimUid es: " + mMimUid);
        mUid = getIntent().getStringExtra("mUid");
        Log.e("Mylog","Ahora mUsuario es: "+mRango);
        mMin = getIntent().getIntExtra("mMin", 30);
        mMax = getIntent().getIntExtra("mMax", 50);

        Rview = (RecyclerView) findViewById(R.id.recycler_view_rango_mezclado);
        btn_exit = (Button) findViewById(R.id.btn_exit_articulos_rango_mezclado);

        Rview.setLayoutManager(new LinearLayoutManager(this));
        Articulos = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        articuloAdapter = new ArticuloAdapter(this, Articulos);

        Rview.setAdapter(articuloAdapter);

        final GestureDetector mGestureDetector = new GestureDetector(ArticulosRangoMezclado.this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        database.getReference().child("Articulos").child("Rango").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Articulos.removeAll(Articulos);
                for (DataSnapshot snapshot :
                        dataSnapshot.getChildren()){
                            for (DataSnapshot subsnapshot: snapshot.getChildren()){
                                mComparador = Integer.parseInt(snapshot.getKey());
                                if (mComparador >=mMin && mComparador<=mMax){
                                    Articulo articulo = subsnapshot.getValue(Articulo.class);
                                    Articulos.add(articulo);
                                }
                            }
                    /*Articulo articulo = snapshot.getValue(Articulo.class);
                    Articulos.add(articulo);*/
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
                        intent = new Intent(ArticulosRangoMezclado.this,CargandoDatos.class);
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
}
