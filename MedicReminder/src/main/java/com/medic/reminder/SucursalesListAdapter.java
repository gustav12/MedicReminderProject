package com.medic.reminder;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class SucursalesListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<InfoSucursal> mSucursalesList;

    public SucursalesListAdapter(Context context, ArrayList<InfoSucursal> sucursalesList) {
        mContext = context;
        mSucursalesList = sucursalesList;
    }

    @Override
    public int getCount() {
        return mSucursalesList.size();
    }

    @Override
    public InfoSucursal getItem(int position) {
        return mSucursalesList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        RelativeLayout itemView;
        itemView = (RelativeLayout) mLayoutInflater.inflate(R.layout.row_view, viewGroup, false);
        itemView.setBackgroundResource(R.drawable.rounded_corners);

        Typeface typefaceLight = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Light.ttf");
        Typeface typefaceMedium = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Medium.ttf");

        TextView txtSucursal = (TextView) itemView.findViewById(R.id.textViewSucursal);
        TextView txtDireccion = (TextView) itemView.findViewById(R.id.textViewDireccion);
        TextView txtTelefonos = (TextView) itemView.findViewById(R.id.textViewTelefonos);

        txtSucursal.setTypeface(typefaceLight);
        txtDireccion.setTypeface(typefaceMedium);

        txtSucursal.setText(mSucursalesList.get(position).getSucursal().toString());
        txtDireccion.setText(mSucursalesList.get(position).getDireccion().toString());
        txtTelefonos.setText(mSucursalesList.get(position).getTelefonos().toString());

        return itemView;
    }
}
