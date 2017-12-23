package com.example.jesus95.truequedegarage.InicioSesion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.ValueEventListener;

public class Registrarse extends AppCompatActivity {
    EditText mNameField, mEmailField, mPasswordField;
    private Button mRegisterButton;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgress;
    funcionesprincipales mfunciones;
    MaterialDialog materialDialog;
    private FirebaseUser user;
    String mUid;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        getSupportActionBar().hide();

        mNameField = (EditText) findViewById(R.id.et_name);
        mEmailField = (EditText) findViewById(R.id.et_email);
        mPasswordField = (EditText) findViewById(R.id.et_password);
        mRegisterButton = (Button) findViewById(R.id.btn_registerdos);
        mProgress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mfunciones = new funcionesprincipales();


        mRegisterButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view){
                startRegister();
            }

        });

    }

    public void startRegister(){
        final String name = mNameField.getText().toString().trim();
        final String email = mEmailField.getText().toString().trim();
        final String password = mPasswordField.getText().toString().trim();
        mProgress.setMessage("Espere por favor");
        mProgress.show();
        int passwordvalida=0, longitudpassword;

        Log.e("Mylog","la password es: "+password);
        Log.e("Mylog","Ahora pasara al chequeo de password");

        passwordvalida = mfunciones.checarpassword(password);
        longitudpassword = mfunciones.checarlongitud(password);
        Log.e("Mylog","La longitud del password es: "+longitudpassword);
        if( (passwordvalida == 0) && (longitudpassword >= 6)){
            Log.e("Mylog","Inicia proceso de guardado");
            try {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgress.dismiss();
                        perfilinfo();
                        intent = new Intent(Registrarse.this,NewUserPaso1.class);
                        intent.putExtra("name",name)
                                .putExtra("email",email)
                                .putExtra("password",password)
                                .putExtra("mUid",mUid);
                        startActivity(intent);
                        finish();

                    }
                });


            }catch (Exception e) {  //Encierra excepciones
                Log.e("Mylog", "Excepcion: "+e);
                Log.e("Mylog", "Error al crear usuario");
                mProgress.dismiss();
                materialDialogcreator("Hubo un problema al registrar usuario, intentalo mas tarde");
            }//cierre try-catch excepcion

        }else{

            if(passwordvalida !=0){     //Si la password son puros numeros, envia mensaje
                mProgress.dismiss();
                materialDialogcreator("No se puede usar una contraseña que contenga solo numeros");
            }
            if(longitudpassword<6){     //Si la password es demasiado corta, envia mensaje
                mProgress.dismiss();
                materialDialogcreator("Tu password es demasiado corta");
            }

        }




    }//cierre funcion startregister

    public void materialDialogcreator(String Content){
        materialDialog = new MaterialDialog.Builder(this)
                .title("Error")
                .content(Content)
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
            Log.e("Mylog","usuid es: "+ mUid);

        }
    }


}