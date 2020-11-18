package com.mg;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterRanking extends BaseAdapter {
    private List<Cadastro> c;
    private final Activity act;

    int i = 0;

    public AdapterRanking(List<Cadastro> lutadores, Activity act) {
        this.c = lutadores;
        this.act = act;
    }
    @Override
    public int getCount() {
        return c.size();
    }

    @Override
    public Object getItem(int position) {
        return c.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = act.getLayoutInflater() .inflate(R.layout.lista_cadastro_personalizado, parent, false);


        TextView position_score =view.findViewById(R.id.position);
        TextView nome = view.findViewById(R.id.clan_name);
        TextView score= view.findViewById(R.id.score);
        ImageView img = view.findViewById(R.id.lista_curso_personalizada_imagem);

        Cadastro ca = c.get(position);
        if(ca.getClan().equals("Cardeiro")) {
            img.setBackgroundResource(R.drawable.matuto_animation_parado);
            AnimationDrawable anima = (AnimationDrawable) img.getBackground();
            anima.start();
        }else if (ca.getClan().equals("Mandacaru")){
            img.setBackgroundResource(R.drawable.shark_animation);
            AnimationDrawable anima = (AnimationDrawable) img.getBackground();
            anima.start();
        }else if(ca.getClan().equals("Palma")){
            img.setBackgroundResource(R.drawable.cocada_animation);
            AnimationDrawable anima = (AnimationDrawable) img.getBackground();
            anima.start();
        }else{
            System.out.println("ERRO NA ANIMAÇÃO "+ca.getClan());
        }

        if(position == 0){
            position_score.setText(Html.fromHtml("<font color=#FF4500>" + (position + 1)+"</font>"));
        }else if(position == 1) {
            position_score.setText(Html.fromHtml("<font color=#FF8C00>" + (position + 1)+"</font>"));
        }else if(position == 2) {
            position_score.setText(Html.fromHtml("<font color=#FFA500>" + (position + 1)+"</font>"));
        }else{
            position_score.setText("" + (position + 1));
        }

        nome.setText(ca.getClan() + " \n" + ca.getNick());
        score.setText("Score :" + ca.getScore());
        return view;
    }

    //Metodo atualizar Lista
    public void atualizar(List<Cadastro> listUsuarios){
        this.c.clear();
        this.c = listUsuarios;
        this.notifyDataSetChanged();
    }
}