package com.mg;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mg.CRUD.Create;
import com.mg.CRUD.Read;
import com.mg.CRUD.Update;

import java.util.ArrayList;
import java.util.Random;

public class New_Game extends AppCompatActivity {
    private int o=0;
    private int l;
    private int first = 0;
    private int rotatesGiven = 0;
    //Number of Cangaceiro regade;
    private int cr=0;
    //...
    private int a=0;
    //dano of attack player
    private int danoNPC;
    private int danoPlayer;
    //...
    private String aaPlayer;
    private String nick;
    private String clan;

    private int attU;
    private int ad;
    private int ap;
    private int f;
    private int hp;
    private int hpNPC;
    private int u;
    private int dado=0;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_new__game);
        Create c = new Create(getApplicationContext());
        c.createTable();

        final ImageButton imgBtn = findViewById(R.id.imgBtn);
        final Intent intent_record = new Intent (this, Finish_Activity.class);
        final TextView txt_battle = findViewById(R.id.txt_battle);
        final TextView txt_ataques = findViewById(R.id.txt_ataques);

        final Button btn_ataque1= findViewById(R.id.btn_51);
        final Button btn_ataque2= findViewById(R.id.btn_52);
        final Button btn_ataque3= findViewById(R.id.btn_53);
        final Button btn_ataque4= findViewById(R.id.btn_54);
        Button btn_ok = findViewById(R.id.btn_ok);
        Button btn_sair = findViewById(R.id.btn_op);

        //Hp User and HP NPC
        final TextView txt_hpNPC = findViewById(R.id.hpNPC);
        Animation desloca = new TranslateAnimation(800,0,0,0);
        desloca.setFillAfter(true);
        desloca.setDuration(1200);
        txt_hpNPC.startAnimation(desloca);

        final TextView txt_hpU = findViewById(R.id.hpU);
        Animation desloca1 = new TranslateAnimation(-800,0,0,0);
        desloca1.setFillAfter(true);
        desloca1.setDuration(1200);
        txt_hpU.startAnimation(desloca1);
        //********

        //TextView of Battle
        txt_battle.setText("Olá caba da peste, este é um jogo de sobrevivencia onde você tem que matar o maior número de cangaceiros renegados. \n\nCutuque \"Ok\" para eliminar os cabroco fia de uma de peste.");
        botao();
        //Focus****

        //Receives the name and distributes the attributes
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            setNick(extras.getString("userName"));
        }else{
            System.out.println("Erro...");
        }

        Read r = new Read(getApplicationContext());
        final ArrayList<Cadastro> cArray = r.getNick();

        for (int i = 0; i < cArray.size(); i++) {
            Cadastro ca = cArray.get(i);
            if (ca.getNick().equals(getNick())) {
                setClan(ca.getClan());
                Stt stt = new Stt(getClan());
                setAd(stt.getAd());
                setAp(stt.getAp());
                setF(stt.getSom2());
                setHp(stt.getHp());
                setHpNPC(stt.getHpNPC());
                System.out.println(getNick());
                break;
            }else{
                System.out.println("ERRO no for da TELA 5");
            }
        }
        //************

        txt_hpU.setText(mostrarHP());
        txt_hpNPC.setText(mostrarHpNPC());


        btn_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(New_Game.this, R.raw.btnsound);
                mp.start();
                existMenu();
            }
        });
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(New_Game.this, R.raw.btnsound);
                mp.start();
                if (first == 1 && getAttU()!=0 && a==0 && dado==0){
                    givenAttack();
                    imgBtn.setClickable(false);
                    dado=1;
                }else if (dado==1 && a == 1){
                    givenDefense();
                    imgBtn.setClickable(false);
                    dado=0;
                }
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(New_Game.this, R.raw.btnok);
                txt_hpU.setText(mostrarHP());
                txt_hpNPC.setText(mostrarHpNPC());
                mp.start();
                if (first==0){
                    first=1;
                    botao();
                    txt_battle.setText("Escolha uma habilidade e aperte OK.");
                }else
                    if (first == 1 && a==0 && getAttU()==0 && getHp()>0){
                        txt_battle.setText("Escolha uma habilidade e aperte OK.");
                        btn_ataque1.setClickable(true);
                        btn_ataque2.setClickable(true);
                        btn_ataque3.setClickable(true);
                        btn_ataque4.setClickable(true);

                    }else
                        if (first==1 && a==0 && getAttU()!=0){
                            if (dado==1) {
                                txt_battle.setText(battle());
                                a = 1;
                                System.out.println("L173");
                                setAttU(0);
                                System.out.println(getAttU());
                                System.out.println(getDanoPlayer()+getRotatesGiven());
                            }else
                                if (dado==0){
                                txt_battle.setText("Clique no dado para saber se vai acertar ou errar o ataque. Logo depois aperte OK.");
                                imgBtn.setClickable(true);
                                btn_ataque1.setClickable(false);
                                btn_ataque2.setClickable(false);
                                btn_ataque3.setClickable(false);
                                btn_ataque4.setClickable(false);
                                txt_ataques.setText(null);
                            }
                        }else
                            if(first==1 && a==1){
                                if (dado == 0) {
                                    txt_battle.setText(attNPC()
                                            + "\n\nTurno de ataque."
                                            + "\nEscolha uma habilidade para revidar.");
                                    setHp(getHp()-getDanoNPC()+getRotatesGiven());
                                    btn_ataque1.setClickable(true);
                                    btn_ataque2.setClickable(true);
                                    btn_ataque3.setClickable(true);
                                    btn_ataque4.setClickable(true);
                                    a=0;
                                }else
                                    if(getHpNPC()<=0 && dado ==1) {
                                    dado=0;
                                    a = 0;
                                    txt_battle.setText(attNPC());

                                }else{
                                    txt_battle.setText("Gire o dado e clique Ok, para ver se você consegui desviar completamente ou parcialmente do dano.");
                                    imgBtn.setClickable(true);
                                    btn_ataque1.setClickable(false);
                                    btn_ataque2.setClickable(false);
                                    btn_ataque3.setClickable(false);
                                    btn_ataque4.setClickable(false);
                                    txt_ataques.setText(null);
                                }
                                txt_ataques.setText(null);
                                setAttU(0);
                        }else
                            if (getHp()<=0){
                                setHp(0);
                                txt_battle.setText("Você foi eliminado boa tentativa. Clique em OK.");
                                Update u = new Update((getApplicationContext()));

                                for (int i = 0; i < cArray.size(); i++) {
                                    Cadastro ca = cArray.get(i);
                                    if (ca.getNick().equals(getNick())) {
                                        if(ca.getScore()<=cr) {
                                            ca.setScore(cr);
                                            u.insertNinja(ca);
                                            intent_record.putExtra("ninjas",(cr));
                                            intent_record.putExtra("name",getNick());
                                            intent_record.putExtra("r",(1));
                                            startActivity(intent_record);
                                            alert("Você foi eliminado. Sua pontuação foi de :"+cr);
                                        }else{
                                            intent_record.putExtra("ninjas",(cr));
                                            intent_record.putExtra("name",getNick());
                                            intent_record.putExtra("r",(1));
                                            startActivity(intent_record);
                                            alert("Você foi eliminado. Sua pontuação foi de :"+cr);
                                        }
                                    }else{
                                        System.out.println("ERRO no for da TELA 5");
                                    }
                                }
                                finish();
                            }
            }
        });

        btn_ataque1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(New_Game.this, R.raw.btnaa);
                mp.start();
                if (first==1){
                    desloca_txt();
                    txt_ataques.setText("Ataque 1-Murro no pal da venta."
                            +"\n\nDano: 7"
                            +"\nMp: 0");
                    setAttU(1);
                    imgBtn.setVisibility(View.VISIBLE);
                    setDanoPlayer(7+cr);
                }
            }
        });

        btn_ataque2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(New_Game.this, R.raw.btnaa);
                mp.start();
                if (first==1 && getAp()>=1){
                    desloca_txt();
                    txt_ataques.setText("Ataque 2-Vuadora na boca do estomago."
                            +"\nDano: 14"
                            +"\nMp: 1");

                    setAttU(2);
                    imgBtn.setVisibility(View.VISIBLE);
                    setDanoPlayer(14+cr);
                }else{
                    txt_ataques.setText("Vc não tem mana suficiente para esse ataque.");
                    setAttU(5);
                    dado=1;
                    setDanoPlayer(0);
                }
            }
        });

        btn_ataque3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(New_Game.this, R.raw.btnaa);
                mp.start();
                if (first==1 && getAp()>=3){
                    desloca_txt();
                    txt_ataques.setText("Ataque 3-Tiro de BACAMARTE."
                            +"\n\nDano: 21"
                            +"\nMp: 2");
                    setAttU(3);
                    imgBtn.setVisibility(View.VISIBLE);
                    setDanoPlayer(21+cr);
                }else{
                    txt_ataques.setText("Vc não tem mana suficiente para esse ataque.");
                    setAttU(5);
                    dado=1;
                    setDanoPlayer(0);
                }
            }
        });

        btn_ataque4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(New_Game.this, R.raw.btnaa);
                mp.start();
                if (first==1 && getAp()>=5){
                    desloca_txt();
                    txt_ataques.setText("Ataque 4-Armadilha do cangaço. Ahhhhhhhhhhhhhhhhhhh"
                            +"\nDano: 31"
                            +"\nMp: 5");
                    setAttU(4);
                    imgBtn.setVisibility(View.VISIBLE);
                    setDanoPlayer(31+cr);
                }else{
                    txt_ataques.setText("Vc não tem mana suficiente para esse ataque.");
                    setAttU(5);
                    dado=1;
                    setDanoPlayer(0);
                }
            }
        });
    }
    public String mostrarHP(){
        Cadastro c = new Cadastro();
        return   "- "+getNick()+" "+getClan()
                +"\n\n- HP:"+getHp()+"\n"
                +"- Mana: "+getAp();
    }
    public String mostrarHpNPC(){
        return   "-HP Ninja inimigo. \n\n"
                +"-Número:"+(cr+1)
                +"\n-HP:"+getHpNPC();
    }

    public String battle(){
        damageUs();
        if(getHp()>0){
            switch(getAttU()){
                case 1:
                    if(l==1){
                        aaPlayer =(getNick()+" tentou dar um Murro no pal da venta do inimigo."+
                                "\nCausou : "+(getRotatesGiven())+" de dano." +
                                "\n\nClique Ok.");
                        break;
                    }else
                    if(l==6){
                        aaPlayer =(getNick()+" deu um super murro.\nO inimigo ficou atordoado e com isso perdeu mais vida." +
                                "\nCausou : "+(getDanoPlayer()+getRotatesGiven())+" de dano." +
                                "\n\nClique Ok.");
                        break;
                    }else {
                        aaPlayer = getNick()+" deu um Murro brabo no pal da venta do infiliz." +
                                "\nCausou : "+(getDanoPlayer()+getRotatesGiven())+" de dano."+
                                "\n\nClique Ok.";
                        break;
                    }
                case 2:
                    setAp(getAp()-1);
                    if(l==1){
                        aaPlayer = getNick()+" tentou dar uma Voadora no cabroco só q acabou errando." +
                                "\nCausou : "+(getRotatesGiven())+" de dano." +
                                "\n\nClique Ok.";
                        break;
                    }else
                    if(l==6){
                        aaPlayer = getNick()+" deu uma Vuadora da peste na boca que o cabroco devolveu o almoço da semana passada." +
                                "\nCausou : "+(getDanoPlayer()+getRotatesGiven())+" de dano." +
                                "\n\nClique Ok.";
                        break;
                    }else {
                        aaPlayer = getNick()+" deu uma Voadora na boca do estomago do cabroco." +
                                "\nCausou : "+(getDanoPlayer()+getRotatesGiven())+" de dano."+
                                "\n\nClique Ok.";
                        break;
                    }
                case 3:
                    setAp(getAp()-3);
                    if(l==1){
                        aaPlayer = getNick()+" tentou dar um tiro no caba, só que esqueceu a mira em casa e errou o tiro."+
                                "\nO cangaceiro do mal entra em choque." +
                                "\nCausou : "+(getRotatesGiven())+" de dano." +
                                "\n\nClique Ok.";
                        break;
                    }else
                    if(l==6){
                        aaPlayer = getNick()+" deu um tiro no caba que ele ficou vazando feito vasilha furada." +
                                "\nCausou : "+(getDanoPlayer()+getRotatesGiven())+" de dano." +
                                "\n\nClique Ok.";
                        break;
                    }else {
                        aaPlayer = getNick()+" deu um tiro no caba." +
                                "\nCausou : "+(getDanoPlayer()+getRotatesGiven())+" de dano."+
                                "\n\nClique Ok.";
                        break;
                    }
                case 4:
                    setAp(getAp()-5);
                    if(l==1){
                        aaPlayer = getNick()+" fingiu que iria prum lado e acabou ficando no mesmo lugar, errou o golpe."+
                                "\nCausou : "+(getRotatesGiven())+" de dano." +
                                "\n\nClique Ok.";
                        break;
                    }else
                    if(l==6){
                        aaPlayer = getNick()+" fingiu que iria prum lado e foi pro outro e deu-li uma peixeirada no bucho."+
                                "\nCausou : "+(getDanoPlayer()+getRotatesGiven())+" de dano." +
                                "\n\nClique Ok.";
                        break;
                    }else {
                        aaPlayer = getNick()+" fingiu que iria prum lado e foi pro outro e deu-li uma peixeirada." +
                                "\nCausou : "+(getDanoPlayer()+getRotatesGiven())+" de dano."+
                                "\n\nClique Ok.";
                        break;
                    }
                case 5:
                    aaPlayer = getNick()+" passou a vez por falta de mana.\nClique OK para continuar.";
                    break;
            }

            if(getAp()<=0){
                setAp(0);
            }
        }

        if (getHpNPC()<0){
            setHpNPC(0);
        }
        return  aaPlayer;
    }

    public String attNPC(){
        damage();
        String npc = null;
        final TextView txt_hpNPC = findViewById(R.id.hpNPC);
        if(getHpNPC()>0 && getHp()>0){
            switch (ataqueComputer()){
                case 1 :
                    setDanoNPC(10);
                    damage();
                    if (l == 1) {
                        npc = "Cangaceiro do mal deu um murro na sua testa.\nEssa habilidade tirou "+getDanoNPC()+" do seu HP." +
                                "\nQue azar da peste você levou o golpe e não conseguiu se defender.";
                    }else
                    if(l==6){
                        npc = "Cangaceiro do mal deu um murro na sua testa.\nEssa habilidade tirou "+getDanoNPC()+" do seu HP." +
                                "\nHoje é o seu dia de sorte bicho brabo esquivou";
                    }else{
                        npc = "Cangaceiro do mal deu um murro na sua testa.\nEssa habilidade tirou "+getDanoNPC()+" do seu HP." +
                                "\nVocê conseguiu defender reduzindo "+getRotatesGiven()+" do dano.";
                    }

                    break;
                case 2 :
                    setDanoNPC(13);
                    damage();
                    if (l == 1) {
                        npc = "Cangaceiro do mal deu um chute na costela.\nEssa habilidade tirou "+getDanoNPC()+" do seu HP." +
                                "\nQue azar da peste você levou o golpe e não conseguiu se defender.";
                    }else
                    if(l==6){
                        npc = "Cangaceiro do mal deu um chute na costela.\nEssa habilidade tirou "+getDanoNPC()+" do seu HP." +
                                "\nHoje é o seu dia de sorte bicho brabo esquivou";
                    }else{
                        npc = "Cangaceiro do mal deu um chute na costela.\nEssa habilidade tirou "+getDanoNPC()+" do seu HP." +
                                "\nVocê conseguiu defender reduzindo "+getRotatesGiven()+" do dano.";
                    }

                    break;
                case 3 :
                    setDanoNPC(16);
                    damage();
                    if (l == 1) {
                        npc = "Cangaceiro do mal aplicou um chute nos quartus.\nEssa habilidade tirou "+getDanoNPC()+" do seu HP." +
                                "\nQue azar da peste você levou o golpe e não conseguiu se defender.";
                    }else
                    if(l==6){
                        npc = "Cangaceiro do mal aplicou um chute nos quartus.\nEssa habilidade tirou "+getDanoNPC()+" do seu HP." +
                                "\nHoje é o seu dia de sorte bicho brabo esquivou";
                    }else{
                        npc = "Cangaceiro do mal aplicou um chute nos quartus.\nEssa habilidade tirou "+getDanoNPC()+" do seu HP." +
                                "\nVocê conseguiu defender "+getRotatesGiven()+" do dano.";
                    }
                    break;
                case 4 :
                    setDanoNPC(19);
                    damage();
                    if (l == 1) {
                        npc = "Cangaceiro do mal aplicou um chute nos quartus.\nEssa habilidade tirou "+getDanoNPC()+" do seu HP." +
                                "\nQue azar da peste você levou o golpe e não conseguiu se defender.";
                    }else
                    if(l==6){
                        npc = "Cangaceiro do mal aplicou um chute nos quartus.\nEssa habilidade tirou "+getDanoNPC()+" do seu HP." +
                                "\nHoje é o seu dia de sorte bicho brabo esquivou";
                    }else{
                        npc = "Cangaceiro do mal aplicou um chute nos quartus.\nEssa habilidade tirou "+getDanoNPC()+" do seu HP." +
                                "\nVocê conseguiu defender "+getRotatesGiven()+" do dano.";
                    }
                    break;
                default:
                    break;
            }
        }else
            if(getHp()>0&& cr%5==0) {

                setHp(getHp() + 5);

                if (getHp() > 170) {

                    setHp(170);
                }
            }else
                if(cr%2==0){

                    setAp(getAp()+1);

                }else
                    if(getHpNPC()>0){
                        botao();
                        return  npc+"\nCutuque OK para continuar.";
                    }

        if(getHpNPC()<=0){
            setHpNPC(10+(cr*cr));
            o=getHpNPC();
            cr++;
            npc=(   "Você é um caba da peste mesmo derrotou um Cangaceiro renegado.\n"
                    +"Outro Cangaceiro fi de uma peste encontrado.\n"
                    +"\nCutuque OK para continuar.");

            Animation desloca = new TranslateAnimation(800,0,0,0);
            desloca.setFillAfter(true);
            desloca.setDuration(1200);
            txt_hpNPC.startAnimation(desloca);
            botao();
        }
        return npc;
    }

    static int ataqueComputer (){
        Random gerador = new Random();
        return gerador.nextInt(4)+1; //Retorna um número entre 1 e 3.
    }

    private void damage(){
        if(dado==0) {
            if (l == 1) {
                setRotatesGiven(0);
            } else if (l == 2) {
                setRotatesGiven((int) (getDanoNPC() * 0.2));
            } else if (l == 3) {
                setRotatesGiven((int) (getDanoNPC() * 0.3));
            } else if (l == 4) {
                setRotatesGiven((int) (getDanoNPC() * 0.5));
            } else if (l == 5) {
                setRotatesGiven((int) (getDanoNPC() * 0.6));
            } else if (l == 6) {
                setRotatesGiven(getDanoNPC() * 1);
            }
        }
    }
    private void damageUs(){
        if(dado==1) {
            if (l == 1) {
                setRotatesGiven(0);
                setHpNPC(getHpNPC());
                if (getHpNPC() <= 0) {
                    setHpNPC(0);
                }
            } else if (l == 2) {
                setRotatesGiven((int) (getDanoPlayer() * 0.2));
                setHpNPC(getHpNPC() - getRotatesGiven() - getDanoPlayer());
                if (getHpNPC() <= 0) {
                    setHpNPC(0);
                }
            } else if (l == 3) {
                setRotatesGiven((int) (getDanoPlayer() * 0.3));
                setHpNPC(getHpNPC() - getRotatesGiven() - getDanoPlayer());
                if (getHpNPC() <= 0) {
                    setHpNPC(0);
                }
            } else if (l == 4) {
                setRotatesGiven((int) (getDanoPlayer() * 0.5));
                setHpNPC(getHpNPC() - getRotatesGiven() - getDanoPlayer());
                if (getHpNPC() <= 0) {
                    setHpNPC(0);
                }
            } else if (l == 5) {
                setRotatesGiven((int) (getDanoPlayer() * 0.6));
                setHpNPC(getHpNPC() - getRotatesGiven() - getDanoPlayer());
                if (getHpNPC() <= 0) {
                    setHpNPC(0);
                }
            } else if (l == 6) {
                setRotatesGiven(getDanoPlayer() * 1);
                setHpNPC(getHpNPC() - getRotatesGiven() - getDanoPlayer());
                if (getHpNPC() <= 0) {
                    setHpNPC(0);
                }
            }
        }
    }

    private void desloca_txt(){
        TextView txt_ataques = findViewById(R.id.txt_ataques);
        Animation desloca1 = new TranslateAnimation(120,0,0,0);
        desloca1.setFillAfter(true);
        desloca1.setDuration(500);
        txt_ataques.startAnimation(desloca1);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void givenDefense(){
        System.out.println("GIVEN DEFENSE");

        ImageButton imgBtn = findViewById(R.id.imgBtn);
        imgBtn.setBackgroundResource(R.drawable.dado);
        final AnimationDrawable anima = (AnimationDrawable) imgBtn.getBackground();
        imgBtn.setImageIcon(null);
        anima.start();
        Animation desloca = new TranslateAnimation(480,0,-50,0);
        desloca.setDuration(500);
        imgBtn.startAnimation(desloca);
        Random gerador = new Random();
        int i = gerador.nextInt(6)+1;//Retorna um número entre 1 e 6.
        switch (i) {
            case 1:
                imgBtn.setBackgroundResource(R.drawable.lado1);
                l = 1;
                break;
            case 2:
                imgBtn.setBackgroundResource(R.drawable.lado2);
                l = 2;
                break;
            case 3:
                imgBtn.setBackgroundResource(R.drawable.lado3);
                l = 3;
                break;
            case 4:
                imgBtn.setBackgroundResource(R.drawable.lado4);
                l = 4;
                break;
            case 5:
                imgBtn.setBackgroundResource(R.drawable.lado5);
                l = 5;
                break;
            case 6:
                imgBtn.setBackgroundResource(R.drawable.lado6);
                l = 6;
                break;
            default:
                alert("ERROR! Switch (GIVEN of USER)");
        }
        desloca.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation arg0) {
            }
            @Override
            public void onAnimationRepeat(Animation arg0) {
            }
            @Override
            public void onAnimationEnd(Animation arg0) {
                //TODO: iniciar a nova animação...
                anima.stop();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void givenAttack(){
        System.out.println("GIVEN ATTACK");

        ImageButton imgBtn = findViewById(R.id.imgBtn);
        imgBtn.setBackgroundResource(R.drawable.dado);
        final AnimationDrawable anima = (AnimationDrawable) imgBtn.getBackground();
        imgBtn.setImageIcon(null);
        anima.start();
        Animation desloca = new TranslateAnimation(480,0,-50,0);
        desloca.setDuration(500);
        imgBtn.startAnimation(desloca);
        Random gerador = new Random();
        int i = gerador.nextInt(6)+1;//Retorna um número entre 1 e 6.
        switch (i) {
            case 1:
                imgBtn.setBackgroundResource(R.drawable.lado1);
                l = 1;
                //Damage of the given
                setRotatesGiven(0);
                //...
                anima.stop();
                break;
            case 2:
                imgBtn.setBackgroundResource(R.drawable.lado2);
                setRotatesGiven(10);
                l = 2;
                anima.stop();
                break;
            case 3:
                imgBtn.setBackgroundResource(R.drawable.lado3);
                setRotatesGiven(30);
                l = 3;
                anima.stop();
                break;
            case 4:
                imgBtn.setBackgroundResource(R.drawable.lado4);
                setRotatesGiven(50);
                l = 4;
                anima.stop();
                break;
            case 5:
                imgBtn.setBackgroundResource(R.drawable.lado5);
                setRotatesGiven(70);
                l = 5;
                anima.stop();
                break;
            case 6:
                imgBtn.setBackgroundResource(R.drawable.lado6);
                setRotatesGiven(100);
                l = 6;
                anima.stop();
                break;
            default:
                alert("ERROR! Switch (GIVEN of USER)");
        }
    }

    private void botao(){
        AlphaAnimation animation = new AlphaAnimation(1, 0); // Altera alpha de visível a invisível
        animation.setDuration(500); // duração - meio segundo
        animation.setInterpolator(new LinearInterpolator());
        Button btn = findViewById(R.id.btn_ok);
        if (first ==0) {
            animation.setRepeatCount(Animation.INFINITE); // Repetir infinitamente
            animation.setRepeatMode(Animation.REVERSE); //Inverte a animação no final para que o botão vá desaparecendo
            btn.startAnimation(animation);
        }else{
            animation.setRepeatCount(Animation.RESTART);
            btn.startAnimation(animation);
        }
    }
    private void existMenu() {
        final Intent intent_menu = new Intent(this, Menu_Principal.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(New_Game.this);
        builder.setMessage("Já tá afim de correr do combate e voltar para o menu?");
        builder.setCancelable(true);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                startActivity(intent_menu);

            }
        });

        builder.setNegativeButton("Voltar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void alert(String s){
        Toast.makeText(this,s, Toast.LENGTH_SHORT).show();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public int getAd() {
        return ad;
    }

    public void setAd(int ad) {
        this.ad = ad;
    }

    public int getAp() {
        return ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getHpNPC() {
        return hpNPC;
    }

    public void setHpNPC(int hpNPC) {
        this.hpNPC = hpNPC;
    }

    public int getAttU() {
        return attU;
    }

    public void setAttU(int attU) {
        this.attU = attU;
    }

    public int getRotatesGiven() {
        return rotatesGiven;
    }

    public void setRotatesGiven(int rotatesGiven) {
        this.rotatesGiven = rotatesGiven;
    }

    public int getDanoNPC() {
        return danoNPC;
    }

    public void setDanoNPC(int danoNPC) {
        this.danoNPC = danoNPC;
    }

    public int getDanoPlayer() {
        return danoPlayer;
    }

    public void setDanoPlayer(int danoPlayer) {
        this.danoPlayer = danoPlayer;
    }
}