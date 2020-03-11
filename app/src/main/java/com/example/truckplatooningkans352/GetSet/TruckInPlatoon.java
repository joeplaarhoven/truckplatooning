package com.example.truckplatooningkans352.GetSet;

public class TruckInPlatoon {
    public String truckKenteken, platoonRol, lading, rijRichting, vertrekDatum;


    public TruckInPlatoon(String truckKenteken, String platoonRol, String rijRichting, String vertrekDatum, String lading) {
        this.truckKenteken = truckKenteken;
        this.platoonRol = platoonRol;
        this.rijRichting = rijRichting;
        this.vertrekDatum = vertrekDatum;
        this.lading = lading;
    }


    public String getTruckKenteken() {
        return truckKenteken;
    }

    public void setTruckKenteken(String truckKenteken) {
        this.truckKenteken = truckKenteken;
    }

    public String getPlatoonRol() {
        return platoonRol;
    }

    public void setPlatoonRol(String platoonRol) {
        this.platoonRol = platoonRol;
    }

    public String getRijRichting() {
        return rijRichting;
    }

    public void setRijRichting(String rijRichting) {
        this.rijRichting = rijRichting;
    }

    public String getVertrekDatum() {
        return vertrekDatum;
    }

    public void setVertrekDatum(String vertrekDatum) {
        this.vertrekDatum = vertrekDatum;
    }

    public String getLading() { return lading; }

    public void setLading(String lading){ this.lading = lading; }
}
