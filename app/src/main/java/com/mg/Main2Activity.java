package com.mg;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.mg.CRUD.Create;
import com.mg.CRUD.Read;
import com.mg.CRUD.Update;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private String clan;
    TextView txt1;
    TextView txt2;
    TextView txt3;
    TextView txt4;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Create c = new Create(getApplicationContext());
        c.createTable();

        txt1 = findViewById(R.id.txt_nome);
        txt2 = findViewById(R.id.txt_pass);
        txt3 = findViewById(R.id.txt_pass2);
        txt4 = findViewById(R.id.email);

        mp = MediaPlayer.create(Main2Activity.this, R.raw.btnsound);

        final TextView txt_status1 = findViewById(R.id.txt_status_1);
        final TextView txt_status2 = findViewById(R.id.txt_status_2);
        final TextView txt_atributo = findViewById(R.id.txt2);

        final ImageView img_ninja = findViewById(R.id.img_ninja);

        final Button btn_ok = findViewById(R.id.cadastrar);
        final Button btn_mandacaru = findViewById(R.id.mandacaru);
        final Button btn_cardeiro = findViewById(R.id.cardeiro);
        final Button btn_palma = findViewById(R.id.palma);


        final AlertDialog.Builder dlg = new AlertDialog.Builder(this);



        btn_mandacaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_mandacaru.setBackgroundResource(R.drawable.tela1_btn01_2);
                btn_cardeiro.setBackgroundResource(R.drawable.tela1_btn01_1);
                btn_palma.setBackgroundResource(R.drawable.tela1_btn01_1);
                txt_status2.setText("20\n25\n50\n80");
                txt_atributo.setText("Mágia+Agilidade");
                img_ninja.setBackgroundResource(R.drawable.shark_animation);
                AnimationDrawable anima = (AnimationDrawable) img_ninja.getBackground();
                anima.start();
                Animation desloca = new TranslateAnimation(450,0,0,0);
                desloca.setFillAfter(true);
                desloca.setDuration(1200);
                img_ninja.startAnimation(desloca);
                clan= "Mandacaru";
            }
        });
        btn_cardeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_cardeiro.setBackgroundResource(R.drawable.tela1_btn01_2);
                btn_mandacaru.setBackgroundResource(R.drawable.tela1_btn01_1);
                btn_palma.setBackgroundResource(R.drawable.tela1_btn01_1);
                txt_status2.setText("30\n20\n30\n100");
                txt_atributo.setText("Mágia+Vida");

                img_ninja.setBackgroundResource(R.drawable.matuto_animation_parado);
                AnimationDrawable anima = (AnimationDrawable) img_ninja.getBackground();
                anima.start();
                Animation desloca = new TranslateAnimation(450,0,0,0);
                desloca.setFillAfter(true);
                desloca.setDuration(1200);
                img_ninja.startAnimation(desloca);
                clan="Cardeiro";
            }
        });
        btn_palma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_palma.setBackgroundResource(R.drawable.tela1_btn01_2);
                btn_mandacaru.setBackgroundResource(R.drawable.tela1_btn01_1);
                btn_cardeiro.setBackgroundResource(R.drawable.tela1_btn01_1);
                txt_status2.setText("40\n30\n40\n90");
                txt_atributo.setText("Força+Agilidade");

                img_ninja.setBackgroundResource(R.drawable.cocada_animation);
                AnimationDrawable anima = (AnimationDrawable) img_ninja.getBackground();
                anima.start();
                Animation desloca = new TranslateAnimation(450,0,0,0);
                desloca.setFillAfter(true);
                desloca.setDuration(1200);
                img_ninja.startAnimation(desloca);
                clan="Cardeiro";
            }
        });


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Cadastro p = new Cadastro();
                p.setNick(txt1.getText().toString());
                validaCampo();
            }
        });


    }

    private void validaCampo(){
        final Intent intent = new Intent(this, MainActivity.class);
        boolean res = false;

        Cadastro p = new Cadastro();
        Update u = new Update(getApplicationContext());

        String nick = txt1.getText().toString();
        String senha =txt2.getText().toString();
        String senha2 =txt3.getText().toString();
        String email = txt4.getText().toString();


        if(res = isCampoVoid(nick)){
            txt1.requestFocus();
        }else
        if(res = isCampoVoid(senha)){
            txt2.requestFocus();
        }else
        if(res= isCampoVoid(senha2)){
            txt3.requestFocus();
        }else
        if(res =!senha.equals(senha2)){
            txt3.requestFocus();
        }else
        if(res= !isEmailValid(email)) {
            txt4.requestFocus();
        }else
        if(isValid(nick,email)==1){
            txt1.requestFocus();
        }else
        if(isValid(nick,email)==2){
            txt4.requestFocus();
        }
        if (res){
            Toast.makeText(getApplicationContext(), "Confira o campo.", Toast.LENGTH_SHORT).show();
        }else
        if(isValid(nick,email)==0){
            p.setNick(nick);
            p.setSenha(senha2);
            p.setEmail(email);
            p.setClan(clan);
            p.setSom(1);
            if(u.insertPessoa(p)){
                Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso.", Toast.LENGTH_SHORT).show();
                System.out.println(p.getNick()+"\n"+p.getSenha()+"\n"+p.getClan()+"\n"+p.getEmail()+p.getSom());
                startActivity(intent);
                finish();
            }
        }

    }

    private int isValid(String nick, String email){
        //Esse é o select do meu banco
        Read r = new Read(getApplicationContext());
        //...
        int t = 0;
        //Esse Array list vai procurar se já existe o nome no banco.
        ArrayList<Cadastro> cArray = r.getNick();
        //...
        //O for vai percorrer o banco procurando igualdade entre o que foi digitado com o que foi achado no banco.
        for (int i = 0; i < cArray.size(); i++) {

            Cadastro c = cArray.get(i);
            if (c.getNick().equals(nick)) {
                Toast.makeText(getApplicationContext(), c.getNick()+" já está sendo usado.", Toast.LENGTH_SHORT).show();
                t=1;
            }else if(c.getEmail().equals(email)){
                Toast.makeText(getApplicationContext(),c.getEmail()+" já está sendo usado.", Toast.LENGTH_SHORT).show();
                t=2;
            }else{
                t=0;
            }
        }
        //...
        return t;
    }

    private boolean isCampoVoid(String valor){
        boolean result = (TextUtils.isEmpty(valor)||valor.trim().isEmpty());
        return result;
    }

    private boolean isEmailValid(String email){
        boolean result = (!isCampoVoid(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return result;
    }

}
