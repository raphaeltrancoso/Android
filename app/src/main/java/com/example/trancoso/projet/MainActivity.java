package com.example.trancoso.projet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void jouerActivity(View view){
        Intent iii = new Intent(this, JouerActivity.class);
        startActivity(iii);
    }

    protected void parametresActivity(View view){
        Intent iii = new Intent(this, ParametresActivity.class);
        startActivity(iii);
    }
}
