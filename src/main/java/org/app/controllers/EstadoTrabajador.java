package org.app.controllers;

public class EstadoTrabajador {
    private char estadoTraId;
    private String estadoTraNom;
    private char estadoEstReg;

    public char getEstadoTraId() {
        return estadoTraId;
    }

    public void setEstadoTraId(char estadoTraId) {
        this.estadoTraId = estadoTraId;
    }

    public String getEstadoTraNom() {
        return estadoTraNom;
    }

    public void setEstadoTraNom(String estadoTraNom) {
        this.estadoTraNom = estadoTraNom;
    }

    public char getEstadoEstReg() {
        return estadoEstReg;
    }

    public void setEstadoEstReg(char estadoEstReg) {
        this.estadoEstReg = estadoEstReg;
    }
}
