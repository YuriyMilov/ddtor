package gu.client.model;

import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Shipper implements BaseObject {
	private static final long serialVersionUID = 1L;
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
	

	@Persistent
	private String sName;
	public String getName() {
		return sName;
	}
	public void setName(String sName) {
		this.sName = sName;
	}	
 
	@Persistent
	private String sCity;
	public String getCity() {
		return sCity;
	}
	public void setCity(String sCity) {
		this.sCity = sCity;
	}

	@Persistent
	private String sProv;
	public String getProv() {
		return sProv;
	}
	public void setProv(String sProv) {
		this.sProv = sProv;
	}

	@Persistent
	private String sPostalCode;
	public String getPostalCode() {
		return sPostalCode;
	}
	public void setPostalCode(String sPostalCode) {
		this.sPostalCode = sPostalCode;
	}
	@Persistent
	private String sLatitude;
	public String getLatitude() {
		return sLatitude;
	}
	public void setLatitude(String sLatitude) {
		this.sLatitude = sLatitude;
	}
	@Persistent
	private String sLongtitude;
	public String getLongtitude() {
		return sLongtitude;
	}
	public void setLongtitude(String sLongtitude) {
		this.sLongtitude = sLongtitude;
	}


}
