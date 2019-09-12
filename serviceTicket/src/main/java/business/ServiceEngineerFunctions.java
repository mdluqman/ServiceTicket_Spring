package business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import beans.EndUserBean;
import beans.ServiceEngineerBean;
import services.ServiceEngineerServices;

@Component
public class ServiceEngineerFunctions {
	@Autowired
	ServiceEngineerServices sed = new ServiceEngineerServices();
	
	public List<EndUserBean> getsetickets(ServiceEngineerBean se) {
		return sed.getsetickets(se);
	}

	public List<String> ReportperS() {
		return sed.ReportperS();
	}

	public List<String> ReportperSE() {
		return sed.ReportperSE();
	}

	public List<String> avgage(ServiceEngineerBean se) {
		return sed.avgage(se);
	}

	public int ChangeStat(EndUserBean eub) {
		return sed.ChangeStat(eub);
	}

	public int ChangePriority(EndUserBean eub) {
		return sed.ChangePriority(eub);
	}
}
