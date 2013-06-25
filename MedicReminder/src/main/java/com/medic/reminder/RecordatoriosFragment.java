package com.medic.reminder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;

public class RecordatoriosFragment extends Fragment {

    private static final int RESULT_OK = 1;
    private static final int REQUEST_CODE = 1;
    private RecordatoriosListAdapter myRecordatoriosAdapter;
    private ArrayList<InfoRecordatorio> lstRecordatorios = null;

    private DAL mDAL;
    private Context mContext;
    private ListView listViewRecordatorios;

    public RecordatoriosFragment(Context context) {
        this.mContext = context;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        menu.setHeaderTitle("Acciones");
        menu.setHeaderIcon(android.R.drawable.ic_menu_more);
        inflater.inflate(R.menu.recordatorio_contextmenu, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recordatorios_fragment, container, false);

        if (rootView != null) {
            listViewRecordatorios = (ListView) rootView.findViewById(R.id.listViewRecordatorios);
        }
        registerForContextMenu(listViewRecordatorios);

        TextView txtHeader = (TextView) rootView.findViewById(R.id.txtHeader);

        Typeface typeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Light.ttf");
        txtHeader.setTypeface(typeface);

        try {
            CargarRecordatorios();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        Button btnAgregarNuevo = (Button) rootView.findViewById(R.id.btnAgregarNuevoRecordatorio);
        btnAgregarNuevo.setTypeface(typeface);
        btnAgregarNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NuevoRecordatorio.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if (resultCode == RESULT_OK) {

                CargarRecordatorios();

            }
        }
    }//onActivityResult

    private void CargarRecordatorios(){

        mDAL = new DAL(mContext);
        try {

            mDAL.open();
            lstRecordatorios = mDAL.RecuperarTodos();
            mDAL.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        myRecordatoriosAdapter = new RecordatoriosListAdapter(mContext, lstRecordatorios);
        listViewRecordatorios.setAdapter(myRecordatoriosAdapter);
        myRecordatoriosAdapter.notifyDataSetChanged();
    }

    private Boolean BorrarRecordatorio(InfoRecordatorio item){
        mDAL = new DAL(mContext);
        try {
            mDAL.open();
            mDAL.BorrarRecordatorio(item);
            mDAL.close();
            myRecordatoriosAdapter.notifyDataSetChanged();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.BorrarItem:
                InfoRecordatorio recordatorio = lstRecordatorios.get(info.position);
                if (recordatorio != null){
                    BorrarItemSeleccionado(recordatorio, info.position);
                }
                break;
        }
        return true;
    }

    private void BorrarItemSeleccionado(final InfoRecordatorio item, final int position){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Borrar Recordatorio");
        builder.setMessage("Confirma eliminar..?");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (BorrarRecordatorio(item) == true){
                    lstRecordatorios.remove(position);
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        try {
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }//BorrarItemSeleccionado


}
