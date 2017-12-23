package com.example.jesus95.truequedegarage.Articulos;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jesus95.truequedegarage.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BuscarRango extends AppCompatActivity {
    Button btn_exit, btn_menosde1, btn_entre1y5, btn_masde5, btn_personalizado;
    TextView txt_menosde1, txt_entre1y5, txt_masde5;
    long mRango1;
    int i=200;
    int mEmMin, mEmMax;
    int mComparador;
    Long mTotalRango1 = 0l, mTotalRango2=0l, mTotalRango3=0l, mTotal =0l;
    List<String> mChildrens;
    Intent intent;
    String mMimUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_buscar_rango);
        getSupportActionBar().hide();
        mChildrens = new ArrayList<>();

        btn_exit = (Button) findViewById(R.id.btn_exit_buscar_rango);
        btn_menosde1 = (Button) findViewById(R.id.btn_menosde1_buscar_rango);
        btn_entre1y5 = (Button) findViewById(R.id.btn_entre1y5_buscar_rango);
        btn_masde5 = (Button) findViewById(R.id.btn_masde5_buscar_rango);
        btn_personalizado = (Button) findViewById(R.id.btn_personalizado_buscar_rango);
        txt_menosde1 = (TextView) findViewById(R.id.txt_view_menosde1_buscar_rango);
        txt_entre1y5 = (TextView) findViewById(R.id.txt_view_entre1y5_buscar_rango);
        txt_masde5 = (TextView) findViewById(R.id.txt_view_masde5_buscar_rango);

        mMimUid = getIntent().getStringExtra("mMimUid");
        Log.e("Mylog", "mMimUid es: " + mMimUid);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

            database.getReference().child("Articulos").child("Rango").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    mChildrens.removeAll(mChildrens);
                    for (DataSnapshot snapshot :
                            dataSnapshot.getChildren()){
                         String nombre = snapshot.getKey();
                        Log.e("Mylog","La key es: "+nombre);
                        for (DataSnapshot snapshot1: snapshot.getChildren()){
                            mTotal++;
                            mComparador = Integer.parseInt(snapshot.getKey());
                            if (mComparador <1000){
                                mTotalRango1++;
                            }
                            if (mComparador >=1000 && mComparador<=5000){
                                mTotalRango2++;
                            }
                            if (mComparador >5000){
                                mTotalRango3++;
                            }
                        }


                        mChildrens.add(nombre);
                    }
                    Log.e("Mylog","mTotal es: "+mTotal);
                    Log.e("Mylog","mTotal es: "+mTotalRango1);
                    Log.e("Mylog","mTotal es: "+mTotalRango2);
                    Log.e("Mylog","mTotal es: "+mTotalRango3);
                    txt_menosde1.setText(""+mTotalRango1+" ARTICULOS");
                    txt_entre1y5.setText(""+mTotalRango2+" ARTICULOS");
                    txt_masde5.setText(""+mTotalRango3+" ARTICULOS");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e("Mylog","Fail");
                }
            });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_menosde1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(BuscarRango.this, ArticulosRangoMezclado.class);
                intent.putExtra("mMin", 100);
                intent.putExtra("mMax", 1000);
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);
            }
        });
        btn_masde5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(BuscarRango.this, ArticulosRangoMezclado.class);
                intent.putExtra("mMin", 5001);
                intent.putExtra("mMax", 100000);
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);
            }
        });
        btn_entre1y5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(BuscarRango.this, ArticulosRangoMezclado.class);
                intent.putExtra("mMin", 1000);
                intent.putExtra("mMax", 5000);
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);
            }
        });

        btn_personalizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RangoPersonalizado();
            }
        });




    }

    public void RangoPersonalizado(){
        try {
            AlertDialog.Builder mbuilder = new AlertDialog.Builder(this);
            final Button btn_aceptar_personalizado;
            final EditText mRangoMin, mRangoMax;


            LayoutInflater inflater = this.getLayoutInflater();
            View v = inflater.inflate(R.layout.rango_personalizado, null);
            mbuilder.setView(v);

            btn_aceptar_personalizado = (Button) v.findViewById(R.id.btn_aceptar_emergente_rango_personalizado);
            mRangoMin = (EditText) v.findViewById(R.id.edit_text_rango_minimo_emergente);
            mRangoMax = (EditText) v.findViewById(R.id.edit_text_rango_maximo_emergente);
            mRangoMin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRangoMin.setText("");
                }
            });
            mRangoMax.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRangoMax.setText("");
                }
            });

            btn_aceptar_personalizado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        mEmMin = Integer.parseInt(mRangoMin.getText().toString());
                        mEmMax = Integer.parseInt(mRangoMax.getText().toString());
                        intent = new Intent(BuscarRango.this, ArticulosRangoMezclado.class);
                        intent.putExtra("mMin", mEmMin);
                        intent.putExtra("mMax", mEmMax);
                        intent.putExtra("mMimUid", mMimUid);
                        startActivity(intent);
                    }catch (NumberFormatException MalIntroduccion){
                        Log.e("Mylog",""+MalIntroduccion);
                        Toast.makeText(getApplicationContext(),"Revisa que hayas puesto bien el rango", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            mbuilder.show();
        }catch (Exception e){

        }
    }

}
