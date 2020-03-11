package com.example.truckplatooningkans352.ui.newPlatoon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.truckplatooningkans352.DatabaseHelperPlatoonInformatie;
import com.example.truckplatooningkans352.GetSet.PlatoonInformatie;
import com.example.truckplatooningkans352.R;

public class NewPlatoonFragment extends Fragment {

    DatabaseHelperPlatoonInformatie dbHelper;
    String currentDate;

    EditText rijrichtingET;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_new_platoon, container, false);

        CalendarView cv = root.findViewById(R.id.PlatInfo_vertrekCal);

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                currentDate = dayOfMonth + "/" + (month + 1) + "/" + year;

            }
        });

        rijrichtingET = root.findViewById(R.id.PlatInfo_rijrichtingET);

        dbHelper = new DatabaseHelperPlatoonInformatie(getContext());
        Button addPlatoonBtn = root.findViewById(R.id.PlatInfo_addPlatoonBtn);
        addPlatoonBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        final String rijrichting = rijrichtingET.getText().toString();

                        PlatoonInformatie PlInfo = new PlatoonInformatie(rijrichting, currentDate);
                        dbHelper.addPlatoonInformatie(PlInfo);
                    }
                });
        return root;
    }
}