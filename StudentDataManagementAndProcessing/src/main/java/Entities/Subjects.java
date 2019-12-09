package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Subjects")
public class Subjects {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int subject_ID;
	private String subjectName;
	
	public Subjects() {
		super();
	}
	public Subjects(int subject_ID2) {
		subject_ID = subject_ID2;
	}
	public int getSubject_ID() {
		return subject_ID;
	}
	public void setSubject_ID(int subject_ID) {
		this.subject_ID = subject_ID;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	@Override
	public String toString() {
		return "Subjects [subject_ID=" + subject_ID + ", subjectName=" + subjectName + "]";
	}
	
	
}
