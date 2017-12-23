package com.example.jesus95.truequedegarage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jesus95.truequedegarage.Articulos.ArticulosCategoria;
import com.example.jesus95.truequedegarage.Articulos.CargandoDatos;
import com.example.jesus95.truequedegarage.Articulos.Finalizar;
import com.example.jesus95.truequedegarage.Articulos.MenuArticulos;
import com.example.jesus95.truequedegarage.Articulos.ProcesoCreacionArticulo1;
import com.example.jesus95.truequedegarage.Articulos.ProcesoCreacionEd21;
import com.example.jesus95.truequedegarage.Perfil.TuPerfil;
import com.google.firebase.auth.FirebaseAuth;

public class MenuPrincipal extends AppCompatActivity {
    Button btnusuario, btnlogout, btnBuscar, btnPlus, btnCrear;
    Intent intent;
    String mUid, mMimUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        getSupportActionBar().hide();

        mUid = getIntent().getStringExtra("mUid");
        Log.e("Mylog","mUid ahora es :"+mUid);
        mMimUid = getIntent().getStringExtra("mMimUid");
        Log.e("Mylog", "mMimUid es: " + mMimUid);

        btnusuario = (Button) findViewById(R.id.btn_usuario);
        btnlogout = (Button) findViewById(R.id.btn_logout_menu);
        btnBuscar = (Button) findViewById(R.id.btn_buscar);
        btnPlus = (Button) findViewById(R.id.btn_plus_item);
        btnCrear = (Button) findViewById(R.id.btn_crear);


        btnusuario.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                intent = new Intent(MenuPrincipal.this, TuPerfil.class);
                intent.putExtra("mUid",mUid);
                startActivity(intent);
            }

        });

        btnlogout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                FirebaseAuth.getInstance().signOut();
                intent = new Intent(MenuPrincipal.this, InicioApp.class);
                startActivity(intent);
                finish();
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){


                intent = new Intent(MenuPrincipal.this, MenuArticulos.class);
                intent.putExtra("mMimUid", mUid);
                startActivity(intent);


                /*intent = new Intent(MenuPrincipal.this, CargandoDatos.class);
                intent.putExtra("mId", "1a0000");
                startActivity(intent);
                finish();*/
            }

        });

        btnPlus.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                /*intent = new Intent(MenuPrincipal.this, CargandoDatos.class);
                intent.putExtra("mId","Ks_HBCHzqTH4Nk4nE9");
                intent.putExtra("mCategoria","1");
                startActivity(intent);*/

                intent = new Intent(MenuPrincipal.this, ProcesoCreacionEd21.class);
                intent.putExtra("mUid",mUid);
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);

            }

        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MenuPrincipal.this, ProcesoCreacionEd21.class);
                intent.putExtra("mUid",mUid);
                intent.putExtra("mMimUid", mMimUid);
                startActivity(intent);
            }
        });



    }
}
