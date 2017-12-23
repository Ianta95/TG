package com.example.jesus95.truequedegarage.InicioSesion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.jesus95.truequedegarage.MenuPrincipal;
import com.example.jesus95.truequedegarage.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button btninicio;
    int mcontrol=0;
    EditText txt_correo, txt_password;
    MaterialDialog materialDialog;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private ProgressDialog mProgress;
    funcionesprincipales mfunciones;
    Intent intent;
    String mUid, mMimUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        mfunciones = new funcionesprincipales();


        btninicio = (Button) findViewById(R.id.btn_inicio);
        txt_correo = (EditText) findViewById(R.id.et_correo);
        txt_password = (EditText) findViewById(R.id.et_contrasena);
        btninicio = (Button) findViewById(R.id.btn_inicio);

        mProgress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        btninicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                    iniciosesion();


            }

        });




    }

    private void iniciosesion(){
        String email = txt_correo.getText().toString().trim();
        String password = txt_password.getText().toString().trim();
        fprogressdialog("Espere por favor");

        try{

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    mProgress.dismiss();

                    if (!task.isSuccessful()) {
                        Log.e("Mylog", "signInWithEmail:failed", task.getException());
                        Toast.makeText(Login.this, "Fallo el inicio",
                                Toast.LENGTH_SHORT).show();

                    }else{
                        user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            // Name, email address, and profile photo Url
                            String usname = user.getDisplayName();
                            Log.e("Mylog","usname es: "+usname);
                            String usemail = user.getEmail();
                            Log.e("Mylog","usemail es: "+usemail);

                            // The user's ID, unique to the Firebase project. Do NOT use this value to
                            // authenticate with your backend server, if you have one. Use
                            // FirebaseUser.getToken() instead.
                            mUid = user.getUid();
                            mMimUid = user.getUid();
                            Log.e("Mylog","usuid es: "+ mUid);
                            intent = new Intent(Login.this, MenuPrincipal.class);
                            intent.putExtra("mUid",mUid);
                            intent.putExtra("mMimUid", mMimUid);
                            startActivity(intent);
                            finish();
                        }
                    }

                }
            });

        }catch (Exception e){
            Log.e("Mylog", "Excepcion: "+e);
            Log.e("Mylog", "Error al crear usuario");
            mProgress.dismiss();
            mensaje("Error al iniciar sesion. Intentelo mas tarde");
        }

    }

    //Dialog proces para esperar respuesta
    public void fprogressdialog(String mensaje){
        mProgress.setMessage(mensaje);
        mProgress.setCancelable(false);
        mProgress.show();
    }

    //Mensaje para mostrar
    public void mensaje(String mensaje){
        materialDialog = new MaterialDialog.Builder(this)
                .title("Error")
                .content(mensaje)
                .positiveText("Ok")
                .show();
    }

    public void perfilinfo(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String usname = user.getDisplayName();
            Log.e("Mylog","usname es: "+usname);
            String usemail = user.getEmail();
            Log.e("Mylog","usemail es: "+usemail);

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            mUid = user.getUid();
            mMimUid = user.getUid();
            Log.e("Mylog","usuid es: "+ mUid);
            mcontrol++;
            btninicio.setTextSize(20f);
            btninicio.setText("Listo! Haz click aqui para continuar");
            btninicio.setBackgroundResource(R.color.naranja);

        }
    }

}
