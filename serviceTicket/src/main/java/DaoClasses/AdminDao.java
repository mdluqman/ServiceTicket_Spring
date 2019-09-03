package DaoClasses;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BeanClasses.EndUserBean;
import BeanClasses.ServiceEngineerBean;
import BeanClasses.UserBean;
import BeanClasses.deptInfo;
import BeanClasses.usertypeinfo;
import repositories.DeptRepository;
import repositories.EndUserRepository;
import repositories.Repo;
import repositories.ServiceEngineerRepo;
import repositories.usertypeinfoRepository;

@Service
public class AdminDao {

	@Autowired
	DeptRepository repo;

	@Autowired
	EndUserRepository eurepo;

	@Autowired
	Repo userRepo;

	@Autowired
	ServiceEngineerRepo seRepo;

	@Autowired
	usertypeinfoRepository utiRepo;

	public List<deptInfo> getdept() {
		List<deptInfo> l = repo.findAll();
		return l;

	}

	public String registerinbean(UserBean user) {
		if (!userRepo.findById(user.getUsername()).isEmpty()) {
			System.out.println(userRepo.findById(user.getUsername()));
			return "user already registered(ie username already exists,try to Register with different username!)";
		} else {
			userRepo.save(user);
			return "Registered";
		}
	}

	public String registerinsebean(ServiceEngineerBean se) {

		System.out.println("SID: " + se.getServiceEngineerId());
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

	public String delete(UserBean user) {
		List<EndUserBean> eubl = eurepo.findbyname(user.getUsername());
		ServiceEngineerBean s = new ServiceEngineerBean();
		for (int i = 0; i < eubl.size(); i++) {
			s.setServiceEngineerId(eubl.get(i).getServiceengineer().getServiceEngineerId());
			Optional<ServiceEngineerBean> ob2 = seRepo.findById(s.getServiceEngineerId());
			ServiceEngineerBean sebo = ob2.get();
			if (eubl.get(i).getTicketStatus().equals("New") || eubl.get(i).getTicketStatus().equals("WorkInProgress")) {

				List<EndUserBean> list = eurepo.getsewaitingtickets(sebo.getServiceEngineerId(), "Waiting", user);
				if (list.size() > 0) {
					System.out.println(list.get(0).getTicketId() + " " + list.get(0).getusername());
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
				System.out.println("hi delete");
				UserBean users = new UserBean();
				users.setUsername("USER-DELETED");
				Optional<EndUserBean> obj = eurepo.findById(eubl.get(i).getTicketId());
				EndUserBean e = obj.get();
				e.setusername(users);
				System.out.println(e.getTicketId() + " " + e.getusername().getUsername());
				eurepo.save(e);
			}
		}
		userRepo.deleteById(user.getUsername());
		System.out.println("requested Delete Performed");
		return "requested Delete Performed";
	}

	public String deletese(ServiceEngineerBean se) {
		Optional<ServiceEngineerBean> s = seRepo.findById(se.getServiceEngineerId());
		ServiceEngineerBean sebo = s.get();
		List<ServiceEngineerBean> sel = seRepo.findSEbydept(sebo.getDept());
		EndUserBean eubo = new EndUserBean();
		eubo.setServiceengineer(sebo);
		System.out.println("b4 if loop");
		if (sebo.getDept().getDeptNo() != 4) {
			if (sel.size() > 1) {
				String x = del(sebo);
				System.out.println(x);
				return x;
				}
			else
				return "you cannot delete the only serviceEngineer in " + sebo.getDept().getDeptName() + "Department";
		} else if (sel.size() > 2) {
			String x = del(sebo);
			return x;
		} else
		{
			return "you cannot delete the only serviceEngineer in " + sebo.getDept().getDeptName() + "Department";
		 
	}	
	}
	
	public String del(ServiceEngineerBean sebo)
	{
		List<ServiceEngineerBean> sel = seRepo.findSEbydept(sebo.getDept());
		EndUserBean eubo = new EndUserBean();
		eubo.setServiceengineer(sebo);
		System.out.println(eubo.getServiceengineer().getServiceEngineerId());
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
	}
