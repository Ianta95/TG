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

public class ArticulosMimUid extends AppCompatActivity {
    RecyclerView Rview;

    List<Articulo> Articulos;
    Button btn_exit;
    String mUid, mMimUid;

    ArticuloAdapter articuloAdapter;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_articulos_mim_uid);
        getSupportActionBar().hide();

        mUid = getIntent().getStringExtra("mUid");
        Log.e("Mylog","Ahora mUsuario es: "+mUid);
        mMimUid = getIntent().getStringExtra("mUid");
        Log.e("Mylog", "mMimUid es: " + mMimUid);

        Rview = (RecyclerView) findViewById(R.id.recycler_view_mimuid);
        btn_exit = (Button) findViewById(R.id.btn_exit_articulos_mimuid);

        Rview.setLayoutManager(new LinearLayoutManager(this));
        Articulos = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        articuloAdapter = new ArticuloAdapter(this, Articulos);

        Rview.setAdapter(articuloAdapter);

        final GestureDetector mGestureDetector = new GestureDetector(ArticulosMimUid.this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        database.getReference().child("Articulos").child("mUid").child(""+mUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Articulos.removeAll(Articulos);
                for (DataSnapshot snapshot :
                        dataSnapshot.getChildren()){
                    Articulo articulo = snapshot.getValue(Articulo.class);
                    Articulos.add(articulo);
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
                        intent = new Intent(ArticulosMimUid.this,CargandoDatos.class);
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
