package com.example.trancoso.projet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ParametresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    protected void onClickAjouterJeu(View view){
        Intent iii = new Intent(this, AjouterJeuActivity.class);
        startActivity(iii);
    }

    protected void onClickAjouterCarte(View view){
        Intent iii = new Intent(this, AjouterCarteActivity.class);
        startActivity(iii);
    }

    protected void onClickSupprimerJeu(View view){
        Intent iii = new Intent(this, SupprimerJeuActivity.class);
        startActivity(iii);
    }

    protected void onClickAfficherJeu(View view){
        Intent iii = new Intent(this, AfficherJeuActivity.class);
        startActivity(iii);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.jouerMenu:
                intent = new Intent(this, JouerActivity.class);
                startActivity(intent);
                return true;
            case R.id.modifierJeuMenu:
                intent = new Intent(this, ParametresActivity.class);
                startActivity(intent);
                return true;
            case R.id.quitterMenu:
                intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory( Intent.CATEGORY_HOME );
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
        }
        return super.onOptionsItemSelected(item);
    }
}
