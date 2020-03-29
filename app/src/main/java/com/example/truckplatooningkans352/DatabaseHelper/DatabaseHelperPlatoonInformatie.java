package com.example.truckplatooningkans352.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.truckplatooningkans352.GetSet.PlatoonInformatie;
import com.example.truckplatooningkans352.GetSet.TruckInformatie;

public class DatabaseHelperPlatoonInformatie extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "PlatoonInfo";
    private static final String COL_RIJRICHTING = "rijRichting";
    private static final String COL_VERTREKDATUM = "vertrekDatum";

    public DatabaseHelperPlatoonInformatie(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableTruckInfo = "CREATE TABLE " + TABLE_NAME + "("
                + COL_RIJRICHTING + " TEXT, "
                + COL_VERTREKDATUM + " TEXT," +
                "PRIMARY KEY ("+ COL_RIJRICHTING+ ","+ COL_VERTREKDATUM +"))";
        db.execSQL(createTableTruckInfo);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //@Override
    //public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    //    onCreate(db);
    //}

    public boolean addPlatoonInformatie(PlatoonInformatie Pinfo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues insertValues = new ContentValues();
        insertValues.put(COL_RIJRICHTING, Pinfo.getRijRichting());
        insertValues.put(COL_VERTREKDATUM, Pinfo.getVertrekDatum());
        Long result = db.insert(TABLE_NAME, null, insertValues);
        return result != -1;

    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}

