package com.mg.CRUD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mg.Cadastro;
import java.util.ArrayList;
import java.util.List;

public class Read extends SQLiteOpenHelper {
    private static final String NOME_DB = "MEU_DB";
    private static final int VERSAO_DB = 1;
    private static final String TABELA_PESSOA = "TABELA_PESSOA";

    private static final String PATH_DB = "/data/user/0/com.matuto/databases/MEU_DB";
    private Context mContext;
    private SQLiteDatabase db;

    public Read(Context context) {
        super(context, NOME_DB,null, VERSAO_DB);
        this.mContext=context;
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //logica pra atualizar o Banco de dados.
    }

    public ArrayList<Cadastro> getNick(){
        openDB();
        ArrayList<Cadastro> pArray = new ArrayList<>();
        String getPessoas = "SELECT * FROM "+TABELA_PESSOA;

        try{
            Cursor c = db.rawQuery(getPessoas,null);

            if (c.moveToFirst()){
                do{
                    Cadastro p = new Cadastro();
                    p.setNick(c.getString(0));
                    p.setSenha(c.getString(2));
                    p.setClan(c.getString(1));
                    p.setEmail(c.getString(3));
                    p.setScore(c.getInt(4));
                    p.setSom(c.getInt(5));

                    pArray.add(p);
                }while(c.moveToNext());
                c.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }

        return pArray;
    }


    public ArrayList<Cadastro> getNickk(){
        openDB();
        ArrayList<Cadastro> pArray = new ArrayList<>();
        String[] colunas = new String[]{"CLAN","NICK","SCORE"};

        try{
            Cursor c = db.query("TABELA_PESSOA",colunas,null,null,null,null,"SCORE");

            if (c.moveToFirst()){
                do{
                    Cadastro p = new Cadastro();
                    p.setNick(c.getString(0));
                    p.setClan(c.getString(1));
                    p.setScore(c.getInt(4));
                    pArray.add(p);
                }while(c.moveToNext());
                c.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.close();
        }

        return pArray;
    }
    public ArrayList<Cadastro> search(){
        String[] colunas = new String[]{"CLAN","NOME","SCORE"};
        Cursor c = db.query("TABELA_PESSOA",colunas,null,null,null,null,"SCORE ASC");
        ArrayList<Cadastro> list = new ArrayList();

        while(c.moveToNext()){
            Cadastro cadastro = new Cadastro();
            cadastro.setClan(c.getString(0));
            cadastro.setNick(c.getString(1));
            cadastro.setScore(c.getInt(2));

            list.add(cadastro);
        }
        return list;
    }

    public List<Cadastro> exibirTodos() {
        List<Cadastro> usuario = new ArrayList<>();
        Cursor cursor = db.query("TABELA_PESSOA",new String[]{"CLAN","NOME","SCORE"},
                null,null,null,null,"SCORE DESC");
        while (cursor.moveToNext()) {
            Cadastro user = new Cadastro();
            user.setClan(cursor.getString(0));
            user.setNick(cursor.getString(1));
            user.setScore(cursor.getInt(2));

            usuario.add(user);
        }
        return usuario;
    }
    private void openDB(){
        if (!db.isOpen()){
            db = mContext.openOrCreateDatabase(PATH_DB,SQLiteDatabase.OPEN_READWRITE,null);
        }
    }

}