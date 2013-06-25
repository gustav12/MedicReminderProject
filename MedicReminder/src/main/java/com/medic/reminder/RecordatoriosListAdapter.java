package com.medic.reminder;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecordatoriosListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<InfoRecordatorio> mRecordatoriosList;

    public RecordatoriosListAdapter(Context context, ArrayList<InfoRecordatorio> recordatoriosList) {
        mContext = context;
        mRecordatoriosList = recordatoriosList;
    }

    @Override
    public int getCount() {
        return mRecordatoriosList.size();
    }

    @Override
    public InfoRecordatorio getItem(int position) {
        return mRecordatoriosList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout itemView;
        itemView = (LinearLayout) mLayoutInflater.inflate(R.layout.row_view_recordatorio, viewGroup, false);
        itemView.setBackgroundResource(R.drawable.rounded_corners);

        Typeface typefaceLight = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Light.ttf");
        Typeface typefaceMedium = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Medium.ttf");

        TextView txtMedicina = (TextView) itemView.findViewById(R.id.textViewMedicina);
        TextView txtDescripcion = (TextView) itemView.findViewById(R.id.textViewDescripcion);
        TextView txtSiguienteToma = (TextView) itemView.findViewById(R.id.textViewTomarCada);

        txtMedicina.setTypeface(typefaceLight);
        txtDescripcion.setTypeface(typefaceMedium);
        txtSiguienteToma.setTypeface(typefaceMedium);

        String TomarCada = String.format("Tomar cada %s horas..", mRecordatoriosList.get(position).getTomarCada());
        txtMedicina.setText(mRecordatoriosList.get(position).getMedicina().toString());
        txtDescripcion.setText(mRecordatoriosList.get(position).getDescripcion().toString());
        txtSiguienteToma.setText(TomarCada);

        return itemView;
    }

}
