package BeanClasses;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authentication")
public class UserBean {

	@Id
	private String username;
	private String passwords;
	@ManyToOne
	@JoinColumn(name = "userTypeId")
	private usertypeinfo usertype;	
	

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswords() {
		return passwords;
	}
	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}

	public usertypeinfo getUsertype() {
		return usertype;
	}
	public void setUsertype(usertypeinfo usertype) {
		this.usertype = usertype;
	}
	@Override
	public String toString() {
		return "UserBean [username=" + username + ", passwords=" + passwords + ", userTypeId=" + usertype + "]";
	}
	
}
