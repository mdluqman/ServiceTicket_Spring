package Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import embedded.AbsenteesEmbeddedKey;

@Entity
@Table(name="Absentees")
@IdClass(AbsenteesEmbeddedKey.class)
public class Absentees {

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="username",  referencedColumnName = "username")
	private LoginCredentials username;
	
	@Id
	private String date;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="subject_ID", referencedColumnName = "subject_ID")
	private Subjects subject_ID;
	
	public LoginCredentials getUsername() {
		return username;
	}
	public void setUsername(LoginCredentials username) {
		this.username = username;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Absentees [username=" + username + ", date=" + date + ", subject_ID=" + subject_ID + "]";
	}
	public Subjects getSubject_ID() {
		return subject_ID;
	}
	public void setSubject_ID(Subjects subject_ID) {
		this.subject_ID = subject_ID;
	}
	
	
}
