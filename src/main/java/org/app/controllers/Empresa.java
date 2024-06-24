package org.app.controllers;

public class Empresa {
    private int emprCod;
    private String emprNom;
    private String emprDir;
    private char emprEstRegEmpCod;

    public int getEmprCod() {
        return emprCod;
    }

    public void setEmprCod(int emprCod) {
        this.emprCod = emprCod;
    }

    public String getEmprNom() {
        return emprNom;
    }

    public void setEmprNom(String emprNom) {
        this.emprNom = emprNom;
    }

    public String getEmprDir() {
        return emprDir;
    }

    public void setEmprDir(String emprDir) {
        this.emprDir = emprDir;
    }

    public char getEmprEstRegEmpCod() {
        return emprEstRegEmpCod;
    }

    public void setEmprEstRegEmpCod(char emprEstRegEmpCod) {
        this.emprEstRegEmpCod = emprEstRegEmpCod;
    }
}
