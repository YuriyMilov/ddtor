package gu.client.view;

import com.google.gwt.maps.client.MapWidget;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;

public class DaSo extends DataSource {

    private static DaSo instance = null;

    public static DaSo getInstance() {
        if (instance == null) {
            instance = new DaSo("worldDS");
        }
        return instance;
    }

    public DaSo(String id) {

        setID(id);
        setRecordXPath("/List/country");
        DataSourceIntegerField pkField = new DataSourceIntegerField("pk");
        pkField.setHidden(true);
        pkField.setPrimaryKey(true);
        
        DataSourceBooleanField ii = new DataSourceBooleanField("ii", "Mapped");
        DataSourceIntegerField orig = new DataSourceIntegerField("orig", "kms to Orig");
        DataSourceIntegerField dest = new DataSourceIntegerField("dest", "kms to Dest");
        
        DataSourceFloatField shiplat = new DataSourceFloatField("shiplat", "ShipLat");
        DataSourceFloatField shiplng = new DataSourceFloatField("shiplng", "ShipLng");

        DataSourceFloatField conslat = new DataSourceFloatField("conslat", "ConsLat");
        DataSourceFloatField conslng = new DataSourceFloatField("conslng", "ConsLng");

        
        DataSourceTextField prefix = new DataSourceTextField("prefix", "Prefix");
        prefix.setRequired(true);
        
        DataSourceTextField woNumber = new DataSourceTextField("woNumber", "WO number");
        woNumber.setRequired(true);

        DataSourceTextField from = new DataSourceTextField("from", "From");
        //from.setRequired(true);

        DataSourceTextField to = new DataSourceTextField("to", "To");
        DataSourceTextField equipment = new DataSourceTextField("equipment", "Equipment");
        DataSourceIntegerField pieces = new DataSourceIntegerField("pieces", "Pieces");
        DataSourceTextField type = new DataSourceTextField("type", "Type");
        DataSourceTextField description = new DataSourceTextField("description", "Description");

        DataSourceIntegerField lbs = new DataSourceIntegerField("lbs", "Weight (Lbs)");
       //lbs



        //DataSourceBooleanField memberG8Field = new DataSourceBooleanField("member_g8", "G8");

        //DataSourceTextField continentField = new DataSourceTextField("continent", "Continent");
       // continentField.setValueMap("Europe", "Asia", "North America", "Australia/Oceania", "South America", "Africa");

        DataSourceDateField dd = new DataSourceDateField("dd", "Delivery Date");
        dd.setRequired(false);
        DataSourceDateField shd = new DataSourceDateField("shd", "Shipping Date");
        shd.setRequired(false);
        
       // DataSourceFloatField areaField = new DataSourceFloatField("area", "Area (km&sup2;)");
       // DataSourceIntegerField populationField = new DataSourceIntegerField("population", "Population");
        //DataSourceFloatField gdpField = new DataSourceFloatField("gdp", "GDP ($M)");
      //  map.setCanEdit(true);
		//       setFields(pkField, prefix, woNumber, from, to, shd, dd, equipment, pieces, type, lbs, description);

        //setFields(pkField, orig, dest, shiplat, shiplng, conslat, conslng, prefix, woNumber, from, to, shd, dd, equipment, pieces, type, lbs, description);

        setFields(pkField, ii, orig, dest, prefix, woNumber, from, to, shd, dd, equipment, pieces, type, lbs, description);
        setDataURL("qq");
        setClientOnly(true);
        
    }
}