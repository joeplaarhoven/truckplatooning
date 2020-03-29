package com.example.truckplatooningkans352.ui.newPlatoon;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.truckplatooningkans352.CSVfile;
import com.example.truckplatooningkans352.DatabaseHelper.DatabaseHelperPlatoonInformatie;
import com.example.truckplatooningkans352.DatabaseHelper.DatabaseHelperTruckInPlatoon;
import com.example.truckplatooningkans352.GetSet.LeiderTruck;
import com.example.truckplatooningkans352.GetSet.PlatoonInformatie;
import com.example.truckplatooningkans352.LinearRegression;
import com.example.truckplatooningkans352.R;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class NewPlatoonFragment extends Fragment {

    DatabaseHelperPlatoonInformatie dbHelper;
    DatabaseHelperTruckInPlatoon dbHelperTruckInPlatoon;
    String currentDate;

    EditText rijrichtingET;
    Calendar myCalendar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_new_platoon, container, false);

        final String kenteken = getActivity().getIntent().getExtras().getString("kenteken");

//        CalendarView cv = root.findViewById(R.id.PlatInfo_vertrekCal);

        InputStream inputStream = getResources().openRawResource(R.raw.truckplatoongeldterug);
        CSVfile csvFile = new CSVfile(inputStream);
        ArrayList<ArrayList<Double>> scoreList = csvFile.read();



        double[] targetY = new double[scoreList.get(0).size()];
        double[] targetX = new double[scoreList.get(0).size()];
        for (int i = 0; i < targetY.length; i++) {

            targetY[i] = scoreList.get(0).get(i);
            targetX[i] = scoreList.get(1).get(i);
        }


        LinearRegression lr = new LinearRegression(targetY, targetX);


        myCalendar = Calendar.getInstance();

        EditText edittext= root.findViewById(R.id.PlatInfo_vertrekCal);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(root);
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });











//        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month,
//                                            int dayOfMonth) {
//
//                currentDate = dayOfMonth + "/" + (month + 1) + "/" + year;
//
//            }
//        });

        rijrichtingET = root.findViewById(R.id.PlatInfo_rijrichtingET);

        dbHelper = new DatabaseHelperPlatoonInformatie(getContext());
        Button addPlatoonBtn = root.findViewById(R.id.PlatInfo_addPlatoonBtn);
        addPlatoonBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final EditText afstand = root.findViewById(R.id.etAfstand_newPlatoon);
                        String afstandSt = afstand.getText().toString();


                        double afstandD = Double.parseDouble(afstandSt);

                        Double totaalBrandstof = lr.predict(afstandD);

                        Double roundedDouble = Math.round(totaalBrandstof * 100.0) / 100.0;


                        final String rijrichting = rijrichtingET.getText().toString();

                        EditText edittext =root.findViewById(R.id.PlatInfo_vertrekCal);
                        currentDate = edittext.getText().toString();

                        EditText edittextLading =root.findViewById(R.id.etLading_newPlatoon);
                        String lading = edittextLading.getText().toString();

                        PlatoonInformatie PlInfo = new PlatoonInformatie(rijrichting, currentDate);
                        dbHelper.addPlatoonInformatie(PlInfo);

                        LeiderTruck lt = new LeiderTruck(kenteken,"Leidertruck", rijrichting, currentDate, lading, roundedDouble);
                        dbHelperTruckInPlatoon = new DatabaseHelperTruckInPlatoon(getContext());
                        dbHelperTruckInPlatoon.addLeiderTruck(lt);


                    }
                });
        return root;
    }



    private void updateLabel(View root) {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        EditText edittext =root.findViewById(R.id.PlatInfo_vertrekCal);
        edittext.setText(sdf.format(myCalendar.getTime()));
    }

}
