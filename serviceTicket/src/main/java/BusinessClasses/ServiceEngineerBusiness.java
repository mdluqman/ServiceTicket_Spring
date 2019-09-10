package BusinessClasses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Beans.EndUserBean;
import Beans.ServiceEngineerBean;
import DaoClasses.EndUserDao;
import DaoClasses.ServiceEngineerDao;

@Component
public class ServiceEngineerBusiness {

	@Autowired
	ServiceEngineerDao sed = new ServiceEngineerDao();

	public List<EndUserBean> getsetickets(ServiceEngineerBean se) {
		System.out.println("in b");
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
