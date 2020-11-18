package com.mg;

public class Stt {
    private String nick;
    private String clan;
    private int ad;
    private int ap;
    private int som2;
    private int hp;
    private int hpNPC;

    public Stt(String clan) {
        setHpNPC(10);

        switch (clan){
            case "Palma":
                setAd(40);
                setAp(15);
                setSom2(1);
                setHp(90);
                break;
            case "Mandacaru":

                setAd(20);
                setAp(25);
                setSom2(1);
                setHp(80);
                break;
            case "Cardeiro":

                setAd(40);
                setAp(20);
                setSom2(1);
                setHp(100);
                break;
            case "Lampião":
                setAd(100);
                setAp(150);
                setHp(1000);
                setSom2(1);
                break;
            default:
                break;
        }
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

    public int getSom2() {
        return som2;
    }

    public void setSom2(int som2) {
        this.som2 = som2;
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


}
