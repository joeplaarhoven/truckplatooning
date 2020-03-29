package com.example.truckplatooningkans352.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.truckplatooningkans352.GetSet.LeiderTruck;
import com.example.truckplatooningkans352.GetSet.TruckInPlatoon;
import com.example.truckplatooningkans352.GetSet.VolgTruck;

public class DatabaseHelperTruckInPlatoon extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "TruckInPlatoon";
    private static final String COL_RIJRICHTING = "rijRichting";
    private static final String COL_VERTREKDATUM = "vertrekDatum";
    private static final String COL_KENTEKEN = "kenteken";
    private static final String COL_PLATOONROL = "platoonRol";
    private static final String COL_LADING = "lading";
    private static final String COL_BESPARING = "besparing";
    private static final String COL_GELDTERUG = "geldTerug";

    public DatabaseHelperTruckInPlatoon(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableTruckInfo = "CREATE TABLE " + TABLE_NAME + "("
                + COL_KENTEKEN + " TEXT NOT NULL, "
                + COL_RIJRICHTING + " TEXT NOT NULL, "
                + COL_VERTREKDATUM + " TEXT NOT NULL, "
                + COL_PLATOONROL + " TEXT NOT NULL, "
                + COL_LADING+ " TEXT NOT NULL, "
                + COL_BESPARING + " REAL,"
                + COL_GELDTERUG + " REAL," +
                "PRIMARY KEY ("+ COL_RIJRICHTING+ ","+ COL_VERTREKDATUM +", "+ COL_KENTEKEN + "),"+
                "FOREIGN KEY(" + COL_KENTEKEN + ") REFERENCES TruckInfo(" + COL_KENTEKEN + ")," +
                "FOREIGN KEY(" + COL_VERTREKDATUM + ") REFERENCES PlatoonInfo(" + COL_VERTREKDATUM + ")," +
                "FOREIGN KEY(" + COL_RIJRICHTING  + ") REFERENCES PlatoonInfo(" + COL_RIJRICHTING + "))";
        db.execSQL(createTableTruckInfo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean addVolgTruck(VolgTruck vt) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues insertValues = new ContentValues();
        insertValues.put(COL_VERTREKDATUM, vt.getVertrekDatum());
        insertValues.put(COL_RIJRICHTING, vt.getRijRichting());
        insertValues.put(COL_KENTEKEN, vt.getTruckKenteken());
        insertValues.put(COL_PLATOONROL, vt.getPlatoonRol());
        insertValues.put(COL_LADING, vt.getLading());
        insertValues.put(COL_BESPARING, vt.getBesparing());
        Long result = db.insert(TABLE_NAME, null, insertValues);
        return result != -1;

    }

    public boolean addLeiderTruck(LeiderTruck lt) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues insertValues = new ContentValues();
        insertValues.put(COL_VERTREKDATUM, lt.getVertrekDatum());
        insertValues.put(COL_RIJRICHTING, lt.getRijRichting());
        insertValues.put(COL_KENTEKEN, lt.getTruckKenteken());
        insertValues.put(COL_PLATOONROL, lt.getPlatoonRol());
        insertValues.put(COL_LADING, lt.getLading());
        insertValues.put(COL_GELDTERUG, lt.getGeldTerug());
        Long result = db.insert(TABLE_NAME, null, insertValues);
        return result != -1;

    }


    public Cursor getData(String kenteken) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME + " WHERE " + COL_KENTEKEN + " = '" + kenteken + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public boolean updateKenteken(String kenteken, String kentekenOld){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL_KENTEKEN,kenteken);

        Integer result = db.update(TABLE_NAME, cv, COL_KENTEKEN + " = '"+ kentekenOld + "'", null);
        return result != 1;
    }
}

