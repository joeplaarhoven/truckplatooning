package com.example.truckplatooningkans352.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.truckplatooningkans352.GetSet.TruckInPlatoon;
import com.example.truckplatooningkans352.R;

import java.util.List;

public class PlatoonListViewAdapter extends ArrayAdapter<TruckInPlatoon> {

    private int resourceLayout;
    private Context context;

    public PlatoonListViewAdapter(Context context, int resource, List<TruckInPlatoon> items){
        super(context, resource, items);
        this.resourceLayout = resource;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(resourceLayout, null);
        }
        TruckInPlatoon TrInPlat = getItem(position);
        if(TrInPlat != null){
            TextView rijRichting = v.findViewById(R.id.tvListView_rijRichting);
            TextView platoonRol = v.findViewById(R.id.tvListView_platoonRol);
            TextView vertrekDatum = v.findViewById(R.id.tvListView_vertrekDatum);
            if(rijRichting != null){
                rijRichting.setText(TrInPlat.getRijRichting());
            }
            if(platoonRol != null){
                platoonRol.setText(TrInPlat.getPlatoonRol());
            }
            if(vertrekDatum != null){
                vertrekDatum.setText(TrInPlat.getVertrekDatum());
            }
        }
        return v;
    }
}
