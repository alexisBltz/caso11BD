package org.app.controllers;

public class TipoPrestamo {
    private char tipoPrestamoId;
    private String tipPresNom;
    private char tipPresEstReg;

    public char getTipoPrestamoId() {
        return tipoPrestamoId;
    }

    public void setTipoPrestamoId(char tipoPrestamoId) {
        this.tipoPrestamoId = tipoPrestamoId;
    }

    public String getTipPresNom() {
        return tipPresNom;
    }

    public void setTipPresNom(String tipPresNom) {
        this.tipPresNom = tipPresNom;
    }

    public char getTipPresEstReg() {
        return tipPresEstReg;
    }

    public void setTipPresEstReg(char tipPresEstReg) {
        this.tipPresEstReg = tipPresEstReg;
    }
}
