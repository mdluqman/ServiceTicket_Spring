package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LoginCredentials")
public class LoginCredentials {

	@Override
	public String toString() {
		return "LoginCredentials [username=" + username + ", password=" + password + ", usertype=" + usertype + "]";
	}
	@Id
	private String username;
	@Column(name="pass")
	private String password;
	private String usertype;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	
}
