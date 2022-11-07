package com.user.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.user.Assistance.InterfaceLogin;
import com.user.Database.Firebase;
import com.user.R;

public class Register extends AppCompatActivity implements InterfaceLogin.InsertUser {
    private EditText editRemail, editRsenha, editRConfSenha;
    private Button btRegister;
    private Toolbar toolb;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editRemail = findViewById(R.id.editRegisterEmail);
        editRsenha = findViewById(R.id.editRegisterPassword);
        editRConfSenha = findViewById(R.id.editConfirmPass);
        btRegister = findViewById(R.id.buttonRegister);

        startToolbar();

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(editRemail.getText().toString(), editRsenha.getText().toString(),
                        editRConfSenha.getText().toString());

            }

        });

    }

    private void startToolbar(){
        toolb = findViewById(R.id.toolbar);
        toolb.setTitle(getResources().getString(R.string.register));
        setSupportActionBar(toolb);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().show();

        toolb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }

        });

    }

    @Override
    public void registerUser(String email, String senha, String confSenha) {
        autenticacao = Firebase.getFirebaseAuthentication();
        if (email.isEmpty() && senha.isEmpty() && confSenha.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Entre com e-mail, senha e confirmar senha!", Toast.LENGTH_SHORT).show();

        } else if (senha.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Campo de senha vazio!", Toast.LENGTH_SHORT).show();

        } else if(confSenha.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Campo de confirmar senha vazio!", Toast.LENGTH_SHORT).show();

        }else if(!senha.equals(confSenha)){
            Toast.makeText(getApplicationContext(), "Senhas diferentes, corrija a senha!", Toast.LENGTH_SHORT).show();

        } else {
            autenticacao.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Usuário cadastrado com sucesso", Toast.LENGTH_LONG).show();
                        finish();

                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof FirebaseAuthWeakPasswordException) {
                        Toast.makeText(getApplicationContext(), "Senha deve conter no mínimo 6 digitos", Toast.LENGTH_LONG).show();

                    } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(getApplicationContext(), "E-mail inválido, digite o correto", Toast.LENGTH_LONG).show();

                    } else if (e instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "E-mail já esta cadastrado no sistema", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao efetuar o cadastro", Toast.LENGTH_LONG).show();

                    }

                }

            });

        }

    }

}