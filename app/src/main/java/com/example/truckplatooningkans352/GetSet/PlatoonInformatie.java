package com.example.truckplatooningkans352.GetSet;

import java.util.Date;

public class PlatoonInformatie {
    private String rijRichting, vertrekDatum;

    public PlatoonInformatie( String rijRichting, String vertrekDatum) {

        this.rijRichting = rijRichting;
        this.vertrekDatum = vertrekDatum;
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
}
