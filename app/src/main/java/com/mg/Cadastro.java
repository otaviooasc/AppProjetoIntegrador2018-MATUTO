package com.mg;

public class Cadastro {
    private String nick;
    private String senha;
    private String email;
    private String clan;
    private int score;
    private int som;

    @Override
    public String toString() {
        return clan+" "+nick+": "+score;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSom() {
        return som;
    }

    public void setSom(int som) {
        this.som = som;
    }
}
