package com.example.truckplatooningkans352.ui.truckInfo;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.truckplatooningkans352.DatabaseHelper.DatabaseHelperPlatoonInformatie;
import com.example.truckplatooningkans352.DatabaseHelper.DatabaseHelperTruckInPlatoon;
import com.example.truckplatooningkans352.DatabaseHelper.DatabaseHelperTruckInformatie;
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

        EditText etKenteken = root.findViewById(R.id.etTruckKenteken_truckInfo);
        EditText etMerk = root.findViewById(R.id.etTruckMerk_truckInfo);
        EditText etNaam = root.findViewById(R.id.etNaam_truckinfo);

        final String kenteken = getActivity().getIntent().getExtras().getString("kenteken");

        Cursor data = dbHelperTruckInformatie.getData(kenteken);

        while (data.moveToNext()) {
            etKenteken.setText(data.getString(0));
            etNaam.setText(data.getString(1));
            etMerk.setText(data.getString(2));
        }

        btnEditTruckInfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       String kentekenNew = etKenteken.getText().toString();
                       String merkNew = etMerk.getText().toString();
                       String naamNew = etNaam.getText().toString();
                       getActivity().getIntent().putExtra("kenteken", kentekenNew);

                       if(kentekenNew != kenteken){
                           dbHelperTruckInPlatoon.updateKenteken(kentekenNew, kenteken);
                       }
                        dbHelperTruckInformatie.updateTruckInfo(kentekenNew, kenteken, naamNew, merkNew);
                    }
                });
        return root;
    }
}