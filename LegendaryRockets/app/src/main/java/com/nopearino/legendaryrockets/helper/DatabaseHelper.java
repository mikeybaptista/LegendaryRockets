package com.nopearino.legendaryrockets.helper;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nopearino.legendaryrockets.model.Rockets;

public class DatabaseHelper extends SQLiteOpenHelper {

    //nome de base de dados
    private static final String DATABASE_NAME="Legendaries";

    //versão da base de dados
    private static final int DATABASE_VERSION=3;

    //nome das tabelas
    private static final String TABLE_ROCKETS = "rockets";

    //nome das colunas das tabelas
    private static final String KEY_ID = "id";
    private static final String KEY_NOME = "nome";
    private static final String KEY_MANUF = "manuf";

    // Tag para o LogCat
    private static final String LOG = "DatabaseHelper";

    // Instrução SQL para a criação da tabela ROCKETS
    private static final String CREATE_TABLE_ROCKETS = "CREATE TABLE "
            + TABLE_ROCKETS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NOME
            + " TEXT," + KEY_MANUF + " TEXT" + ")";


    public DatabaseHelper(Context context, String name, CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context applicationContext) {
        super(applicationContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criação das tabelas
        db.execSQL(CREATE_TABLE_ROCKETS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Remoção das tableas existentes
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROCKETS);

        // Criação das novas tabelas
        onCreate(db);
    }

    /**
     * Criar Rockets
     */
    public Rockets criarRockets(String nome, String manuf) {

        Rockets rocket = new Rockets(nome, manuf);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOME, rocket.getNome());
        values.put(KEY_MANUF, rocket.getManuf());

        // inserir artigo
        int rocket_id = (int) db.insert(TABLE_ROCKETS, null, values);
        rocket.setId(rocket_id);

        return rocket;
    }

    /**
     * Consulta de um rocket dado um id
     */
    public Rockets obterRockets(long rocket_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT  * FROM " + TABLE_ROCKETS + " WHERE "
                + KEY_ID + " = " + rocket_id;

        Log.e(LOG, query);

        Cursor c = db.rawQuery(query, null);

        if (c != null) c.moveToFirst();

        Rockets rocket = new Rockets();
        rocket.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        rocket.setManuf((c.getString(c.getColumnIndex(KEY_MANUF))));
        rocket.setNome((c.getString(c.getColumnIndex(KEY_NOME))));

        return rocket;
    }

    /**
     * Devolver todos os rockets numa lista
     */
    public List<Rockets> obterRockets() {
        String query;
        List<Rockets> rockets = new ArrayList<Rockets>();


        query = "SELECT  * FROM " + TABLE_ROCKETS;

        Log.e(LOG, query);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);


        // Iterar sobre todos os registos da tabela Rockets e adição à lista
        if (c.moveToFirst()) {
            do {
                Rockets rocket = new Rockets();
                rocket.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                rocket.setManuf((c.getString(c.getColumnIndex(KEY_MANUF))));
                rocket.setNome((c.getString(c.getColumnIndex(KEY_NOME))));

                // adicionar à lista
                rockets.add(rocket);
            } while (c.moveToNext());
        }
        return rockets;
    }

    /**
     * Atualizar rocket
     */
    public int atualizarRocket(Rockets rocket) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOME, rocket.getNome());
        values.put(KEY_MANUF, rocket.getManuf());

        // updating row
        return db.update(TABLE_ROCKETS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(rocket.getId()) });
    }

    /**
     * Remover rocket
     */
    public void removerRocket(long rocket_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ROCKETS, KEY_ID + " = ?",
                new String[] { String.valueOf(rocket_id) });
    }

    public void fecharBD() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

}
