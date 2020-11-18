package com.mg;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mg.CRUD.Read;
import com.mg.CRUD.Update;

import java.util.ArrayList;

public class Recuperar_Senha2 extends AppCompatActivity {
    EditText camp1;
    EditText camp2;
    Button btn_s;
    String s_1;
    String s_2;
    String nick;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar__senha2);

        final Intent intent_login = new Intent(this,MainActivity.class);

        mp = MediaPlayer.create(Recuperar_Senha2.this, R.raw.btnsound);

        camp1 = findViewById(R.id.txt_pass);
        camp2 = findViewById(R.id.txt_pass2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            nick = (extras.getString("nome"));
        }else{
            System.out.println("Erro...Tela_2");
        }
        final Update u = new Update(getApplicationContext());
        Read r = new Read(getApplicationContext());
        final ArrayList<Cadastro> cArray = r.getNick();

        btn_s = findViewById(R.id.s);
        btn_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s_1 = camp1.getText().toString();
                s_2 = camp2.getText().toString();
                mp.start();
                if(!s_1.equals("") && !s_2.equals("")){
                    if(s_1.equals(s_2)){
                        for (int i = 0; i < cArray.size(); i++) {
                            Cadastro c = cArray.get(i);
                            if(c.getNick().equals(nick)){
                                c.setSenha(s_2);
                                u.updatePessoa(c);
                                Toast.makeText(getApplicationContext(),"Troca realizada com sucesso "+nick+"! :D",Toast.LENGTH_SHORT).show();
                                startActivity(intent_login);
                                finish();
                            }else{
                                System.out.println("ERRO AO TROCAR DE SENHA.");
                            }
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Os campos estão diferentes, ajeita sapeste ai fi de mãe!",Toast.LENGTH_SHORT).show();
                        camp1.requestFocus();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Preencha os campos caba da peste! >:(",Toast.LENGTH_SHORT).show();
                    camp1.requestFocus();
                }
            }
        });
    }
}
