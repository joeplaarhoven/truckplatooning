package com.example.truckplatooningkans352.GetSet;

public class LeiderTruck extends TruckInPlatoon{
    private Integer korting;

    public LeiderTruck(String truckKenteken, String platoonRol, String rijRichting, String vertrekDatum, String lading, Integer korting) {
        super(truckKenteken, platoonRol, rijRichting, vertrekDatum, lading);
        this.korting = korting;
    }

    public Integer getKorting() {
        return korting;
    }

    public void setKorting(Integer korting) {
        this.korting = korting;
    }
}
