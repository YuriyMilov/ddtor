package qd.gmap;

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.users.User;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Mrkr4 {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	@Persistent
	private Date date;
	@Persistent
	private String s1;
	@Persistent
	private String s2;
	@Persistent
	private String s3;
	@Persistent
	private String s4;


	public Mrkr4(Date date,
			String s1, String s2, String s3,
			String s4) {
		this.date = date;
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;

	}

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}
	public String get_s1(){return s1;}
	public String get_s2(){return s2;}
	public String get_s3(){return s3;}
	public String get_s4(){return s4;}

		
}