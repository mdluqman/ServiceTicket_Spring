package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import BeanClasses.EndUserBean;
import BeanClasses.ServiceEngineerBean;
import BeanClasses.UserBean;
import BeanClasses.deptInfo;

public interface EndUserRepository extends JpaRepository<EndUserBean, String>{

	@Query("select e from EndUserBean e where e.username= :uname order by  field(ticketStatus,'New','Waiting','Completed') , ticketPriority desc , dateOfIssue desc ")
	List<EndUserBean> gettickets(@Param("uname") UserBean userBean);
	
	@Query("select e from EndUserBean e where e.dept= :deptid order by ticketPriority desc , dateOfIssue desc")
	List<EndUserBean> assigntickets(@Param("deptid") deptInfo did);
	
	@Query("select e from EndUserBean e where e.serviceengineer= :seid order by  field(ticketStatus,'New','Waiting','Completed') , ticketPriority desc , dateOfIssue desc ")
	List<EndUserBean> getsetickets(@Param("seid") ServiceEngineerBean serviceEngineerBean);
	
//	@Query("select e from EndUserBean e where e.serviceengineer= :seid order by  field(ticketStatus,'New','Waiting','Completed') , ticketPriority desc , dateOfIssue desc ")
//	List<EndUserBean> getsetickets(@Param("seid") String serviceEngineerBean);
	
	@Query(value="select (AVG(TIMESTAMPDIFF(day,  dateOfCompletion, dateOfAction))) from EndUserBean where ticketStatus = ?1 and ticketPriority=?2", nativeQuery = true)
	String ReportperS(String stat,int prio);
	
	@Query(value="select (AVG(TIMESTAMPDIFF(day, dateOfCompletion, dateOfAction))) from EndUserBean  where ticketStatus = ?1 and serviceengineer_ServiceEngineerId=?2", nativeQuery = true)
	String ReportperSE(String stat,String string);
	
	@Query(value="select TIMESTAMPDIFF(day, dateOfIssue, sysdate()) from EndUserBean where ticketId = ?1", nativeQuery = true)
	String avgage(String tid);
	
//	-----------------------------------====================----------------------------------
	
	@Query(value="select * from EndUserBean where serviceengineer_ServiceEngineerId =?1 and ticketStatus=?2 order by ticketPriority desc", nativeQuery = true)
	List<EndUserBean> getwaitORpendtickets(String seid,String status);
	
	@Query(value="select * from EndUserBean where customerUsername =?1 order by field(ticketStatus,'New','Waiting','Completed')", nativeQuery = true)
	List<EndUserBean> findbyname(String string);
	
	
//	------------------------------------=====================---------------------------------
	
	@Query(value = "select * from EndUserBean  where serviceengineer_ServiceEngineerId= ?1 and ticketStatus= ?2 and customerUsername!= ?3  order by  ticketPriority desc , dateOfIssue desc ", nativeQuery = true)
	List<EndUserBean> getsewaitingtickets(String string,String status,UserBean user);
}
