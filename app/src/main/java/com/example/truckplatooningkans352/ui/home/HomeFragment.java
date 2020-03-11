package com.example.truckplatooningkans352.ui.home;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.truckplatooningkans352.AddTruckToPlatoon;
import com.example.truckplatooningkans352.DatabaseHelperTruckInPlatoon;
import com.example.truckplatooningkans352.GetSet.PlatoonInformatie;
import com.example.truckplatooningkans352.GetSet.TruckInPlatoon;
import com.example.truckplatooningkans352.MainActivity;
import com.example.truckplatooningkans352.NavigationActivity;
import com.example.truckplatooningkans352.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    DatabaseHelperTruckInPlatoon dbHelper;
    ArrayList<TruckInPlatoon> alTrucksInPlatoon;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final String kenteken = getActivity().getIntent().getExtras().getString("kenteken");

        dbHelper = new DatabaseHelperTruckInPlatoon(getContext());
        alTrucksInPlatoon = new ArrayList<>();
        ListView lv = root.findViewById(R.id.listViewTruckPlatoon);
        populateList(kenteken);
        ListAdapter adapter = new PlatoonListViewAdapter(getContext(), R.layout.listview_truck_in_platoon_row, alTrucksInPlatoon);
        lv.setAdapter(adapter);



        Button addTruckBtn = root.findViewById(R.id.truckInPlat_addBtn);
        addTruckBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(v.getContext(), AddTruckToPlatoon.class);
                        i.putExtra("kenteken", kenteken);

                        startActivity(i);
                    }
                });
        return root;
    }

    public void populateList(String personalKenteken){
        Cursor data = dbHelper.getData(personalKenteken);
        while (data.moveToNext()){
            String kenteken = data.getString(0);
            String platoonRol = data.getString(1);
            String rijRichting = data.getString(2);
            String vertrekDatum = data.getString(3);
            String lading = data.getString(4);
            TruckInPlatoon p = new TruckInPlatoon(kenteken, platoonRol, rijRichting, vertrekDatum, lading);
            alTrucksInPlatoon.add(p);
        }

    }
}