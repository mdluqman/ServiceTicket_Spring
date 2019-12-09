package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Branch")
public class Branch {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int branch_ID;
	private String branchName;
	public Branch(int branch_ID2) {
		branch_ID = branch_ID2;
	}
	public Branch() {
		super();
	}
	public int getBranch_ID() {
		return branch_ID;
	}
	public void setBranch_ID(int branch_ID) {
		this.branch_ID = branch_ID;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	@Override
	public String toString() {
		return "Branch [branch_ID=" + branch_ID + ", branchName=" + branchName + "]";
	}
	
	
}
