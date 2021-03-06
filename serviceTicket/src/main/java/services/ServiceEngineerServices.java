package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import beans.EndUserBean;
import beans.ServiceEngineerBean;
import beans.UserBean;
import exceptions.MethodNotAllowed;
import repositories.DeptRepository;
import repositories.EndUserRepository;
import repositories.LoginRepository;
import repositories.ServiceEngineerRepository;
import repositories.usertypeinfoRepository;

@Service
public class ServiceEngineerServices {
	@Autowired
	EndUserRepository eurepo;
	
	@Autowired
	LoginRepository urepo;

	@Autowired
	ServiceEngineerRepository serepo;

	@Autowired
	DeptRepository drepo;

	public List<EndUserBean> getsetickets(UserBean user) {
		List<ServiceEngineerBean> s = serepo.findSEbyname(user);
		ServiceEngineerBean eub = new ServiceEngineerBean();
		eub.setServiceEngineerId(s.get(0).getServiceEngineerId());
		EndUserBean e = new EndUserBean();
		e.setServiceengineer(eub);
		List<EndUserBean> l = eurepo.getsetickets(e.getServiceengineer());
		return l;
	}

	public List<String> ReportperS() {
		List<String> avgseverity = new ArrayList<String>();
		for (int i = 1; i <= 3; i++) {
			String a = eurepo.ReportperS("Completed", i);
			try {
				avgseverity.add(a.toString());
			} catch (NullPointerException e) {
				avgseverity.add("-----");
			}
		}
		return avgseverity;
	}

	public List<String> ReportperSE() {
		List<ServiceEngineerBean> s = serepo.findAll();
		List<String> avgseverity = new ArrayList<String>();
		for (int i = 0; i < s.size(); i++) {
			String a = eurepo.ReportperSE("Completed", s.get(i).getServiceEngineerId());
			avgseverity.add(s.get(i).getSEusername().getUsername());
			try {
				avgseverity.add(a.toString());
			} catch (NullPointerException e) {
				avgseverity.add("-----");
			}
		}
		return avgseverity;
	}

	public List<String> avgage(UserBean user) {
		System.out.println(user.toString());
//		user = urepo.findById(se.getSEusername().getUsername()).get();
		ServiceEngineerBean se = new ServiceEngineerBean();
		se.setSEusername(user);
		List<ServiceEngineerBean> s = serepo.findSEbyname(user);
		se.setServiceEngineerId(s.get(0).getServiceEngineerId());
		EndUserBean e = new EndUserBean();
		e.setServiceengineer(se);
		List<EndUserBean> opentickets = eurepo.getsetickets(e.getServiceengineer());
		List<String> avgseverity = new ArrayList<String>();
		for (int i = 0; i < opentickets.size(); i++) {
			if (opentickets.get(i).getTicketStatus().equals("Completed")) {
				continue;
			} else {
				avgseverity.add(opentickets.get(i).getTicketId());
				String age = eurepo.avgage(opentickets.get(i).getTicketId());
				avgseverity.add(age.toString());
			}
		}
		return avgseverity;
	}

