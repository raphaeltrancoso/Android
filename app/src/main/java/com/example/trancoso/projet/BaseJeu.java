package com.example.trancoso.projet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cedla on 30/12/2016.
 */

public class BaseJeu extends SQLiteOpenHelper {
    private final static int VERSION = 8;
    private static BaseJeu instance;
    private final static String DB_NAME = "base_jeu";

    private final static String TABLE_JEU = "jeu_table";
    private final static String COLONNE_THEME = "theme";

    private final static String TABLE_CARTE = "carte_table";
    private final static String COLONNE_RECTO = "recto";
    private final static String COLONNE_VERSO = "verso";
    //private final static String COLONNE_DIFFICULTE = "difficulte";


    private final static String CREATE_JEU = "create table " + TABLE_JEU + "(" +
            COLONNE_THEME + " varchar(30) not null," +
            " _id integer primary key );";

    private final static String CREATE_CARTE = "create table " + TABLE_CARTE + "(" +
            COLONNE_RECTO + "  varchar(100) not null, " +
            COLONNE_VERSO + " varchar(100) not null,    " +
            " id_jeu int references jeu_table, " +
            "_id integer primary key );";

    public static BaseJeu getInstance(Context context) {
        if (instance == null) {
            instance = new BaseJeu(context);
        }
        return instance;
    }

    private BaseJeu(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_JEU);
        db.execSQL(CREATE_CARTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("drop table if exists TABLE_JEU;");
            db.execSQL("drop table if exists TABLE_CARTE ;");
            onCreate(db);
        }
    }
}
