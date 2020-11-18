package com.mg.CRUD;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Create extends SQLiteOpenHelper {
    private static final String NOME_DB = "MEU_DB";
    private static final int VERSAO_DB = 1;
    private static final String TABELA_PESSOA = "TABELA_PESSOA";

    private static final String PATH_DB = "/data/user/0/com.matutogamer/databases/MEU_DB";
    private Context mContext;
    private SQLiteDatabase db;

    public Create(Context context) {
        super(context, NOME_DB,null, VERSAO_DB);
        this.mContext=context;
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //logica pra atualizar o Banco de dados.
        String cadastro = "DROP TABLE IF EXISTS TABELA_PESSOAS";
        db.execSQL(cadastro);
    }

    public boolean createTable(){
        openDB();
        String createTable = "CREATE TABLE IF NOT EXISTS "+TABELA_PESSOA+"("
                +"NOME TEX NOT NULL,"
                +"CLAN TEXT NOT NULL,"
                +"SENHA TEXT NOT NULL,"
                +"EMAIL TEXT,"
                +"SCORE INTEGER,"
                +"MUSIC INTEGER)";
        try{
            db.execSQL(createTable);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    private void openDB(){
        if (!db.isOpen()){
            db = mContext.openOrCreateDatabase(PATH_DB,SQLiteDatabase.OPEN_READWRITE,null);
        }
    }
}