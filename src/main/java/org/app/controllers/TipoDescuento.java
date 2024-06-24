package org.app.controllers;

public class TipoDescuento {
    private int descuPlaCod;
    private String tiPdesPlaNom;
    private char tiPdesPlaEstReg;

    public int getDescuPlaCod() {
        return descuPlaCod;
    }

    public void setDescuPlaCod(int descuPlaCod) {
        this.descuPlaCod = descuPlaCod;
    }

    public String getTiPdesPlaNom() {
        return tiPdesPlaNom;
    }

    public void setTiPdesPlaNom(String tiPdesPlaNom) {
        this.tiPdesPlaNom = tiPdesPlaNom;
    }

    public char getTiPdesPlaEstReg() {
        return tiPdesPlaEstReg;
    }

    public void setTiPdesPlaEstReg(char tiPdesPlaEstReg) {
        this.tiPdesPlaEstReg = tiPdesPlaEstReg;
    }
}

