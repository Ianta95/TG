package com.example.jesus95.truequedegarage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jesus95.truequedegarage.InicioSesion.Login;
import com.example.jesus95.truequedegarage.InicioSesion.NewUserPaso1;
import com.example.jesus95.truequedegarage.InicioSesion.Registrarse;

public class InicioApp extends AppCompatActivity {
    //Declaramos botones en el java
    Button btnregistro, btniniciarsesion;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_app);
        getSupportActionBar().hide();

        //Los referenciamos con el layout
        btniniciarsesion = (Button) findViewById(R.id.btn_login);
        btnregistro = (Button) findViewById(R.id.btn_register);

        //Asignamos un OnClickListener
        btniniciarsesion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                intent = new Intent(InicioApp.this, Login.class);
                startActivity(intent);
                finish();
            }

        });

        //Asignamos un OnClicklistener
        btnregistro.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                intent = new Intent(InicioApp.this, Registrarse.class);
                startActivity(intent);
                finish();
            }

        });

    }
}
