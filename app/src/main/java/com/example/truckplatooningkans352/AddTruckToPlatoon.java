package com.example.truckplatooningkans352;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.truckplatooningkans352.GetSet.PlatoonInformatie;
import com.example.truckplatooningkans352.GetSet.TruckInPlatoon;
import com.example.truckplatooningkans352.GetSet.VolgTruck;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AddTruckToPlatoon extends AppCompatActivity {

    private static final String TAG = "MyActivity";
    ArrayList<PlatoonInformatie> alPlatoons;
    Cursor data;
    String rijrichting, vertrekDatum;
    private ArrayList<ArrayList<Double>> fullArray, kilometer, brandstof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_truck_to_platoon);

        InputStream inputStream = getResources().openRawResource(R.raw.truckplatoonbesparing);
        CSVfile csvFile = new CSVfile(inputStream);
        ArrayList<ArrayList<Double>> scoreList = csvFile.read();



        double[] targetY = new double[scoreList.get(0).size()];
        double[] targetX = new double[scoreList.get(0).size()];
        for (int i = 0; i < targetY.length; i++) {

            targetY[i] = scoreList.get(0).get(i);
            targetX[i] = scoreList.get(1).get(i);
        }


        LinearRegression lr = new LinearRegression(targetY, targetX);

        Double totaalBrandstof = lr.predict(500);

        Double roundedDouble = Math.round(totaalBrandstof * 100.0) / 100.0;


        alPlatoons = new ArrayList<PlatoonInformatie>();

        final DatabaseHelperPlatoonInformatie dbHelperPlatoonInfo = new DatabaseHelperPlatoonInformatie(this.getBaseContext());
        final DatabaseHelperTruckInPlatoon dbHelperTruckInPlatoon = new DatabaseHelperTruckInPlatoon(this.getBaseContext());
        data = dbHelperPlatoonInfo.getData();
        final Spinner mySpinner = findViewById(R.id.spinner);
        while (data.moveToNext()){
            String rijRichting = data.getString(0);
            String vertrekDatum = data.getString(1);
            PlatoonInformatie p = new PlatoonInformatie(rijRichting, vertrekDatum);
            alPlatoons.add(p);
            mySpinner.setAdapter(new SpinnerAdapter(this, R.layout.spinner_row, alPlatoons));
        }


        final Button registerTruck = findViewById(R.id.btnRegisterTruck);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {

                rijrichting = ((TextView)view.findViewById(R.id.rijrichting_spinnerrow)).getText().toString();
                vertrekDatum = ((TextView)view.findViewById(R.id.vertrekDatum_spinnerRow)).getText().toString();

            }

            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }
        });

        final String kenteken = getIntent().getExtras().getString("kenteken");


        registerTruck.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final EditText ladingET = findViewById(R.id.etLading_addTruckInPlatoon);
                        String lading = ladingET.getText().toString();
                        VolgTruck trInPlat = new VolgTruck(kenteken, "Volgtruck", rijrichting, vertrekDatum, lading, roundedDouble);
                        dbHelperTruckInPlatoon.addTruckInPlatoon(trInPlat);
                        
                    }
                });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

