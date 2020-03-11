package com.example.truckplatooningkans352.GetSet;

public class VolgTruck extends TruckInPlatoon {

    public VolgTruck(String truckKenteken, String platoonRol, String rijRichting, String vertrekDatum, String lading, Double besparing) {
        super(truckKenteken, platoonRol, rijRichting, vertrekDatum, lading);
        this.besparing = besparing;
    }


    public Double getBesparing() {
        return besparing;
    }

    public void setBesparing(Double besparing) {
        this.besparing = besparing;
    }

    private Double besparing;

}
