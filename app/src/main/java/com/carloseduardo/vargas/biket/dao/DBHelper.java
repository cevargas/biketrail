package com.carloseduardo.vargas.biket.dao;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    static final int VERSION = 3;
    private static final String DB_NAME = "biket.db";
    private static DBHelper mInstance = null;

    public static synchronized DBHelper getInstance(Context ctx) {
        if (mInstance == null) {
            mInstance = new DBHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS ["+RotaDAO.TABELA+"](\n" +
                "    ["+RotaDAO.ID+"] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \n" +
                "    ["+RotaDAO.DATA+"] DATETIME, \n" +
                "    ["+RotaDAO.SITUACAO+"] INTEGER, \n" +
                "    ["+RotaDAO.START_LONGITUDE+"] DOUBLE, \n" +
                "    ["+RotaDAO.END_LONGITUDE+"] DOUBLE, \n" +
                "    ["+RotaDAO.START_LATITUDE+"] DOUBLE, \n" +
                "    ["+RotaDAO.END_LATITUDE+"] DOUBLE, \n" +
                "    ["+RotaDAO.TOTAL_PERCURSO+"] VARCHAR(20), \n" +
                "    ["+RotaDAO.SINCRONIZADO+"] BOOLEAN(1), \n" +
                "    ["+RotaDAO.DH_SINCRONIZADO+"] DATETIME);\n");

        db.execSQL("CREATE TABLE IF NOT EXISTS ["+PercursoDAO.TABELA+"](\n" +
                "    ["+PercursoDAO.ID+"] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \n" +
                "    ["+PercursoDAO.ID_ROTA+"] INTEGER NOT NULL CONSTRAINT ["+ PercursoDAO.FK_ROTA_PERCURSO+"]" +
                "    REFERENCES "+RotaDAO.TABELA+"(["+RotaDAO.ID+"]) ON DELETE CASCADE ON UPDATE CASCADE, \n" +
                "    ["+PercursoDAO.SPEED+"] VARCHAR(20), \n" +
                "    ["+PercursoDAO.LONGITUDE+"] DOUBLE, \n" +
                "    ["+PercursoDAO.LATITUDE+"] DOUBLE);\n");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RotaDAO.TABELA + "");
        db.execSQL("DROP TABLE IF EXISTS " + PercursoDAO.TABELA + "");
        onCreate(db);
    }
}