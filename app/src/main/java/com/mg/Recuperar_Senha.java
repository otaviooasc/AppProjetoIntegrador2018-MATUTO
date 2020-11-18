package com.mg;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mg.CRUD.Read;

import java.util.ArrayList;

public class Recuperar_Senha extends AppCompatActivity {
    TextView txt1;
    TextView txt2;
    Button btn;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar__senha);

        mp = MediaPlayer.create(Recuperar_Senha.this, R.raw.btnsound);

        btn =findViewById(R.id.cadastrar);

        txt1 = findViewById(R.id.txt_nome);
        txt2 = findViewById(R.id.email);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp.start();
                validaCamp();

            }
        });
    }

    private void validaCamp(){
        boolean res = false;
        int errado= 0;
        Intent intent = new Intent(this,Recuperar_Senha2.class);
        Read r = new Read(getApplicationContext());
        ArrayList<Cadastro> cArray = r.getNick();
        String nick = txt1.getText().toString();
        String email = txt2.getText().toString();

        if(res = isCampoVoid(nick)){
            txt1.requestFocus();
        }else if (res = isCampoVoid(email)){
            txt2.requestFocus();
        }else if ((res = isCampoVoid(nick)) && (res = isCampoVoid(email))) {
            txt1.requestFocus();
            txt2.requestFocus();
        }else{
            //Toast.makeText(getApplicationContext(),"PASSOU",Toast.LENGTH_SHORT).show();
        }
        for (int i = 0; i < cArray.size(); i++) {
            Cadastro c = cArray.get(i);
            if(nick.equals(c.getNick())){
                System.out.println("1");
                if(email.equals((c.getEmail()))){
                    intent.putExtra("nome",nick);
                    errado=2;
                    Toast.makeText(getApplicationContext(),"Verificação de dados realizada com sucesso.",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"O email está errado.",Toast.LENGTH_SHORT).show();
                    txt2.requestFocus();
                    break;
                }
            }else if (errado==0){
                errado=1;
            }
        }
        if (errado ==1) {
            Toast.makeText(getApplicationContext(), "O nome está errado.", Toast.LENGTH_SHORT).show();
            txt1.requestFocus();
        }
    }

    private boolean isCampoVoid(String valor){
        boolean result = (TextUtils.isEmpty(valor)||valor.trim().isEmpty());
        return result;
    }
}