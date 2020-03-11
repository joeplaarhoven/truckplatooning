package com.example.truckplatooningkans352.ui.truckInfo;

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
import com.example.truckplatooningkans352.DatabaseHelperTruckInPlatoon;
import com.example.truckplatooningkans352.DatabaseHelperTruckInformatie;
import com.example.truckplatooningkans352.GetSet.PlatoonInformatie;
import com.example.truckplatooningkans352.R;

public class TruckInfoFragment extends Fragment {


    DatabaseHelperPlatoonInformatie dbHelper;
    String currentDate;

    EditText rijrichtingET;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        DatabaseHelperTruckInformatie dbHelperTruckInformatie = new DatabaseHelperTruckInformatie(getContext());
        DatabaseHelperTruckInPlatoon dbHelperTruckInPlatoon = new DatabaseHelperTruckInPlatoon(getContext());
        Button btnEditTruckInfo = root.findViewById(R.id.btnAanpassen_truckInfo);
        btnEditTruckInfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        return root;
    }
}