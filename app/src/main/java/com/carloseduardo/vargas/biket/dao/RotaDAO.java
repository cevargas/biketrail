package com.carloseduardo.vargas.biket.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.carloseduardo.vargas.biket.models.Percurso;
import com.carloseduardo.vargas.biket.models.Rota;
import com.carloseduardo.vargas.biket.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RotaDAO {

    public static final String TAG = RotaDAO.class.getSimpleName();

    public static final String TABELA = "rota";

    public static final String ID = "_id";
    public static final String DATA = "data"; //data da rota
    public static final String SITUACAO = "situacao"; //iniciado 0 - pausado 1 - finalizado 2
    public static final String TOTAL_PERCURSO = "total_percurso"; //km total do percurso
    public static final String SINCRONIZADO = "sincronizado"; //enviao para API
    public static final String DH_SINCRONIZADO = "dhsincronizado"; //data envio para API
    public static final String START_LONGITUDE = "start_longitude"; //longitude inicial
    public static final String END_LONGITUDE = "end_longitude"; //longitude final
    public static final String START_LATITUDE = "start_latitude"; //latitude inicial
    public static final String END_LATITUDE = "end_latitudee"; //longitude end

    private Context context;

    public RotaDAO(Context context) {
        this.context = context;
    }

    public Long save(Rota rota) {

        SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();

        ContentValues novaRota = new ContentValues();
        novaRota.put(RotaDAO.DATA, Utils.getDateTime());
        novaRota.put(RotaDAO.SITUACAO, rota.getSituacao());
        novaRota.put(RotaDAO.SINCRONIZADO, rota.isSync());
        novaRota.put(RotaDAO.DH_SINCRONIZADO, (rota.getDataHoraSync() != null) ? rota.getDataHoraSync().toString() : null);
        novaRota.put(RotaDAO.TOTAL_PERCURSO, rota.getTotalPercurso());
        novaRota.put(RotaDAO.START_LONGITUDE, rota.getStartLongitude());
        novaRota.put(RotaDAO.END_LONGITUDE, rota.getEndLongitude());
        novaRota.put(RotaDAO.START_LATITUDE, rota.getStartLatitude());
        novaRota.put(RotaDAO.END_LATITUDE, rota.getEndLatitude());

        return db.insert(RotaDAO.TABELA, null, novaRota);
    }

    public List<Rota> list() {

        List<Rota> rotasList = new ArrayList<>();
        SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
        Cursor cursor = null;

        try {
            String[] columns = new String[] {
                ID, DATA, SITUACAO, START_LONGITUDE, START_LATITUDE
            };

            cursor = db.query(TABELA, columns, null, null, null, null, DATA+" DESC");

            while(cursor.moveToNext()) {

                Rota rota = new Rota();
                rota.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                rota.setStartLongitude(cursor.getDouble(cursor.getColumnIndex(START_LONGITUDE)));
                rota.setStartLatitude(cursor.getDouble(cursor.getColumnIndex(START_LATITUDE)));
                rota.setSituacao(cursor.getInt(cursor.getColumnIndex(SITUACAO)));
                String data = cursor.getString(cursor.getColumnIndex(DATA));
                rota.setData(Utils.dbStringToDateForDb(data));

                //Percurso
                Percurso percurso = new PercursoDAO(context).findPercursoByIdRota(db, rota.getId());
                rota.setPercurso(percurso);

                rotasList.add(rota);
            }
        } catch (SQLiteException e) {
            Log.d(TAG, e.getMessage());
        } finally{
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }

        return rotasList;
    }

    public Rota getRotaById(Long id){

        Rota rota = new Rota();
        SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
        Cursor cursor = null;

        try {

            String query = "SELECT * FROM " + TABELA + " WHERE " + ID + "=" + id;
            Log.d("query", query);
            cursor = db.rawQuery(query, null);

            if(cursor.moveToNext()) {

                rota.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                String data = cursor.getString(cursor.getColumnIndex(DATA));
                if(data != null) {
                    rota.setData(Utils.dbStringToDateForDb(data));
                }
                String dataSync = cursor.getString(cursor.getColumnIndex(DH_SINCRONIZADO));
                if(dataSync != null) {
                    rota.setDataHoraSync(Utils.dbStringToDateForDb(dataSync));
                }

                rota.setEndLatitude((double) cursor.getColumnIndex(END_LATITUDE));
                rota.setEndLongitude((double) cursor.getColumnIndex(END_LONGITUDE));
                rota.setSituacao(cursor.getInt(cursor.getColumnIndex(SITUACAO)));
                rota.setStartLatitude((double) cursor.getColumnIndex(START_LATITUDE));
                rota.setStartLongitude((double) cursor.getColumnIndex(START_LONGITUDE));
                rota.setTotalPercurso((double) cursor.getColumnIndex(TOTAL_PERCURSO));

                boolean sync = cursor.getInt(cursor.getColumnIndex(SINCRONIZADO)) > 0;
                rota.setSync(sync);

            }
        } catch (SQLiteException e) {
            Log.d(TAG, e.getMessage());
        } finally {
            if(cursor != null && !cursor.isClosed()){
                cursor.close();
            }
        }

        return rota;
    }

    public void delete() {
        SQLiteDatabase db = DBHelper.getInstance(context).getReadableDatabase();
        db.execSQL("DELETE FROM " + RotaDAO.TABELA);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '"+ RotaDAO.TABELA +"'");
    }
}