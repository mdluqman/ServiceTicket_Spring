package Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import embedded.StudentAndProfessorDetailsEmbeddedKey;

@Entity
@Table(name="StudentAndProfessorDetails")
@IdClass(StudentAndProfessorDetailsEmbeddedKey.class)
public class StudentAndProfessorDetails {
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="username",  referencedColumnName = "username")
	private LoginCredentials username;
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="branch_ID", referencedColumnName = "branch_ID")
	private Branch branch_ID;
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="subject_ID", referencedColumnName = "subject_ID")
	private Subjects subject_ID;
	@Id
	private String section;
	private int semester;
	private int periods;
	
	public StudentAndProfessorDetails()
	{
		
	}

	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public int getPeriods() {
		return periods;
	}
	public void setPeriods(int periods) {
		this.periods = periods;
	}

	public LoginCredentials getUsername() {
		return username;
	}

	public void setUsername(LoginCredentials username) {
		this.username = username;
	}

	public Branch getBranch_ID() {
		return branch_ID;
	}

	public void setBranch_ID(Branch branch_ID) {
		this.branch_ID = branch_ID;
	}

	public Subjects getSubject_ID() {
		return subject_ID;
	}

	public void setSubject_ID(Subjects subject_ID) {
		this.subject_ID = subject_ID;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	@Override
	public String toString() {
		return "StudentAndProfessorDetails [username=" + username + ", branch_ID=" + branch_ID + ", subject_ID="
				+ subject_ID + ", section=" + section + ", semester=" + semester + ", periods=" + periods + "]";
	}
	
	
	

}
