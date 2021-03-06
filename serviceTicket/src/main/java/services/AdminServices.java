package services;

import java.awt.print.Printable;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import beans.EndUserBean;
import beans.ServiceEngineerBean;
import beans.UserBean;
import beans.deptInfo;
import beans.usertypeinfo;
import exceptions.MethodNotAllowed;
import repositories.DeptRepository;
import repositories.EndUserRepository;
import repositories.LoginRepository;
import repositories.ServiceEngineerRepository;
import repositories.usertypeinfoRepository;

@Service
public class AdminServices {
	@Autowired
	DeptRepository departmentrepo;

	@Autowired
	EndUserRepository eurepo;

	@Autowired
	LoginRepository userRepo;

	@Autowired
	ServiceEngineerRepository seRepo;

	@Autowired
	usertypeinfoRepository utiRepo;

	public List<deptInfo> getdept() {
		List<deptInfo> l = departmentrepo.findAll();
		return l;
	}

	public String registerinbean(UserBean user) {
		if (!userRepo.findById(user.getUsername()).isEmpty()) {
			return "user already registered(ie username already exists,try to Register with different username!)";
		} else {
			userRepo.save(user);
			return "Registered";
		}
	}

	public String registerinsebean(ServiceEngineerBean se) {
		seRepo.save(se);
		return "DONE";
	}

	public List<usertypeinfo> getusertype() {
		return utiRepo.findAll();
	}

	public List<UserBean> getclient() {
		return userRepo.findAll();
	}

	public List<ServiceEngineerBean> getse() {
		return seRepo.findAll();
	}

	public void delete(UserBean user) {
		List<EndUserBean> eubl = eurepo.findbyname(user.getUsername());
		ServiceEngineerBean s = new ServiceEngineerBean();
		for (int i = 0; i < eubl.size(); i++) {
			s.setServiceEngineerId(eubl.get(i).getServiceengineer().getServiceEngineerId());
			Optional<ServiceEngineerBean> ob2 = seRepo.findById(s.getServiceEngineerId());
			ServiceEngineerBean sebo = ob2.get();
			if (eubl.get(i).getTicketStatus().equals("New") || eubl.get(i).getTicketStatus().equals("WorkInProgress")) {
				List<EndUserBean> list = eurepo.getsewaitingtickets(sebo.getServiceEngineerId(), "Waiting", user);
				if (list.size() > 0) {
					sebo.setCurrentHighPrioityTicketId(list.get(0).getTicketId());
					Optional<EndUserBean> obj = eurepo.findById(list.get(0).getTicketId());
					EndUserBean e = obj.get();
					e.setTicketStatus("New");
					int b = sebo.getPending() - 1;
					sebo.setPending(b);
					seRepo.save(sebo);
					eurepo.save(e);
					eurepo.deleteById(eubl.get(i).getTicketId());
				} else {
					sebo.setCurrentHighPrioityTicketId("0");
					seRepo.save(sebo);
					eurepo.deleteById(eubl.get(i).getTicketId());
				}
			} else if (eubl.get(i).getTicketStatus().equals("Waiting")) {
				int b = sebo.getPending() - 1;
				sebo.setPending(b);
				seRepo.save(sebo);
				eurepo.deleteById(eubl.get(i).getTicketId());
			} else if (eubl.get(i).getTicketStatus().equals("Completed")) {
				UserBean users = new UserBean();
				users.setUsername("USER-DELETED");
				Optional<EndUserBean> obj = eurepo.findById(eubl.get(i).getTicketId());
				EndUserBean e = obj.get();
				e.setusername(users);
				eurepo.save(e);
			}
		}
		userRepo.deleteById(user.getUsername());
//		return "requested Delete Performed";
	}

	public void deletese(ServiceEngineerBean se) {
		Optional<ServiceEngineerBean> s = seRepo.findById(se.getServiceEngineerId());
		ServiceEngineerBean sebo = s.get();
		List<ServiceEngineerBean> sel = seRepo.findSEbydept(sebo.getDept());
		EndUserBean eubo = new EndUserBean();
		eubo.setServiceengineer(sebo);
		if (sebo.getDept().getDeptNo() != 4) {
			if (sel.size() > 1) {
				String x = del(sebo);
			} else
//				return "you cannot delete the only serviceEngineer in " + sebo.getDept().getDeptName() + " Department";
				throw new MethodNotAllowed("you cannot delete the only serviceEngineer in " + sebo.getDept().getDeptName() + "Department");
		} else if (sel.size() > 2) {
			String x = del(sebo);
		} else {
//			return "you cannot delete the only serviceEngineer in " + sebo.getDept().getDeptName() + " Department";
			throw new MethodNotAllowed("you cannot delete the only serviceEngineer in " + sebo.getDept().getDeptName() + "Department");

		}
	}

	public String del(ServiceEngineerBean sebo) {
		List<ServiceEngineerBean> sel = seRepo.findSEbydept(sebo.getDept());
		EndUserBean eubo = new EndUserBean();
		eubo.setServiceengineer(sebo);
		if (sebo.getCurrentHighPrioityTicketId().equals("0")) {
			List<EndUserBean> eul = eurepo.getsetickets(eubo.getServiceengineer());
			for (int i = 0; i < eul.size(); i++) {
				Optional<EndUserBean> e = eurepo.findById(eul.get(i).getTicketId());
				eubo = e.get();
				ServiceEngineerBean b = new ServiceEngineerBean();
				b.setServiceEngineerId("serviceEngineer-Deleted");
				eubo.setServiceengineer(b);
				eurepo.save(eubo);
			}
			seRepo.deleteById(sebo.getServiceEngineerId());
			return "SE Deleted";
		} else {

			return "Cannot Delete a ServiceEngineer as he is on DUTY";
		}
	}

	public int checkse(ServiceEngineerBean se) {
		// TODO Auto-generated method stub
		Optional<ServiceEngineerBean> check = seRepo.findById(se.getServiceEngineerId());
		if(check.isEmpty())
		{
			return 0;
		}
		else 
			return 1;
	}

	public String createdept(deptInfo dept) {
		// TODO Auto-generated method stub
		List<deptInfo>  x = departmentrepo.getdepts(dept.getDeptName());
		if(x.isEmpty())
		{
			departmentrepo.save(dept);
			return "departmentCreated";
		}
		else 
			throw new MethodNotAllowed("department with same name already exists");
			
		
	}
}
