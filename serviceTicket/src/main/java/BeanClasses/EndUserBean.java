package BeanClasses;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;


@Entity
@Table(name="EndUserBean")
@NamedNativeQueries({@NamedNativeQuery(name="selectDepartments", query="SELECT * from deptInfo dept", resultClass = deptInfo.class)})

public class EndUserBean {
	
	@Id
	private String ticketId;
	private String ticketPriority;
	private String ticketStatus;
//	private String customerUsername;
	private String workStation;
	private String dateOfIssue;
	private String requestedEndDAte;
	private String dateOfAction;
	private String dateOfCompletion;
	private String subject;
	
	public EndUserBean()
	{
		
	}
	public EndUserBean(String ticketId2, UserBean customerUsername2, String string, String string2, String string3,
			String string4, String ticketPriority2, String ticketStatus2, String workStation2, deptInfo deptNo,
			ServiceEngineerBean serviceEngineerId, String subject2) {
		// TODO Auto-generated constructor stub
		this.ticketId = ticketId2;
//		this.customerUsername = customerUsername2;
		this.username=customerUsername2;
		this.dateOfAction = string;
		this.dateOfCompletion=string2;
		this.dateOfIssue=string3;
		this.requestedEndDAte=string4;
		this.ticketPriority = ticketPriority2;
		this.ticketStatus = ticketStatus2;
		this.workStation = workStation2;
		this.dept = deptNo;
		this.serviceengineer=serviceEngineerId;
		this.subject=subject2;
	}



	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketPriority() {
		return ticketPriority;
	}

	public void setTicketPriority(String ticketPriority) {
		this.ticketPriority = ticketPriority;
	}

	public String getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(String ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public String getWorkStation() {
		return workStation;
	}

	public UserBean getusername() {
		return username;
	}
	public void setusername(UserBean username) {
		this.username = username;
	}
	public void setWorkStation(String workStation) {
		this.workStation = workStation;
	}


	public String getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(String dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getRequestedEndDAte() {
		return requestedEndDAte;
	}

	public void setRequestedEndDAte(String requestedEndDAte) {
		this.requestedEndDAte = requestedEndDAte;
	}

	public String getDateOfAction() {
		return dateOfAction;
	}

	public void setDateOfAction(String dateOfAction) {
		this.dateOfAction = dateOfAction;
	}

	public String getDateOfCompletion() {
		return dateOfCompletion;
	}

	public void setDateOfCompletion(String dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
	}

	public ServiceEngineerBean getServiceengineer() {
		return serviceengineer;
	}

	public void setServiceengineer(ServiceEngineerBean serviceengineer) {
		this.serviceengineer = serviceengineer;
	}

	public deptInfo getDept() {
		return dept;
	}

	public void setDept(deptInfo dept) {
		this.dept = dept;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@ManyToOne
	ServiceEngineerBean serviceengineer;
	
	@ManyToOne
	deptInfo dept;
	
	@ManyToOne
	@JoinColumn(name="customerUsername")
	UserBean username;
	
	

}
