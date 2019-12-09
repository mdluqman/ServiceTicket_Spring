package Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import embedded.MarksEmbeddedKey;

@Entity
@Table(name="Marks")
@IdClass(MarksEmbeddedKey.class)
public class Marks {

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="username",  referencedColumnName = "username")
	private LoginCredentials username;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="subject_ID", referencedColumnName = "subject_ID")
	private Subjects subject_ID;
	
	private int mid1;
	private int mid2;
	private int labInternals;
	private int labExternals;
	private int semester;
	private String section;
	private String semGrades;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="branch_ID")
	private Branch branch;
	
	public Marks() {
		
	}

	public LoginCredentials getUsername() {
		return username;
	}

	public void setUsername(LoginCredentials username) {
		this.username = username;
	}

	public Subjects getSubject_ID() {
		return subject_ID;
	}

	public void setSubject_ID(Subjects subject_ID) {
		this.subject_ID = subject_ID;
	}

	public int getMid1() {
		return mid1;
	}

	public void setMid1(int mid1) {
		this.mid1 = mid1;
	}

	public int getMid2() {
		return mid2;
	}

	public void setMid2(int mid2) {
		this.mid2 = mid2;
	}

	public int getLabInternals() {
		return labInternals;
	}

	public void setLabInternals(int labInternals) {
		this.labInternals = labInternals;
	}

	public int getLabExternals() {
		return labExternals;
	}

	public void setLabExternals(int labExternals) {
		this.labExternals = labExternals;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getSemGrades() {
		return semGrades;
	}

	public void setSemGrades(String semGrades) {
		this.semGrades = semGrades;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "Marks [username=" + username + ", subject_ID=" + subject_ID + ", mid1=" + mid1 + ", mid2=" + mid2
				+ ", labInternals=" + labInternals + ", labExternals=" + labExternals + ", semester=" + semester
				+ ", section=" + section + ", semGrades=" + semGrades + ", branch=" + branch + "]";
	}
	
	
	
}
