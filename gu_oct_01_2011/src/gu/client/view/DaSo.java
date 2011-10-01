package gu.client.view;

import gu.client.Srv;
import gu.client.SrvAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.types.ListGridFieldType;

public class DaSo extends DataSource {
	private final SrvAsync srv = GWT.create(Srv.class);
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
		DataSourceIntegerField orig = new DataSourceIntegerField("orig",
				"ms to Orig");
		DataSourceIntegerField dest = new DataSourceIntegerField("dest",
				"ms to Dest");
		DataSourceFloatField shiplat = new DataSourceFloatField("shiplat",
				"ShipLat");
		DataSourceFloatField shiplng = new DataSourceFloatField("shiplng",
				"ShipLng");
		DataSourceFloatField conslat = new DataSourceFloatField("conslat",
				"ConsLat");
		DataSourceFloatField conslng = new DataSourceFloatField("conslng",
				"ConsLng");
		DataSourceTextField prefix = new DataSourceTextField("prefix", "Prefix");
		prefix.setRequired(true);
		//DataSourceTextField nord = new DataSourceTextField("nord",
		//"number of WOs");

		DataSourceTextField woNumber = new DataSourceTextField("woNumber",
		"WO number");

		woNumber.setRequired(true);
		DataSourceTextField from1 = new DataSourceTextField("from1", "FROM: City");
		DataSourceTextField from2 = new DataSourceTextField("from2", "Prov/State");
		DataSourceTextField to1 = new DataSourceTextField("to1", "TO: City");
		DataSourceTextField to2 = new DataSourceTextField("to2", "Prov/State");
		DataSourceTextField equipment = new DataSourceTextField("equipment",
				"Equipment");
		DataSourceIntegerField pieces = new DataSourceIntegerField("pieces",
				"Pieces");
		pieces.setType(FieldType.INTEGER);
		DataSourceTextField type = new DataSourceTextField("type", "Type");
		DataSourceTextField description = new DataSourceTextField(
				"description", "Description");
		DataSourceIntegerField lbs = new DataSourceIntegerField("lbs",
				"Weight (Lbs)");
		lbs.setType(FieldType.INTEGER);
		DataSourceDateField dd = new DataSourceDateField("dd", "Delivery Date");
		dd.setType(FieldType.DATE);
		dd.setRequired(false);
		DataSourceDateField shd = new DataSourceDateField("shd",
				"Shipping Date");
		shd.setType(FieldType.DATE);
		shd.setRequired(false);
//		setFields(pkField, ii, orig, dest, prefix, woNumber, from1, from2, to1,
//				to2, shd, dd, equipment, pieces, type, lbs, description);
		setFields(pkField, ii, prefix, woNumber, from1, from2, to1,
				to2, shd, dd, equipment, pieces, type, lbs, description);
		setDataURL("qq");
		setClientOnly(true);

	}
}