package com.mg.CRUD;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mg.Cadastro;


public class Update extends SQLiteOpenHelper {
    private static final String NOME_DB = "MEU_DB";
    private static final int VERSAO_DB = 1;
    private static final String TABELA_PESSOA = "TABELA_PESSOA";

    private static final String PATH_DB = "/data/user/0/com.matutogamer/databases/MEU_DB";
    private Context mContext;
    private SQLiteDatabase db;

    public Update(Context context) {
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

    public boolean insertPessoa(Cadastro p){
        openDB();
        try{
            ContentValues cv = new ContentValues();
            cv.put("NOME ",p.getNick());
            cv.put("CLAN ",p.getClan());
            cv.put("SENHA ",p.getSenha());
            cv.put("EMAIL ",p.getEmail());
            cv.put("MUSIC",p.getSom());

            db.insert(TABELA_PESSOA,null,cv);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }
    public boolean insertNinja(Cadastro p){
        openDB();
        try{
            String where = "NOME = '"+p.getNick()+"'";
            ContentValues cv = new ContentValues();
            cv.put("SCORE",p.getScore());
            db.update(TABELA_PESSOA,cv,where,null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean updatePessoa(Cadastro p){
        openDB();
        try{
            String where = "NOME = '"+p.getNick()+"'";
            ContentValues cv = new ContentValues();
            cv.put("SENHA",p.getSenha());
            db.update(TABELA_PESSOA,cv,where,null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    public boolean musica(Cadastro p){
        openDB();
        try{
            String where = "NOME = '"+p.getNick()+"'";
            ContentValues cv = new ContentValues();
            cv.put("MUSIC",p.getSom());
            db.update(TABELA_PESSOA,cv,where,null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            db.close();
        }
    }

    @SuppressLint("WrongConstant")
    private void openDB(){
        if (!db.isOpen()){
            db = mContext.openOrCreateDatabase(PATH_DB,SQLiteDatabase.OPEN_READWRITE,null);
        }
    }
}