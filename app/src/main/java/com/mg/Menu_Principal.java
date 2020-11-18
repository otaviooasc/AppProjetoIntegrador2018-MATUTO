package com.mg;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.mg.CRUD.Create;
import com.mg.CRUD.Read;

import java.util.ArrayList;

public class Menu_Principal extends AppCompatActivity {
    private String userName;
    MediaPlayer mp;
    int sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        Create c = new Create(getApplicationContext());
        c.createTable();
        mp = MediaPlayer.create(Menu_Principal.this, R.raw.btnsound);

        final Intent intent_record = new Intent(this, Finish_Activity.class);
        final Intent intent_opc = new Intent (this, MainActivityOptions.class);
        final Intent intent_new_game = new Intent(this, New_Game.class);

        Button btn_exit = findViewById(R.id.btn_sair);
        Button btn_score = findViewById(R.id.btn_recordes);
        Button btn_options = findViewById(R.id.btn_opc);
        Button btn_new_game = findViewById(R.id.btn_jogar);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            setUserName(extras.getString("nome"));
        }else{
            System.out.println("Erro...Menu principal " + getUserName()+ userName);
        }

        btn_new_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teste();
                intent_new_game.putExtra("userName", getUserName());
                intent_new_game.putExtra("som", sound);
                startActivity(intent_new_game);
            }
        });

        btn_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teste();
                intent_opc.putExtra("name",getUserName());
                intent_opc.putExtra("som", sound);
                startActivity(intent_opc);
            }
        });
        btn_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teste();
                intent_record.putExtra("name",getUserName());
                intent_record.putExtra("som", sound);
                startActivity(intent_record);
            }
        });
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                existeapp();
            }
        });

    }
    private void existeapp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Menu_Principal.this);
        builder.setMessage("Qual foi seu desertor ?\nJá tá afim de vazar ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                teste();
                finishAffinity();

            }
        });

        builder.setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                teste();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void teste(){
        Read r = new Read(getApplicationContext());
        final ArrayList<Cadastro> cArray = r.getNick();

        for (int i = 0; i < cArray.size(); i++) {
            Cadastro o = cArray.get(i);
            Cadastro ca = new Cadastro();
            if(o.getNick().equals(getUserName())){
                ca.setNick(o.getNick());
                setUserName(ca.getNick());
                sound = o.getSom();
                System.out.println(o.getSom()+" for antes do teste."+ca.getNick()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                if(sound==1){
                    mp.start();
                }else{
                    mp.stop();
                }
            }else {
                System.out.println("ERRO MENU");
            }
        }

    }
}