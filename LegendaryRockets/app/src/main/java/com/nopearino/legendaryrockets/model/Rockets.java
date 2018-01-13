package com.nopearino.legendaryrockets.model;

/**
 * Created by exemp on 13/01/2018.
 */

public class Rockets {
    int id;
    String nome;
    String manuf;

    //construtores
    public Rockets(){}

    public Rockets(String nome,String manuf){
        this.manuf=manuf;
        this.nome=nome;
    }

    //getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getManuf() {
        return manuf;
    }

    public void setManuf(String manuf) {
        this.manuf = manuf;
    }

}
