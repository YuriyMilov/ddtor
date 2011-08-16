package cc;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Dollar implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	protected Long ident;
	
	@Persistent
	private String s;
	
	@Persistent
	private Date d;

	public Dollar(String s2, Date d2) {
		this.s = s2;
		this.d = d2;
	}
	public String get_rate() {
		if( s == null ) return null;
		return s;
	}
	public Date get_date() {
		if( d == null ) return null;
		return d;
	}

	public void set_rate(String s2) {
		this.s = s2;
	}

	public void set_date(Date d2) {
		this.d = d2;
	}

}
