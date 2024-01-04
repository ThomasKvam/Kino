package com.example.webprogoblig3;

public class Billett {
    private String filmer;
    private int antall;
    private String etternavn;
    private String fornavn;
    private String nummer;
    private String epost;


    public Billett(String filmer, int antall, String fornavn, String etternavn, String nummer, String epost){
        this.filmer=filmer;
        this.antall=antall;
        this.fornavn=fornavn;
        this.etternavn=etternavn;
        this.nummer=nummer;
        this.epost=epost;
    }

    public Billett(){}

    public String getFilmer() {
        return filmer;
    }

    public void setFilmer(String filmer) {
        this.filmer = filmer;
    }

    public int getAntall() {
        return antall;
    }

    public void setAntall(int antall) {
        this.antall = antall;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }
}