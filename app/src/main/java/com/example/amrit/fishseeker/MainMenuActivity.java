package com.example.amrit.fishseeker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }



    static Intent createIntent(Context context){
        Intent intent = new Intent(context, MainMenuActivity.class);
        return intent;
    }
}
