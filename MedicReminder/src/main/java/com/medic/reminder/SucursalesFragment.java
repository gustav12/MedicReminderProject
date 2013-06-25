package com.medic.reminder;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SucursalesFragment extends Fragment {

    private Context mContext;
    public SucursalesFragment(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sucursales_fragment, container, false);

        try {

            AssetManager assetManager = mContext.getAssets();
            InputStream inputStream = assetManager.open("sucursales.xml");

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();

            XmlParser myHandler = new XmlParser();
            xmlReader.setContentHandler(myHandler);

            InputSource inputSource = new InputSource(inputStream);
            xmlReader.parse(inputSource);

            ArrayList<InfoSucursal> lstSucursales = myHandler.getLstSucursales();

            ListView listView = (ListView) rootView.findViewById(R.id.listViewSucursales);
            listView.setAdapter(new SucursalesListAdapter(mContext, lstSucursales));

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return rootView;
    }

}
