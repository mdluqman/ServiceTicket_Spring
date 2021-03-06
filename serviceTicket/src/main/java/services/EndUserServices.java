package services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import beans.EndUserBean;
import beans.ServiceEngineerBean;
import beans.deptInfo;
import repositories.DeptRepository;
import repositories.EndUserRepository;
import repositories.ServiceEngineerRepository;

@Service
public class EndUserServices {
	@Autowired
	EndUserRepository eurepo;

	@Autowired
	ServiceEngineerRepository serepo;

	@Autowired
	DeptRepository drepo;

	public List<deptInfo> getdept() {
		return drepo.findAll();
	}

	public int raiseticket(EndUserBean ticket) {
		List<ServiceEngineerBean> result = serepo.findSEbydept(ticket.getDept());
		ServiceEngineerBean sb = new ServiceEngineerBean();
		for (int i = 0; i < result.size(); i++) {
			if (result.get(i).getCurrentHighPrioityTicketId().equals("0")) {
				sb.setServiceEngineerId(result.get(i).getServiceEngineerId());
				ticket.setServiceengineer(sb);
				Optional<ServiceEngineerBean> sb1 = serepo.findById(ticket.getServiceengineer().getServiceEngineerId());
				sb1.get().setCurrentHighPrioityTicketId(ticket.getTicketId());
				eurepo.save(ticket);
				serepo.save(sb1.get());
				return 1;
			}
		}
		List<EndUserBean> selist = eurepo.assigntickets(ticket.getDept());
		for (int i = 0; i < selist.size(); i++) {
			Optional<ServiceEngineerBean> sb1 = serepo
					.findById(selist.get(i).getServiceengineer().getServiceEngineerId());
			sb = sb1.get();
			int j = sb1.get().getPending() + 1;
			int y = 1;
			int x = Integer.parseInt(selist.get(i).getTicketPriority());
			if (selist.get(i).getTicketStatus().equals("Completed")) {
				y = 0;
			}
			if (x >= Integer.parseInt(ticket.getTicketPriority()) && j < 7 && y == 1) {
				ticket.setServiceengineer(sb);
				ticket.setTicketStatus("Waiting");
				sb.setPending(j);
				eurepo.save(ticket);
				serepo.save(sb);
				return 3;
			} else if (x < Integer.parseInt(ticket.getTicketPriority()) && j < 7 && y == 1) {
				String h = sb.getCurrentHighPrioityTicketId();
				ticket.setServiceengineer(sb);
				ticket.setTicketStatus("New");
				eurepo.save(ticket);
				sb.setCurrentHighPrioityTicketId(ticket.getTicketId());
				sb.setPending(j);
				serepo.save(sb);
				Optional<EndUserBean> e3 = eurepo.findById(h);
				EndUserBean e1 = e3.get();
				e1.setTicketStatus("Waiting");
				eurepo.save(e1);
				return 3;
			}
		}
		return 0;
	}

	public List<EndUserBean> gettickets(EndUserBean eub) {
		List<EndUserBean> l = eurepo.gettickets(eub.getusername());
		return l;
	}

	public int verifytid(String tid) {
		// TODO Auto-generated method stub
		Optional<EndUserBean> l = eurepo.findById(tid);
		if (l.isEmpty())
			return 1;
		else
			return 0;
	}
}
