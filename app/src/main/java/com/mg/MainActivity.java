package com.mg;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mg.CRUD.Read;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String nick;
    TextView txt_cadastro;
    TextView txt_recuperar;

    Intent intent_register;
    Intent intent_main_menu;
    Intent intent_recuperar;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView txt_nome = findViewById(R.id.txt_nome);
        final TextView txt_senha = findViewById(R.id.txt_pass);
        txt_cadastro = findViewById(R.id.txt_cadastro);
        txt_recuperar = findViewById(R.id.txt_recuperar);

        mp = MediaPlayer.create(MainActivity.this, R.raw.btnsound);

        intent_main_menu = new Intent(this, Menu_Principal.class);
        intent_register = new Intent(this, Main2Activity.class);
        intent_recuperar = new Intent(this, Recuperar_Senha.class);

        txt_cadastro.setText(Html.fromHtml("Ainda não tem uma conta ? <font color=#0023FF><u>Registrar</u></font>"));
        txt_recuperar.setText(Html.fromHtml("Esqueceu a senha ? <font color=#0023FF><u>Recuperar</u></font>"));

        Button btn1 = findViewById(R.id.btn_login);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNick(txt_nome.getText().toString());
                String senha= txt_senha.getText().toString();
                int result = 0;

                mp.start();

                Read r = new Read(getApplicationContext());
                ArrayList<Cadastro> cArray = r.getNick();

                for (int i = 0; i < cArray.size(); i++) {
                    Cadastro c = cArray.get(i);
                    if (c.getNick().equals(getNick()) && c.getSenha().equals(senha)) {
                        Toast.makeText(getApplicationContext(), "Opa meu consagrado!", Toast.LENGTH_SHORT).show();
                        System.out.println("<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>");
                        System.out.println(c.getNick());
                        System.out.println(c.getSom());
                        System.out.println(c.getClan());
                        System.out.println(c.getEmail());
                        System.out.println(c.getSenha());
                        System.out.println("<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>");
                        intent_main_menu.putExtra("nome",getNick());
                        intent_main_menu.putExtra("sound",(c.getSom()));
                        startActivity(intent_main_menu);
                        finish();
                        result=1;
                    }else if (c.getNick().equals(getNick())&& !c.getSenha().equals(senha)){
                        Toast.makeText(getApplicationContext(), "Bora meu fi, coloque a senha certa.", Toast.LENGTH_SHORT).show();
                        result = 1;
                    }
                }

                if(result==0){
                    Toast.makeText(getApplicationContext(), "Tá errando o nome caba ou não fez o cadastro ainda ?", Toast.LENGTH_LONG).show();
                }
                if (result==2) {
                    Toast.makeText(getApplicationContext(), "Tem algo errado, por favor confira os campos.", Toast.LENGTH_LONG).show();
                    result=0;
                }
            }
        });
        txt_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_register);
            }
        });
        txt_recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent_recuperar);
            }
        });

    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
