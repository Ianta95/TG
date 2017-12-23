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
import java.util.concurrent.ExecutionException;

public class SeleccionarArticulos extends AppCompatActivity {
    RecyclerView Rview;

    int mSeleccionado;
    List<Articulo> Articulos;
    int mSuma;
    int [] mAsignacion;
    Button btn_exit;
    String mCategoria, mFecha;
    String mUid, mMimUid;
    Intent intent;
    MiniAdapter miniAdapter;
    private float xDelta;
    private float yDelta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_seleccionar_articulos);
        getSupportActionBar().hide();

        mUid = getIntent().getStringExtra("mUid");
        Log.e("Mylog","Ahora mUsuario es: "+mUid);
        mMimUid = getIntent().getStringExtra("mMimUid");
        Log.e("Mylog", "mMimUid es: " + mMimUid);
        mFecha = getIntent().getStringExtra("mFecha");
        Log.e("Mylog","Se tomo mFecha, es :"+mFecha);
        mCategoria = getIntent().getStringExtra("mCategoria");
        Log.e("Mylog","Se tomo mCategoria, es :"+mCategoria);
        Rview = (RecyclerView) findViewById(R.id.recycler_mini_view_seleccionar_articulos);
        btn_exit = (Button) findViewById(R.id.btn_exit_seleccionar_articulos);

        Rview.setLayoutManager(new LinearLayoutManager(this));
        Articulos = new ArrayList<>();
        mAsignacion = new int[50];
        for (int j = 0; j<50; j++){
            mAsignacion[j]=0;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        miniAdapter = new MiniAdapter(this, Articulos);

        Rview.setAdapter(miniAdapter);

        final GestureDetector mGestureDetector = new GestureDetector(SeleccionarArticulos.this, new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        database.getReference().child("Articulos").child("mUid").child(""+mMimUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Articulos.removeAll(Articulos);
                for (DataSnapshot snapshot :
                        dataSnapshot.getChildren()){
                    Articulo articulo = snapshot.getValue(Articulo.class);
                    Articulos.add(articulo);
                }
                miniAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Rview.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                try{
                    View child = rv.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mGestureDetector.onTouchEvent(e)) {
                        int position = rv.getChildAdapterPosition(child);
                        if (mAsignacion[position] == 1){
                            mAsignacion[position] = 0;
                        }else{
                            mAsignacion[position] = 1;
                        }

                        mSuma = 0;
                        for(int j=0;j<50;j++){
                            mSuma = mSuma + mAsignacion[j];
                        }
                        Log.e("Mylog","Los seleccionados son:"+mSuma);
                    }
                }catch (Exception ex){

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
