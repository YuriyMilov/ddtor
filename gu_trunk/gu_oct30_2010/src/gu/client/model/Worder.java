package gu.client.model;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Worder implements BaseObject {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	protected Long id;

	public String getId() {
		if( id == null ) return null;
		return id.toString();
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	

	public Worder(String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, String s11, String s12, String s13, String s14, String s15, String s16) {
		this.wo_number = s1;
		this.ship_id = s2;
		this.cons_id = s3;
		this.customer_name = s4;
		this.equipment_description = s5;
		this.delivery_dt = s6;
		this.pickup_dt = s7;
		this.pieces = s8;
		this.type = s9;
		this.weight_lbs = s10;
		this.weight_kgs = s11;
		this.bol = s12;
		this.desc = s16;
		
		//String	wotype=s13;
		//String	ponum=s14;
		//String	actual_pickup_dt=s15;
		//String	actual_delivr_dt=s16;

	}
	
	public Worder() 
	{
		}
	
	@Persistent
	private String title;
	@Persistent
	private String url;
	@Persistent
	private String equipment_description;
	@Persistent
	private String user_id;

    @Persistent
    private List<Long> digKeys;
    
	@NotPersistent
	private List<User> digs;
	
	public String getDescription() {
		return equipment_description;
	}
	public void setDescription(String equipment_description) {
		this.equipment_description = equipment_description;
	}	
	public List<User> getDigs() {
		return digs;
	}
	public void setDigs(List<User> digs) {
		this.digs = digs;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public List<Long> getDigKeys() {
		return digKeys;
	}
	public void setDigKeys(List<Long> digKeys) {
		this.digKeys = digKeys;
	}
	
	
	

	@Persistent
	private String customer_name;
public String get_customer_name() {
		if( customer_name == null ) return null;
		return customer_name;
	}
	public void set_customer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	

	//@Persistent
	//private String equipment_description;
	//public String get_equipment_description() {
	//	if( equipment_description == null ) return null;
	//	return equipment_description;
	//}
	//public void set_equipment_description(String equipment_description) {
	//	this.equipment_description = equipment_description;
	//}
	
	@Persistent
	private String desc;
	public String get_desc() {
		if( desc == null ) return null;
		return desc;
	}
	public void set_desc(String desc) {
		this.desc = desc;
	}
	
	@Persistent
	private String pieces;
	public String get_pieces() {
		if( pieces == null ) return null;
		return pieces;
	}
	public void set_pieces(String pieces) {
		this.pieces = pieces;
	}
	
	
	@Persistent
	private String type;
	public String get_type() {
		if( type == null ) return null;
		return type;
	}
	public void set_type(String type) {
		this.type = type;
	}

	
	@Persistent
	private String weight_lbs;
	public String get_weight_lbs() {
		if( weight_lbs == null ) return null;
		return weight_lbs;
	}
	public void set_weight_lbs(String weight_lbs) {
		this.weight_lbs = weight_lbs;
	}
	
	@Persistent
	private String weight_kgs;
	public String get_weight_kgs() {
		if( weight_kgs == null ) return null;
		return weight_kgs;
	}
	public void set_weight_kgs(String weight_kgs) {
		this.weight_kgs = weight_kgs;
	}

	
	@Persistent
	private String pickup_dt;
	public String getpickup_dt() {
		if( pickup_dt == null ) return null;
		return pickup_dt;
	}
	public void set_pickup_dt(String pickup_dt) {
		this.pickup_dt = pickup_dt;
	}

	
	@Persistent
	private String delivery_dt;
	public String get_delivery_dt() {
		if( delivery_dt == null ) return null;
		return delivery_dt;
	}
	public void set_delivery_dt(String delivery_dt) {
		this.delivery_dt = delivery_dt;
	}		

	
	@Persistent
	private String bol;
	public String get_bol() {
		if( bol == null ) return null;
		return bol;
	}
	public void set_bol(String bol) {
		this.bol = bol;
	}

	@Persistent
	private String wo_number;
	public String get_wo_number() {
		if( id == null ) return null;
		return wo_number;
	}
	public void set_wo_number(String wo_number) {
		this.wo_number = wo_number;
	}
	
	
	
	
	@Persistent
	private String ship_id;
	
	public String get_ship_id() {
		if( ship_id == null ) return null;
		return ship_id;
	}
	public void set_ship_id(String ship_id) {
		this.ship_id = ship_id;
	}
	
	
	
	
	@Persistent
	private String cons_id;
	
	public String get_cons_id() {
		if( cons_id == null ) return null;
		return cons_id;
	}
	public void set_cons_id(String cons_id) {
		this.cons_id = cons_id;
	}
	
}
