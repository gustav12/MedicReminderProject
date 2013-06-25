package com.medic.reminder;

public class InfoRecordatorio {

    private String Medicina = null;
    private String Descripcion = null;
    private String HoraInicio ;
    private int TomarCada ;
    private int ID;

    public void setID(int id){
        this.ID = id;
    }

    public void setMedicina(String medicina){
        this.Medicina = medicina;
    }

    public void setDescripcion(String descripcion){
        this.Descripcion = descripcion;
    }

    public void setHoraInicio(String horaInicio){
        this.HoraInicio = horaInicio;
    }

    public void setTomarCada(int tomarCada){
        this.TomarCada = tomarCada;
    }

    public int getID(){
        return this.ID;
    }

    public String getMedicina(){
        return this.Medicina;
    }

    public String getDescripcion(){
        return this.Descripcion;
    }

    public String getHoraInicio(){
        return this.HoraInicio;
    }

    public int getTomarCada(){
        return this.TomarCada;
    }

}
