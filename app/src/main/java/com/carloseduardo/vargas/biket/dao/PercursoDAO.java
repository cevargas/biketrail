package com.carloseduardo.vargas.biket.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.carloseduardo.vargas.biket.models.Percurso;
import com.carloseduardo.vargas.biket.models.Rota;

public class PercursoDAO {

    public static final String TAG = PercursoDAO.class.getSimpleName();

    public static final String TABELA = "percurso";

    public static final String ID = "_id";
    public static final String ID_ROTA = "id_rota"; //id da Rota
    public static final String LONGITUDE = "longitude";
    public static final String LATITUDE = "latitude";
    public static final String SPEED = "speed";
    public static final String FK_ROTA_PERCURSO = "fk_rota_percurso";

    private Context context;

    public PercursoDAO(Context context) {
        this.context = context;
    }

    public boolean save(Percurso percurso) {

        SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();

        ContentValues novoPercurso = new ContentValues();
        novoPercurso.put(PercursoDAO.ID_ROTA, percurso.getId_rota());
        novoPercurso.put(PercursoDAO.LONGITUDE, percurso.getLongitude());
        novoPercurso.put(PercursoDAO.LATITUDE, percurso.getLatitude());
        novoPercurso.put(PercursoDAO.SPEED, percurso.getSpeed());

        db.insert(PercursoDAO.TABELA, null, novoPercurso);

        return true;
    }

    public Percurso findPercursoByIdRota(SQLiteDatabase db, Long id) {

        Percurso percurso = new Percurso();
        Cursor cursor = null;

        try {

            String query = "SELECT * FROM "+TABELA+"  WHERE " + ID + "=" + id;

            Log.d(TAG, query);
            cursor = db.rawQuery(query, null);

            if(cursor.moveToFirst()) {
                percurso.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                percurso.setId_rota(cursor.getLong(cursor.getColumnIndex(ID_ROTA)));
                percurso.setLatitude(cursor.getDouble(cursor.getColumnIndex(LATITUDE)));
                percurso.setLongitude(cursor.getDouble(cursor.getColumnIndex(LONGITUDE)));
            }

        } catch (SQLiteException e) {
            Log.d(TAG, e.getMessage());
        } finally{
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }

        return percurso;
    }
}
