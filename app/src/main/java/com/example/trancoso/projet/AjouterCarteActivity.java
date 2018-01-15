package com.example.trancoso.projet;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class AjouterCarteActivity extends AppCompatActivity
        implements  LoaderManager.LoaderCallbacks<Cursor>{

    private static String authority = "com.example.trancoso.projet";
    private Spinner spinner;
    private SimpleCursorAdapter adapter;
    private EditText question, reponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_carte);
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item, null,
                new String[]{"theme"},
                new int[]{android.R.id.text1}, 0);
        spinner.setAdapter(adapter);

        LoaderManager manager = getLoaderManager();
        manager.initLoader(0, null, this);

        question = (EditText)findViewById(R.id.question);
        reponse = (EditText)findViewById(R.id.reponse);
    }

    public void afficher(View view){
        Intent intent = new Intent(this, AfficherCarteActivity.class);
        startActivity(intent);
    }

    public void ajouter(View view){
        Editable editableQ = question.getText();
        Editable editableR = reponse.getText();
        String ed_q = editableQ .toString();
        String ed_r = editableR.toString();
        if(ed_q.contentEquals("") || ed_r.contentEquals("")){
            Toast.makeText(this,"un champ est vide", Toast.LENGTH_SHORT).show();
            return;
        }

        long id = spinner.getSelectedItemId();

        ContentResolver resolver = getContentResolver();

        ContentValues values = new ContentValues();
        values.put("id_jeu",id);
        values.put("recto",ed_q);
        values.put("verso",ed_r);

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath("carte_table");
        Uri uri = builder.build();
        uri = resolver.insert(uri,values);
        Log.d("insert=", "Insertion reussi !");
        editableQ.clear();
        editableR.clear();
        Toast.makeText(this,"carte ajouté", Toast.LENGTH_SHORT).show();
    }
    
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri uri;
        Uri.Builder builder = new Uri.Builder();
        uri = builder.scheme("content")
                .authority(authority)
                .appendPath("jeu_table")
                .build();
        return new CursorLoader(this, uri, new String[]{"_id", "theme"},
                null, null, null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
        Button ajouter = (Button) findViewById(R.id.ajouter);
        ajouter.setActivated(true);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
