package com.example.trancoso.projet;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class JouerActivity extends FragmentActivity implements ListeJeuFragment.OnFragmentInteractionListener {

    private FragmentManager fManager;
    private String mAuthority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jouer);
        mAuthority = getResources().getString(R.string.authority);
        /*Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        fManager = getSupportFragmentManager();
        Fragment fragment = fManager.findFragmentById(R.id.liste_fragment);
        if (fragment == null) {
            fragment = ListeJeuFragment.newInstance(mAuthority,
                    "jeu_table", "theme");
            fManager.beginTransaction().add(R.id.liste_fragment, fragment)
                    .commit();

        }
    }

    /*public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
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
    }*/
    public void onIdSelection(long id){}
}
