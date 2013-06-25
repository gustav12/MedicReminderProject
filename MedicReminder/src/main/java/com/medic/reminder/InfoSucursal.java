package com.medic.reminder;

public class InfoSucursal {

    private String Sucursal = null;
    private String Direccion = null;
    private String Telefonos = null;
    private String Logo = null;

    public void setSucursal(String sucursal){
        this.Sucursal = sucursal;
    }

    public void setDireccion(String direccion){
        this.Direccion = direccion;
    }

    public void setTelefonos(String telefonos){
        this.Telefonos = telefonos;
    }

    public String getSucursal(){
        return this.Sucursal;
    }

    public String getDireccion(){
        return this.Direccion;
    }

    public String getTelefonos(){
        return this.Telefonos;
    }
}
