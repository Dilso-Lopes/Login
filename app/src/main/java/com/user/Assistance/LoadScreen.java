package com.user.Assistance;

import android.content.Context;
import android.content.Intent;

import com.user.Activity.AccessScreen;
import com.user.Activity.MainActivity;
import com.user.Activity.Register;

public class LoadScreen {
    public void getIntent(Context context){
        Intent intRegister = new Intent(context, Register.class);
        context.startActivity( intRegister);

    }
    public void getIntentMain(Context context){
        Intent intMain = new Intent(context, MainActivity.class);
        context.startActivity( intMain);

    }public void getIntentAccess(Context context){
        Intent intAccess = new Intent(context, AccessScreen.class);
        context.startActivity( intAccess);

    }

}
