package com.huynhngoctai.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adt extends ArrayAdapter<Data> {
    public Adt(Context context, List<Data> data) {
        super(context, 0, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Data dataModel = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        TextView text1 = convertView.findViewById(android.R.id.text1);
        TextView text2 = convertView.findViewById(android.R.id.text2);

        text1.setText(dataModel.getId() + ": " + dataModel.getDateTime() + ": "+ dataModel.getVoltage() + ": " + dataModel.getCurrent()+ ": "+ dataModel.getPower()+ ": "+dataModel.getEnergy()+ ": " + dataModel.getFrequency()+ ": " + dataModel.getPowerFactor());

        return convertView;
    }
}

