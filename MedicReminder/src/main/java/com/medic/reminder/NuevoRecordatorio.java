package com.medic.reminder;

import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.text.InputFilter;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.SQLException;

public class NuevoRecordatorio extends Activity {

    private EditText txtMedicina;
    private EditText txtDescripcion;
    private EditText txtRepeticion;
    private TimePicker timePicker;

    private DAL mDAL;

    private static final int RESULT_CANCELED = 0;
    private static final int RESULT_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_recordatorio);

        SetupViews();
    }

    private void SetupViews() {

        txtMedicina = (EditText) findViewById(R.id.txtNombreMedicamento);
        txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);
        txtRepeticion = (EditText) findViewById(R.id.txtRepeticion);
        TextView lblTomarCada = (TextView)findViewById(R.id.lblTomarCada);
        TextView lblHs = (TextView)findViewById(R.id.lblHs);
        TextView lblHoraInicio = (TextView)findViewById(R.id.lblHoraInicio);
        Button btnGuardar = (Button)findViewById(R.id.btnGuardar);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        Typeface typeface = Typeface.createFromAsset(this.getAssets(), "fonts/Roboto-Light.ttf");

        txtMedicina.setTypeface(typeface);
        txtDescripcion.setTypeface(typeface);
        txtRepeticion.setTypeface(typeface);
        lblTomarCada.setTypeface(typeface);
        lblHs.setTypeface(typeface);
        lblHoraInicio.setTypeface(typeface);
        btnGuardar.setTypeface(typeface);

        // setear ingreso numérico solo (1-24)
        txtRepeticion.setFilters(new InputFilter[]{new InputFilterMinMax(1, 24)});

        // validar datos ingresados
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtMedicina.getText().length() == 0){
                    Toast.makeText(getApplicationContext(),
                            "Debe ingresar el nombre del Medicamento", Toast.LENGTH_LONG).show();
                    txtMedicina.requestFocus();
                    return;
                }

                if (txtRepeticion.getText().length() == 0){
                    Toast.makeText(getApplicationContext(),
                            "Ingrese intervalo de horas para tomar el medicamento", Toast.LENGTH_LONG).show();
                    txtRepeticion.requestFocus();
                    return;
                }

                Guardar();

            }// onClick()
        });//setOnClickListener()

    }//SetupViews()


    private void Guardar(){
        InfoRecordatorio item = new InfoRecordatorio();

        item.setMedicina(this.txtMedicina.getText().toString());
        item.setDescripcion(this.txtDescripcion.getText().toString());
        item.setTomarCada(Integer.parseInt(this.txtRepeticion.getText().toString()));

        int Hora, Minuto;
        String HoraInicio;
        Hora = this.timePicker.getCurrentHour();
        Minuto = this.timePicker.getCurrentMinute();

        HoraInicio = String.valueOf(Hora) + String.valueOf(Minuto);
        item.setHoraInicio(HoraInicio);

        try {

            mDAL = new DAL(this);
            mDAL.open();
            mDAL.CrearNuevoRecordatorio(item);
            setResult(RESULT_OK);
            finish();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
}
