package com.example.truckplatooningkans352;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.truckplatooningkans352.GetSet.TruckInPlatoon;

public class DatabaseHelperTruckInPlatoon extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "TruckInPlatoon";
    private static final String COL_RIJRICHTING = "rijRichting";
    private static final String COL_VERTREKDATUM = "vertrekDatum";
    private static final String COL_KENTEKEN = "kenteken";
    private static final String COL_PLATOONROL = "platoonRol";
    private static final String COL_LADING = "lading";
    private static final String COL_BESPARING = "besparing";

    public DatabaseHelperTruckInPlatoon(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableTruckInfo = "CREATE TABLE " + TABLE_NAME + "("
                + COL_KENTEKEN + " TEXT, "
                + COL_RIJRICHTING + " TEXT, "
                + COL_VERTREKDATUM + " TEXT, "
                + COL_PLATOONROL + " TEXT, "
                + COL_LADING+ " TEXT, "
                + COL_BESPARING + " REAL," +
                "PRIMARY KEY ("+ COL_RIJRICHTING+ ","+ COL_VERTREKDATUM +", "+ COL_VERTREKDATUM + "),"+
                "FOREIGN KEY(" + COL_KENTEKEN + ") REFERENCES TruckInfo(" + COL_KENTEKEN + ")," +
                "FOREIGN KEY(" + COL_VERTREKDATUM + ") REFERENCES PlatoonInfo(" + COL_VERTREKDATUM + ")," +
                "FOREIGN KEY(" + COL_RIJRICHTING  + ") REFERENCES PlatoonInfo(" + COL_RIJRICHTING + "))";
        db.execSQL(createTableTruckInfo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean addTruckInPlatoon(TruckInPlatoon TrInPlat) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues insertValues = new ContentValues();
        insertValues.put(COL_VERTREKDATUM, TrInPlat.getVertrekDatum());
        insertValues.put(COL_RIJRICHTING, TrInPlat.getRijRichting());
        insertValues.put(COL_KENTEKEN, TrInPlat.getTruckKenteken());
        insertValues.put(COL_PLATOONROL, TrInPlat.getPlatoonRol());
        insertValues.put(COL_LADING, TrInPlat.getLading());
        insertValues.put(COL_BESPARING, TrInPlat.getBesparing());
        Long result = db.insert(TABLE_NAME, null, insertValues);
        return result != -1;

    }


    public Cursor getData(String kenteken) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME + " WHERE " + COL_KENTEKEN + " = '" + kenteken + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}

