package com.example.jesus95.truequedegarage.InicioSesion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jesus95.truequedegarage.MenuPrincipal;
import com.example.jesus95.truequedegarage.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewUserPaso1 extends AppCompatActivity {

    DatabaseReference mDatabase;
    Button continuar;
    EditText movil, fijo;
    String mUsuario, mName, mPassword, mCorreo, mUid;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_paso1);
        getSupportActionBar().hide();

        continuar = (Button) findViewById(R.id.btn_continuar_paso1);
        movil = (EditText) findViewById(R.id.edt_movil_paso1);
        fijo = (EditText) findViewById(R.id.edt_fijo_paso1);

        mName = getIntent().getStringExtra("name");
        Log.e("Mylog","mName ahora es :"+mName);
        mCorreo = getIntent().getStringExtra("email");
        Log.e("Mylog","mCorreo ahora es :"+mCorreo);
        mPassword = getIntent().getStringExtra("password");
        Log.e("Mylog","mPassword ahora es :"+mPassword);
        mUid= getIntent().getStringExtra("mUid");
        Log.e("Mylog","mUid ahora es :"+mUid);


        /*mUsuario = getIntent().getStringExtra("Usuario");
        Log.e("Mylog","mUsuario ahora es :"+mUsuario);*/

        continuar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                ActualizarUsuarios();
                intent = new Intent(NewUserPaso1.this, MenuPrincipal.class);
                intent.putExtra("mUid",mUid);
                startActivity(intent);
                finish();
            }

        });

    }

    public void ActualizarUsuarios(){

        mDatabase = FirebaseDatabase.getInstance().getReference()
                .child("Usuario");
        mDatabase.child("Basedatos").child(""+mUid).child("movil").setValue(movil.getText().toString().trim());
        mDatabase.child("Basedatos").child(""+mUid).child("fijo").setValue(fijo.getText().toString().trim());
        mDatabase.child("Basedatos").child(""+mUid).child("nombre").setValue(mName);
        mDatabase.child("Basedatos").child(""+mUid).child("correo").setValue(mCorreo);
        mDatabase.child("Basedatos").child(""+mUid).child("password").setValue(mPassword);
        mDatabase.child("Basedatos").child(""+mUid).child("uid").setValue(mUid);
    }


}
