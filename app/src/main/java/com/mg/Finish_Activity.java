package com.mg;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mg.CRUD.Create;
import com.mg.CRUD.Read;

import java.util.ArrayList;
import java.util.List;

public class Finish_Activity extends AppCompatActivity {

    ListView lista;
    Read r;
    Cadastro cadastro;
    int pt;
    private String nickUser;
    int re = 0;

    private List<Cadastro> usuario; //array para listar os usuarios do banco
    private  List<Cadastro> usuarioFiltros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Create c = new Create(getApplicationContext());
        c.createTable();
        Bundle extras = getIntent().getExtras();
        Read read = new Read(getApplicationContext());
        ArrayList<Cadastro> cArray = read.getNick();

        TextView txt_name= findViewById(R.id.txt_score2);
        TextView txt_score= findViewById(R.id.txt_score3);
        ImageView img = findViewById(R.id.img_score2);

        final Intent intent= new Intent(this,Menu_Principal.class);

        if (extras != null) {
            setNickUser(extras.getString("name"));
        }else{
            System.out.println("Erro...Tela_FINISH");
        }
        if(extras !=null){
            pt =(extras.getInt("ninjas"));
        }else{
            System.out.println("Erro...Tela_FINISH");
        }
        if(extras !=null){
            re =(extras.getInt("r"));
        }else{
            System.out.println("Erro...Tela_FINISH");
        }


        for (int i = 0; i < cArray.size(); i++) {
            Cadastro cadastro2 = cArray.get(i);

            if (cadastro2.getNick().equals(getNickUser())) {
                if(re == 0) {
                    if (cadastro2.getClan().equals("Cardeiro")) {
                        img.setBackgroundResource(R.drawable.matuto_animation_parado);
                        AnimationDrawable anima = (AnimationDrawable) img.getBackground();
                        anima.start();
                    } else if (cadastro2.getClan().equals("Mandacaru")) {
                        img.setBackgroundResource(R.drawable.shark_animation);
                        AnimationDrawable anima = (AnimationDrawable) img.getBackground();
                        anima.start();
                    } else if (cadastro2.getClan().equals("Palma")) {
                        img.setBackgroundResource(R.drawable.cocada_animation);
                        AnimationDrawable anima = (AnimationDrawable) img.getBackground();
                        anima.start();
                    } else {
                        System.out.println("ERRO NA ANIMAÇÃO " + cadastro2.getClan());
                    }

                    txt_name.setText(cadastro2.getClan() + " " + cadastro2.getNick());
                    txt_score.setText("Sua pontuação: "+cadastro2.getScore());
                }else if(re==1){
                    if (cadastro2.getClan().equals("Cardeiro")) {
                        img.setBackgroundResource(R.drawable.matuto_animation_parado);
                        AnimationDrawable anima = (AnimationDrawable) img.getBackground();
                        anima.start();
                    } else if (cadastro2.getClan().equals("Mandacaru")) {
                        img.setBackgroundResource(R.drawable.shark_animation);
                        AnimationDrawable anima = (AnimationDrawable) img.getBackground();
                        anima.start();
                    } else if (cadastro2.getClan().equals("Palma")) {
                        img.setBackgroundResource(R.drawable.cocada_animation);
                        AnimationDrawable anima = (AnimationDrawable) img.getBackground();
                        anima.start();
                    } else {
                        System.out.println("ERRO NA ANIMAÇÃO " + cadastro2.getClan());
                    }
                    txt_name.setText(cadastro2.getClan() + " " + cadastro2.getNick());
                    txt_score.setText("Sua pontuação: "+pt);
                }
            }else{
                System.out.println(getNickUser());
            }
        }

        ImageButton img_btn = findViewById(R.id.img_btn);
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("nome",getNickUser());
                startActivity(intent);
                finish();
            }
        });


        cadastro= new Cadastro();
        lista = findViewById(R.id.lista);
        r= new Read(this);

        // Mostrar na activity os usuarios
        usuario = r.exibirTodos(); //chama o metodo para exibir
        usuarioFiltros.addAll(usuario);

        // inserindo a lista no ListView//
        //ArrayAdapter<Cadastro> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, usuarioFiltros);
        AdapterRanking adapter = new AdapterRanking(usuarioFiltros, this);
        lista.setAdapter(adapter);

    }
    @Override
    public  void onResume(){
        super.onResume();
        usuario =r.exibirTodos();
        usuarioFiltros.clear();
        usuarioFiltros.addAll(usuario);
        lista.invalidateViews();
    }

    public String getNickUser() {
        return nickUser;
    }

    public void setNickUser(String nickUser) {
        this.nickUser = nickUser;
    }
}