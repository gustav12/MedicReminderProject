package com.medic.reminder;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class XmlParser extends DefaultHandler {

    private static String FileName;
    InfoSucursal Item = null;
    String currentValue = "";
    Boolean currentElement = false;
    private ArrayList<InfoSucursal> lstSucursales = new ArrayList<InfoSucursal>();

    public ArrayList<InfoSucursal> getLstSucursales() {
        return lstSucursales;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        currentValue = "";
        currentElement = true;

        if (localName.equals("sucursal")) {
            Item = new InfoSucursal();
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        currentElement = false;

        /** set value */
        if (localName.equalsIgnoreCase("descripcion"))
            Item.setSucursal(currentValue);
        else if (localName.equalsIgnoreCase("direccion"))
            Item.setDireccion(currentValue);
        else if (localName.equalsIgnoreCase("telefonos"))
            Item.setTelefonos(currentValue);
        else if (localName.equalsIgnoreCase("sucursal"))
            lstSucursales.add(Item);
    }

    // Called to get tag characters
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentElement == true) {
            currentValue = currentValue + new String(ch, start, length);
        }
    }

}