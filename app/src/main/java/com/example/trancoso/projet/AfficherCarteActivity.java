package com.example.trancoso.projet;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class AfficherCarteActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>{

    private String authority = "com.example.trancoso.projet";

    private Spinner spinner;
    private SimpleCursorAdapter spinnerAdapter;
    private SimpleCursorAdapter listAdapter;
    private ListView carte;
    private long spinnerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_carte);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                spinnerID = id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_spinner_item, null,
                new String[]{"theme"},
                new int[]{android.R.id.text1}, 0);
        spinner.setAdapter(spinnerAdapter);

        carte = (ListView) findViewById(R.id.list);
        listAdapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2, null,
                new String[]{"recto", "verso"},
                new int[]{android.R.id.text1, android.R.id.text2}, 0);
        carte.setAdapter(listAdapter);

        LoaderManager manager = getLoaderManager();
        manager.initLoader(0, null, this);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void afficher(View view) {
        long id;//= spinnerID;  //spinner.getSelectedItemId();
        int position = spinner.getSelectedItemPosition();
        id = spinnerAdapter.getItemId(position);
        Cursor cursor = spinnerAdapter.getCursor();

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content")
                .authority(authority)
                .appendPath("carte_table")
                .appendPath("jeu");
        builder = ContentUris.appendId(builder, id);
        final Uri uri = builder.build();
        Log.d("uri=", uri.toString());
        LoaderManager manager = getLoaderManager();
        //pour alimenter la liste de titres
        manager.restartLoader(1, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                return new CursorLoader(getApplicationContext(),
                        uri, null, null, null, null);
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                listAdapter.swapCursor(data);
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                listAdapter.swapCursor(null);
            }
        });
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
        spinnerAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        spinnerAdapter.swapCursor(null);
    }
}
