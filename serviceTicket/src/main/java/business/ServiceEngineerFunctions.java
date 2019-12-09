package business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import beans.EndUserBean;
import beans.ServiceEngineerBean;
import beans.UserBean;
import services.ServiceEngineerServices;

@Component
public class ServiceEngineerFunctions {
	@Autowired
	ServiceEngineerServices serviceengineerservice = new ServiceEngineerServices();
	
	public List<EndUserBean> getsetickets(UserBean se) {
		return serviceengineerservice.getsetickets(se);
	}

	public List<String> ReportperS() {
		return serviceengineerservice.ReportperS();
	}

	public List<String> ReportperSE() {
		return serviceengineerservice.ReportperSE();
	}

	public List<String> avgage(UserBean se) {
		return serviceengineerservice.avgage(se);
	}

	public void ChangeStat(EndUserBean eub) {
		 serviceengineerservice.ChangeStat(eub);
	}

	public void ChangePriority(EndUserBean eub) {
		serviceengineerservice.ChangePriority(eub);
	}
}
