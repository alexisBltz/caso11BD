package org.app;
import java.util.Date;

public class Trabajador {
    private int trabajadorId;
    private String trabajadorNombre;
    private char trabajaTipoTraId;
    private Date traFecIng;
    private Date traFecCes;
    private Date traFecUltSalVac;
    private char traEstTra;
    private char traEstRegTra;
    private String cueCorNum;
    private char codCenCos;

    // Getters y setters

    public int getTrabajadorId() {
        return trabajadorId;
    }

    public void setTrabajadorId(int trabajadorId) {
        this.trabajadorId = trabajadorId;
    }

    public String getTrabajadorNombre() {
        return trabajadorNombre;
    }

    public void setTrabajadorNombre(String trabajadorNombre) {
        this.trabajadorNombre = trabajadorNombre;
    }

    public char getTrabajaTipoTraId() {
        return trabajaTipoTraId;
    }

    public void setTrabajaTipoTraId(char trabajaTipoTraId) {
        this.trabajaTipoTraId = trabajaTipoTraId;
    }

    public Date getTraFecIng() {
        return traFecIng;
    }

    public void setTraFecIng(Date traFecIng) {
        this.traFecIng = traFecIng;
    }

    public Date getTraFecCes() {
        return traFecCes;
    }

    public void setTraFecCes(Date traFecCes) {
        this.traFecCes = traFecCes;
    }

    public Date getTraFecUltSalVac() {
        return traFecUltSalVac;
    }

    public void setTraFecUltSalVac(Date traFecUltSalVac) {
        this.traFecUltSalVac = traFecUltSalVac;
    }

    public char getTraEstTra() {
        return traEstTra;
    }

    public void setTraEstTra(char traEstTra) {
        this.traEstTra = traEstTra;
    }

    public char getTraEstRegTra() {
        return traEstRegTra;
    }

    public void setTraEstRegTra(char traEstRegTra) {
        this.traEstRegTra = traEstRegTra;
    }

    public String getCueCorNum() {
        return cueCorNum;
    }

    public void setCueCorNum(String cueCorNum) {
        this.cueCorNum = cueCorNum;
    }

    public char getCodCenCos() {
        return codCenCos;
    }

    public void setCodCenCos(char codCenCos) {
        this.codCenCos = codCenCos;
    }
}
