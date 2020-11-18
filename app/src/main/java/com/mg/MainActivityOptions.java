package com.mg;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mg.CRUD.Delete;
import com.mg.CRUD.Read;
import com.mg.CRUD.Update;

import java.util.ArrayList;

public class MainActivityOptions extends AppCompatActivity {
    int sound;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_options);
        final Intent intent = new Intent(this,Recuperar_Senha.class);
        final Intent intent_menu = new Intent(this, Menu_Principal.class);
        final TextView txt=findViewById(R.id.txtSom);

        Bundle extras = getIntent().getExtras();
        ImageButton img_btn = findViewById(R.id.img_btn);
        if (extras != null) {
            name = extras.getString("name");
        } else {
            System.out.println("ERRO >>> NOME Activity OPTION");

        }

        if (extras != null) {
            sound = extras.getInt("som");
            System.out.println(sound + " PEGOU TELA OPTION");
        } else {
            System.out.println("Erro ... Som");
        }

        if(sound==0) {
            txt.setBackgroundColor(Color.parseColor("#FF0000"));
            txt.setText("DESLIGADO");
            sound=0;
        }else{
            txt.setBackgroundColor(Color.parseColor("#4AFF02"));
            txt.setText("LIGADO");
            sound=1;
        }

        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent_menu.putExtra("nome",name);
                startActivity(intent_menu);
                finish();
            }
        });


        Button btn_mudar = findViewById(R.id.btn1);
        btn_mudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        Button btn_som = findViewById(R.id.btn2);
        btn_som.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt.getText().equals("LIGADO")) {
                    txt.setBackgroundColor(Color.parseColor("#FF0000"));
                    txt.setText("DESLIGADO");
                    sound=0;

                    Delete d = new Delete(getApplicationContext());
                    test(sound);
                    //d.deleteTable();
                }else{
                    txt.setBackgroundColor(Color.parseColor("#4AFF02"));
                    txt.setText("LIGADO");
                    sound=1;
                    test(sound);
                }

            }
        });
    }
    public int test(int j){
        final Update u = new Update(getApplicationContext());
        Read r = new Read(getApplicationContext());
        final ArrayList<Cadastro> cArray = r.getNick();

        Cadastro ca = new Cadastro();

        for (int i = 0; i < cArray.size(); i++) {
            Cadastro c = cArray.get(i);
            if(c.getNick().equals(name)){

                c.setSom(sound);
                ca.setSom(c.getSom());

                ca.setNick(c.getNick());
                name=(ca.getNick());

                System.out.println(c.getSom()+" "+sound+" for test(j)");
                u.musica(c);
            }else{
                System.out.println("ERRO AO TROCAR DE SOM.2 "+name);
            }
        }
        return sound;
    }
}
