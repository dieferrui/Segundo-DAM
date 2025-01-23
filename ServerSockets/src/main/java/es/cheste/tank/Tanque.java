package es.cheste.tank;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Tanque implements Serializable {
    String nickname;
    String clase;
    int numSerial;
    int peso;
    int calibreCannon;

    public Tanque(String nickname, String clase, int numSerial, int peso, int calibreCannon) {
        this.nickname = nickname;
        this.clase = clase;
        this.numSerial = numSerial;
        this.peso = peso;
        this.calibreCannon = calibreCannon;
    }

    public Tanque() { super(); }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public int getNumSerial() {
        return numSerial;
    }

    public void setNumSerial(int numSerial) {
        this.numSerial = numSerial;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getCalibreCannon() {
        return calibreCannon;
    }

    public void setCalibreCannon(int calibreCannon) {
        this.calibreCannon = calibreCannon;
    }
}
