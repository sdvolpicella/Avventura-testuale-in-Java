/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;

/**
 *
 * @author valen
 * Classe che salva lo stato dei combattimenti e ne permmette la serializzazione
 */
public class SerializedBattle implements Serializable {

    private String nomeNemico;
    private int vitaNemico;
    private String imagePath;
    private boolean isActive = false;
    private boolean continua = true;
    private int turno = 1;

    public SerializedBattle(String nomeNemico, int vitaNemico, String imagePath, boolean isActive, boolean continua, int turno) {
        this.nomeNemico = nomeNemico;
        this.vitaNemico = vitaNemico;
        this.imagePath = imagePath;
        this.isActive = isActive;
        this.continua = continua;
        this.turno = turno;
    }

    public String getNomeNemico() {
        return nomeNemico;
    }

    public void setNomeNemico(String nomeNemico) {
        this.nomeNemico = nomeNemico;
    }

    public int getVitaNemico() {
        return vitaNemico;
    }

    public void setVitaNemico(int vitaNemico) {
        this.vitaNemico = vitaNemico;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isContinua() {
        return continua;
    }

    public void setContinua(boolean continua) {
        this.continua = continua;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
}
