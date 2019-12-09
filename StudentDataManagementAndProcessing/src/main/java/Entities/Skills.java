package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Skills {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int skillID;
	private String skill;
	public int getSkillID() {
		return skillID;
	}
	public void setSkillID(int skillID) {
		this.skillID = skillID;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	
}
