package com.example.estaodejogos;

public class Record {
    private int id;
//    private int user;
    private String nome_arcade;
    private int ranking;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getUser() {
//        return user;
//    }
//
//    public void setUser(int user) {
//        this.user = user;
//    }

    public String getNome() {
        return nome_arcade;
    }

    public void setNome_arcade(String nome_arcade) {
        this.nome_arcade = nome_arcade;
    }

    public int getranking() {
        return ranking;
    }

    public void setranking(int ranking) {
        this.ranking = ranking;
    }
}
