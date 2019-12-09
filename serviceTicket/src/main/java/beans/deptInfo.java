package beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="deptInfo")
public class deptInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deptNo;
	
	private String deptName;
	
	public int getDeptNo() {
		return deptNo;
	}
	@Override
	public String toString() {
		return "deptInfo [deptNo=" + deptNo + ", deptName=" + deptName + "]";
	}
	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
}
