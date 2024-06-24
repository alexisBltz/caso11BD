package org.app.controllers;

public class TipoCuentaCorriente {
    private int tipoCuentaCorrienteCod;
    private String tipCueCorNom;
    private char tipCueCorEstReg;

    public int getTipoCuentaCorrienteCod() {
        return tipoCuentaCorrienteCod;
    }

    public void setTipoCuentaCorrienteCod(int tipoCuentaCorrienteCod) {
        this.tipoCuentaCorrienteCod = tipoCuentaCorrienteCod;
    }

    public String getTipCueCorNom() {
        return tipCueCorNom;
    }

    public void setTipCueCorNom(String tipCueCorNom) {
        this.tipCueCorNom = tipCueCorNom;
    }

    public char getTipCueCorEstReg() {
        return tipCueCorEstReg;
    }

    public void setTipCueCorEstReg(char tipCueCorEstReg) {
        this.tipCueCorEstReg = tipCueCorEstReg;
    }
}

