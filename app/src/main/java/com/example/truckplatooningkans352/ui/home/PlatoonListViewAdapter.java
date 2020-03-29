package com.example.truckplatooningkans352.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.truckplatooningkans352.GetSet.LeiderTruck;
import com.example.truckplatooningkans352.GetSet.TruckInPlatoon;
import com.example.truckplatooningkans352.GetSet.VolgTruck;
import com.example.truckplatooningkans352.R;

import java.util.List;
//
public class PlatoonListViewAdapter extends ArrayAdapter<TruckInPlatoon> {
    private final List<TruckInPlatoon> items;


    private int resourceLayout;
    private Context context;
    TruckInPlatoon TrInPlat;

    public PlatoonListViewAdapter(Context context, int resource, List<TruckInPlatoon> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.context = context;
        this.items = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(resourceLayout, null);
        }

        VolgTruck vt;
        LeiderTruck lt;
        if (getItem(position) instanceof VolgTruck) {
            vt = (VolgTruck) getItem(position);

            if (vt != null) {
                TextView rijRichting = v.findViewById(R.id.tvListView_rijRichting);
                TextView platoonRol = v.findViewById(R.id.tvListView_platoonRol);
                TextView vertrekDatum = v.findViewById(R.id.tvListView_vertrekDatum);
                TextView afgeleiden = v.findViewById(R.id.tvSubklas);
                if (rijRichting != null) {
                    rijRichting.setText(vt.getRijRichting());
                }
                if (platoonRol != null) {
                    platoonRol.setText(vt.getPlatoonRol());
                }
                if (vertrekDatum != null) {
                    vertrekDatum.setText(vt.getVertrekDatum());
                }
                if (afgeleiden != null) {
                    afgeleiden.setText(vt.getBesparing().toString()+ " Liter besparing");
                }

            }
            }else if (getItem(position) instanceof LeiderTruck) {
            lt = (LeiderTruck) getItem(position);

            if (lt != null) {
                TextView rijRichting = v.findViewById(R.id.tvListView_rijRichting);
                TextView platoonRol = v.findViewById(R.id.tvListView_platoonRol);
                TextView vertrekDatum = v.findViewById(R.id.tvListView_vertrekDatum);
                TextView afgeleiden = v.findViewById(R.id.tvSubklas);
                if (rijRichting != null) {
                    rijRichting.setText(lt.getRijRichting());
                }
                if (platoonRol != null) {
                    platoonRol.setText(lt.getPlatoonRol());
                }
                if (vertrekDatum != null) {
                    vertrekDatum.setText(lt.getVertrekDatum());
                }
                if (afgeleiden != null) {
                    afgeleiden.setText(lt.getGeldTerug().toString() + " euro terug");
                }
            }
        }
        return v;
    }
}
