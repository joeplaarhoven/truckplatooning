package com.example.truckplatooningkans352;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.truckplatooningkans352.GetSet.PlatoonInformatie;

import java.util.ArrayList;

public class SpinnerAdapter extends ArrayAdapter<PlatoonInformatie> {

    ArrayList<String> rijRichting, vertrekDatum;
    ArrayList<PlatoonInformatie> objects;
    public SpinnerAdapter(Context context, int textViewResourceId, ArrayList<PlatoonInformatie> objects)
    {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(this.getContext());
            v = vi.inflate(R.layout.spinner_row, null);
        }

        PlatoonInformatie p = getItem(position);
        if(p != null){
            TextView rijRichting = v.findViewById(R.id.rijrichting_spinnerrow);
            TextView vertrekDatum = v.findViewById(R.id.vertrekDatum_spinnerRow);

            if(rijRichting != null){
                rijRichting.setText(p.getRijRichting());
            }
            if(vertrekDatum != null){
                vertrekDatum.setText(p.getVertrekDatum());
            }
        }

        return v;
    }
}