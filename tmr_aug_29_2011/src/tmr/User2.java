package tmr;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User2 implements BaseObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	protected Long ident;

	public String getId() {
		if( ident == null ) return null;
		return ident.toString();
	}
	public void setId(String id) {
		this.ident = Long.parseLong(id);
	}
	
	@Persistent
	private String name;
	@Persistent
	private String email;
	@Persistent
	private String password;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
