package com.example.truckplatooningkans352.GetSet;

public class LeiderTruck extends TruckInPlatoon{
    private Double geldTerug;

    public LeiderTruck(String truckKenteken, String platoonRol, String rijRichting, String vertrekDatum, String lading, Double geldTerug) {
        super(truckKenteken, platoonRol, rijRichting, vertrekDatum, lading);
        this.geldTerug = geldTerug;
    }

    public Double getGeldTerug() {
        return geldTerug;
    }

    public void setGeldTerug(Double korting) {
        this.geldTerug = geldTerug;
    }
}
