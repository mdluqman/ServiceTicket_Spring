package beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ServiceEngineerBean")
@NamedNativeQueries({
		@NamedNativeQuery(name = "selectengineers", query = "SELECT * from ServiceEngineerBean serviceengineer", resultClass = ServiceEngineerBean.class) })
public class ServiceEngineerBean {
	@Id
	private String ServiceEngineerId;
	private int totalTickets;
	private String currentHighPrioityTicketId;
	private int pending;

	@OneToOne
	@JoinColumn(name = "SEusername")
	UserBean SEusername = new UserBean();
	@ManyToOne
	@JoinColumn(name = "deptNo")
	private deptInfo dept;

	public UserBean getSEusername() {
		return SEusername;
	}

	public void setSEusername(UserBean sEusername) {
		SEusername = sEusername;
	}

	public String getServiceEngineerId() {
		return ServiceEngineerId;
	}

	public void setServiceEngineerId(String serviceEngineerId) {
		ServiceEngineerId = serviceEngineerId;
	}

	public int getTotalTickets() {
		return totalTickets;
	}

	public void setTotalTickets(int totalTickets) {
		this.totalTickets = totalTickets;
	}

	public String getCurrentHighPrioityTicketId() {
		return currentHighPrioityTicketId;
	}

	public void setCurrentHighPrioityTicketId(String currentHighPrioityTicketId) {
		this.currentHighPrioityTicketId = currentHighPrioityTicketId;
	}

	public int getPending() {
		return pending;
	}

	public void setPending(int pending) {
		this.pending = pending;
	}

	public deptInfo getDept() {
		return dept;
	}

	public void setDept(deptInfo dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "ServiceEngineerBean [ServiceEngineerId=" + ServiceEngineerId + ", totalTickets=" + totalTickets
				+ ", currentHighPrioityTicketId=" + currentHighPrioityTicketId + ", pending=" + pending
				+ ", SEusername=" + SEusername + ", dept=" + dept + "]";
	}
}
