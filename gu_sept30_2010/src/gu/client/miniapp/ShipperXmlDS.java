package gu.client.miniapp;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;

public class ShipperXmlDS extends DataSource {

    private static ShipperXmlDS instance = null;

    public static ShipperXmlDS getInstance() {
        if (instance == null) {
            instance = new ShipperXmlDS("supplyCategoryDS");
        }
        return instance;
    }

    public ShipperXmlDS(String id) {

        setID(id);
        setRecordXPath("/List/supplyCategory");


        DataSourceTextField itemNameField = new DataSourceTextField("categoryName", "Item", 128, true);
        itemNameField.setPrimaryKey(true);

        DataSourceTextField parentField = new DataSourceTextField("parentID", null);
        parentField.setHidden(true);
        parentField.setRequired(true);
        parentField.setRootValue("root");
        parentField.setForeignKey("supplyCategoryDS.categoryName");


        setFields(itemNameField, parentField);

        setDataURL("ds/test_data/supplyCategory.data.xml");
        
        setClientOnly(true);

    }
}