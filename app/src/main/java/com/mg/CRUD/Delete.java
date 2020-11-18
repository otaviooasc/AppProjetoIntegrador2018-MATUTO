package com.mg.CRUD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mg.Cadastro;

public class Delete extends SQLiteOpenHelper {
    private static final String NOME_DB = "MEU_DB";
    private static final int VERSAO_DB = 1;
    private static final String TABELA_PESSOA = "TABELA_PESSOA";

    private static final String PATH_DB = "/data/user/0/com.mag/databases/MEU_DB";
    private Context mContext;
    private SQLiteDatabase db;

    public Delete(Context context) {
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
    }

    public boolean deleteTable(){
        openDB();
        String deletTable = "DROP TABLE IF EXISTS "+TABELA_PESSOA;

        try{
            db.execSQL(deletTable);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean deletePessoa(Cadastro p){
        openDB();
        String deletePessoa = "NOME = '"+p.getNick() +"'";

        try{
            db.delete(TABELA_PESSOA,deletePessoa,null);
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
