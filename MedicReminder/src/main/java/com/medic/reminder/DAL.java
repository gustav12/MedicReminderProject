package com.medic.reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAL {

    private SQLiteDatabase mSQLiteDatabase;
    private  MySQLiteHelper mySQLiteHelper;
    private String[] allColumns = {mySQLiteHelper.COLUMNA_ID,
            mySQLiteHelper.COLUMNA_MEDICINA,
            mySQLiteHelper.COLUMNA_DESCRIPCION,
            mySQLiteHelper.COLUMNA_HORAINICIO,
            mySQLiteHelper.COLUMNA_TOMARCADA};

    public DAL(Context context){
        mySQLiteHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        mSQLiteDatabase = mySQLiteHelper.getWritableDatabase();
    }

    public void close(){
        mySQLiteHelper.close();
    }

    public InfoRecordatorio CrearNuevoRecordatorio(InfoRecordatorio recordatorio)
            throws SQLException {

        ContentValues values = new ContentValues();

        values.put(mySQLiteHelper.COLUMNA_MEDICINA, recordatorio.getMedicina());
        values.put(mySQLiteHelper.COLUMNA_DESCRIPCION, recordatorio.getDescripcion());
        values.put(mySQLiteHelper.COLUMNA_HORAINICIO, recordatorio.getHoraInicio());
        values.put(mySQLiteHelper.COLUMNA_TOMARCADA, recordatorio.getTomarCada());

        int insertedID = (int) mSQLiteDatabase.insert(mySQLiteHelper.TABLA_RECORDATORIOS, null, values);

        Cursor cursor = mSQLiteDatabase.query(mySQLiteHelper.TABLA_RECORDATORIOS,
                allColumns, //select *
                mySQLiteHelper.COLUMNA_ID + " = '" + insertedID + "'", //where
                null, null, null,
                mySQLiteHelper.COLUMNA_MEDICINA); // order by

        cursor.moveToFirst();
        recordatorio.setID(insertedID);
        cursor.close();

        return recordatorio;
    }

    public boolean BorrarRecordatorio(InfoRecordatorio recordatorio)
            throws SQLException {

            return  mSQLiteDatabase.delete(mySQLiteHelper.TABLA_RECORDATORIOS,
                    mySQLiteHelper.COLUMNA_ID + "= '" + recordatorio.getID() + "'", null) > 0;
    }

    public ArrayList<InfoRecordatorio> RecuperarTodos(){
        ArrayList<InfoRecordatorio> lst = new ArrayList<InfoRecordatorio>();

        Cursor cursor = mSQLiteDatabase.query(mySQLiteHelper.TABLA_RECORDATORIOS,
                allColumns,  //select *
                null, null, null, null,
                mySQLiteHelper.COLUMNA_MEDICINA); // order by

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){

            InfoRecordatorio item = new InfoRecordatorio();
            item.setID(cursor.getInt(0));
            item.setMedicina(cursor.getString(1));
            item.setDescripcion(cursor.getString(2));
            item.setHoraInicio(cursor.getString(3));
            item.setTomarCada(cursor.getInt(4));

            lst.add(item);
            cursor.moveToNext();
        }
        cursor.close();

        return  lst;
    }

}