	public void ChangeStat(EndUserBean e) {
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		Optional<EndUserBean> eul = eurepo.findById(e.getTicketId());
		if(eul.isEmpty())
		{
//			return 13;
			throw new MethodNotAllowed("Sorry!! but you are trying to deal with a dealt or an Incorrigible ticketid");
		}
		else {
		EndUserBean eubo = eul.get();
		Optional<ServiceEngineerBean> sel = serepo.findById(eubo.getServiceengineer().getServiceEngineerId());
		ServiceEngineerBean sebo = sel.get();
		// CHANGING STATUS TO WIP
		if (e.getTicketStatus().equals("WorkInProgress") && eubo.getTicketStatus().equals("New")) {
			eubo.setTicketStatus("WorkInProgress");
			eubo.setDateOfAction(sqlDate.toString());
			sebo.setCurrentHighPrioityTicketId(eubo.getTicketId());
			eurepo.save(eubo);
			serepo.save(sebo);
//			return 1;
		}
		// CHANGING STATUS FROM NEW TO WAITING
		else if (e.getTicketStatus().equals("Waiting") && eubo.getTicketStatus().equals("New")) {
			List<EndUserBean> list1 = eurepo.getwaitORpendtickets(sebo.getServiceEngineerId(), "Waiting");
			if (list1.size() > 0) {
				Optional<EndUserBean> ob1 = eurepo.findById(list1.get(0).getTicketId());
				EndUserBean eubo1 = ob1.get();
				eubo1.setTicketStatus("New");
				sebo.setCurrentHighPrioityTicketId(list1.get(0).getTicketId());
				eubo.setTicketStatus("Waiting");
				eurepo.save(eubo);
				eurepo.save(eubo1);
				serepo.save(sebo);
//				return 1;
			} else {
				sebo.setCurrentHighPrioityTicketId("0");
				eubo.setTicketStatus("Waiting");
				eurepo.save(eubo);
				serepo.save(sebo);
//				return 1;
			}
		}
		// changing status FROM WORK_IN_PROGRESS to waiting
		else if (e.getTicketStatus().equals("Waiting") && eubo.getTicketStatus().equals("WorkInProgress")) {
			List<EndUserBean> list3 = eurepo.getwaitORpendtickets(sebo.getServiceEngineerId(), "Waiting");
			if (list3.size() > 0) {
				Optional<EndUserBean> ob1 = eurepo.findById(list3.get(0).getTicketId());
				EndUserBean eubo1 = ob1.get();
				eubo1.setTicketStatus("New");
				sebo.setCurrentHighPrioityTicketId(list3.get(0).getTicketId());
				eurepo.save(eubo1);
				serepo.save(sebo);
				eubo.setTicketStatus("Waiting");
				eurepo.save(eubo);
//				return 1;
			} else {
				sebo.setCurrentHighPrioityTicketId("0");
				serepo.save(sebo);
				eubo.setTicketStatus("Waiting");
				eurepo.save(eubo);
//				return 1;
			}
		}
		// changing status TO WORK_IN_PROGRESS from waiting
		else if (e.getTicketStatus().equals("WorkInProgress") && eubo.getTicketStatus().equals("Waiting")) {
			List<EndUserBean> list3 = eurepo.getwaitORpendtickets(sebo.getServiceEngineerId(), "New");
			if (list3.size() > 0) {
				Optional<EndUserBean> ob1 = eurepo.findById(list3.get(0).getTicketId());
				EndUserBean eubo1 = ob1.get();
				eubo1.setTicketStatus("Waiting");
				sebo.setCurrentHighPrioityTicketId(list3.get(0).getTicketId());
				eurepo.save(eubo1);
				serepo.save(sebo);
				eubo.setTicketStatus("WorkInProgress");
				eurepo.save(eubo);
//				return 1;
			} else {
				sebo.setCurrentHighPrioityTicketId("0");
				serepo.save(sebo);
				eubo.setTicketStatus("WorkInProgress");
				eurepo.save(eubo);
//				return 1;
			}
		}
		// CHANGING STATUS FROM WORK_IN_PROGRESS TO COMPLETED
		else if (e.getTicketStatus().equals("Completed") && eubo.getTicketStatus().equals("WorkInProgress")) {
			int l = sebo.getPending() - 1;
			int b = sebo.getTotalTickets() + 1;
			List<EndUserBean> list2 = eurepo.getwaitORpendtickets(sebo.getServiceEngineerId(), "Waiting");
			if (list2.size() > 0) {
				Optional<EndUserBean> ob1 = eurepo.findById(list2.get(0).getTicketId());
				EndUserBean eubo1 = ob1.get();
				eubo1.setTicketStatus("New");
				sebo.setCurrentHighPrioityTicketId(list2.get(0).getTicketId());
				sebo.setTotalTickets(b);
				sebo.setPending(l);
				eurepo.save(eubo1);
				serepo.save(sebo);
				eubo.setTicketStatus("Completed");
				eubo.setDateOfCompletion(sqlDate.toString());
				eurepo.save(eubo);
//				return 1;
			} else {
				sebo.setTotalTickets(b);
				sebo.setCurrentHighPrioityTicketId("0");
				eubo.setTicketStatus("Completed");
				eubo.setDateOfCompletion(sqlDate.toString());
				eurepo.save(eubo);
//				return 1;
			}
		}
		}
//		throw new MethodNotAllowed("Sorry!! but you are trying to deal with a dealt or an Incorrigible ticketid");
	}

	public void ChangePriority(EndUserBean eub) {
		Optional<EndUserBean> eul = eurepo.findById(eub.getTicketId());
		if(eul.isEmpty())
		{
//			return 13;
			throw new MethodNotAllowed("Sorry!! but you are trying to deal with a dealt or an Incorrigible ticketid");
		}
		else {
		EndUserBean eubo = eul.get();
		Optional<ServiceEngineerBean> sel = serepo.findById(eubo.getServiceengineer().getServiceEngineerId());
		ServiceEngineerBean sebo = sel.get();
		if (eubo.getTicketStatus().equals("WorkInProgress")) {
//			return 12;
			throw new MethodNotAllowed("Sorry you cannot change priority of a ticket you are currently working on!! rather try changing the status to achieve your requirement");
		} else if (eubo.getTicketStatus().equals("New")) {
			List<EndUserBean> list1 = eurepo.getwaitORpendtickets(sebo.getServiceEngineerId(), "Waiting");
			if (list1.size() > 0) {
				Optional<EndUserBean> ob1 = eurepo.findById(list1.get(0).getTicketId());
				EndUserBean eubo1 = ob1.get();
				eubo1.setTicketStatus("New");
				eubo.setTicketStatus("Waiting");
				eubo.setTicketPriority(eub.getTicketPriority());
				eurepo.save(eubo1);
				eurepo.save(eubo);
//				return 2;
			} else {
				eubo.setTicketPriority(eub.getTicketPriority());
				eurepo.save(eubo);
//				return 2;
			}
		} else {
			eubo.setTicketPriority(eub.getTicketPriority());
			eurepo.save(eubo);
//			return 2;
		}
		}
	}
}
