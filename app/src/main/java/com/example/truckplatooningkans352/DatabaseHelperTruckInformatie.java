package com.example.truckplatooningkans352;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.truckplatooningkans352.GetSet.TruckInformatie;

public class DatabaseHelperTruckInformatie extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "TruckInfo";
    private static final String COL_CHAFFEUR = "chaffeur";
    private static final String COL_KENTEKEN = "kenteken";
    private static final String COL_MERK = "merk";
    private static final String COL_WACHTWOORD = "wachtwoord";

    public DatabaseHelperTruckInformatie(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableTruckInfo = "CREATE TABLE " + TABLE_NAME + "("
                + COL_KENTEKEN + " TEXT PRIMARY KEY, "
                + COL_CHAFFEUR + " TEXT, "
                + COL_MERK + " TEXT, "
                + COL_WACHTWOORD+ " TEXT)";
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

    public boolean addTruckInformatie(TruckInformatie Tinfo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues insertValues = new ContentValues();
        insertValues.put(COL_CHAFFEUR, Tinfo.getChaffeur());
        insertValues.put(COL_KENTEKEN, Tinfo.getTruckKenteken());
        insertValues.put(COL_MERK, Tinfo.getTruckMerk());
        insertValues.put(COL_WACHTWOORD, Tinfo.getWachtwoord());
        Long result = db.insert(TABLE_NAME, null, insertValues);
        return result != -1;

    }

    public Boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TruckInfo WHERE kenteken = ? AND wachtwoord = ?", new String[]{username, password});
        return cursor.getCount() > 0;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

//    public Integer editData(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("Field1","Bob"); //These Fields should be your String values of actual column names
//        cv.put("Field2","19");
//        cv.put("Field2","Male");
//
//        db.update(TABLE_NAME, cv, COL_KENTEKEN = kenteken, null);
//    }

}

