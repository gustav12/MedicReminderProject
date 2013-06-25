package com.medic.reminder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "recordatorios.db";
    public static final String TABLA_RECORDATORIOS = "recordatorios";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_MEDICINA = "medicina";
    public static final String COLUMNA_DESCRIPCION = "descripcion";
    public static final String COLUMNA_HORAINICIO = "horainicio";
    public static final String COLUMNA_TOMARCADA = "tomarcada";

    public MySQLiteHelper(Context context){//, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, TABLA_RECORDATORIOS, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Database creation sql statement
        String DATABASE_CREATE = String.format("create table %s (", TABLA_RECORDATORIOS);
        DATABASE_CREATE = DATABASE_CREATE + String.format("%s INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ", COLUMNA_ID);
        DATABASE_CREATE = DATABASE_CREATE + String.format("%s TEXT NOT NULL, ", COLUMNA_MEDICINA);
        DATABASE_CREATE = DATABASE_CREATE + String.format("%s TEXT NULL, ", COLUMNA_DESCRIPCION);
        DATABASE_CREATE = DATABASE_CREATE + String.format("%s TEXT NOT NULL, ", COLUMNA_HORAINICIO);
        DATABASE_CREATE = DATABASE_CREATE + String.format("%s INTEGER NOT NULL) ", COLUMNA_TOMARCADA);

        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLA_RECORDATORIOS);
        onCreate(sqLiteDatabase);
    }

}
