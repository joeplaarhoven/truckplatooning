package com.example.truckplatooningkans352.GetSet;

public class TruckInformatie {

    private String truckKenteken, chaffeur, truckMerk, wachtwoord;

    public TruckInformatie(String truckKenteken, String chaffeur, String truckMerk, String wachtwoord) {
        this.truckKenteken = truckKenteken;
        this.chaffeur = chaffeur;
        this.truckMerk = truckMerk;
        this.wachtwoord = wachtwoord;
    }

    public String getTruckKenteken() {
        return truckKenteken;
    }

    public void setTruckKenteken(String truckKenteken) {
        this.truckKenteken = truckKenteken;
    }

    public String getChaffeur() {
        return chaffeur;
    }

    public void setChaffeur(String chaffeur) {
        this.chaffeur = chaffeur;
    }

    public String getTruckMerk() {
        return truckMerk;
    }

    public void setTruckMerk(String truckMerk) {
        this.truckMerk = truckMerk;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {this.wachtwoord = wachtwoord;}
}
