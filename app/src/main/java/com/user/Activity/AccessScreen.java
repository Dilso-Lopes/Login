package com.user.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.user.Assistance.LoadScreen;
import com.user.R;

public class AccessScreen extends AppCompatActivity {
    private Button btSair;
    private LoadScreen intentMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_screen);
        btSair = findViewById(R.id.butExit);

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                intentMain = new LoadScreen();
                intentMain.getIntentMain(AccessScreen.this);

            }

        });

    }

}