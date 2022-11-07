package com.user.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.user.Assistance.LoadScreen;
import com.user.Assistance.InterfaceLogin;
import com.user.Database.Firebase;
import com.user.R;

public class MainActivity extends AppCompatActivity implements InterfaceLogin {
    private EditText editEmail, editSenha;
    private TextView textOption;
    private Button btLogin;
    private LoadScreen intentRegister;
    private FirebaseAuth autenticacao;
    private LoadScreen intentAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editEmail = findViewById(R.id.editEmail);
        editSenha = findViewById(R.id.editPass);
        btLogin = findViewById(R.id.butLogin);
        textOption = findViewById(R.id.textRegister);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateUser(editEmail.getText().toString(), editSenha.getText().toString());

            }

        });

        textOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editEmail.getText().clear();
                editSenha.getText().clear();
                intentRegister = new LoadScreen();
                intentRegister.getIntent(MainActivity.this);

            }

        });

    }

    @Override
    public void validateUser(String email, String senha) {
        autenticacao = Firebase.getFirebaseAuthentication();
        if (email.isEmpty() && senha.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Entre com e-mail e senha!", Toast.LENGTH_SHORT).show();

        }else if(senha.isEmpty()){
            Toast.makeText(getApplicationContext(), "Campo de senha vazio!", Toast.LENGTH_SHORT).show();

        }else {
            autenticacao.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        finish();
                        editEmail.getText().clear();
                        editSenha.getText().clear();
                        intentAccess = new LoadScreen();
                        intentAccess.getIntentAccess(MainActivity.this);

                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof FirebaseAuthInvalidUserException) {
                        Toast.makeText(getApplicationContext(), "E-mail inválido, digite o correto!", Toast.LENGTH_SHORT).show();

                    } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(getApplicationContext(), "Senha inválida!", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Erro ao efetuar o login", Toast.LENGTH_SHORT).show();

                    }

                }

            });

        }

    }

}