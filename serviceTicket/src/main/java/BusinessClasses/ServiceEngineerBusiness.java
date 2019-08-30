package BusinessClasses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import BeanClasses.EndUserBean;
import BeanClasses.ServiceEngineerBean;
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
		// TODO Auto-generated method stub
		return sed.ReportperS();
	}

	public List<String> ReportperSE() {
		// TODO Auto-generated method stub
		return sed.ReportperSE();
	}


	public List<String> avgage(ServiceEngineerBean se) {
		// TODO Auto-generated method stub
		return sed.avgage(se);
	}

	public int ChangeStat(EndUserBean eub) {
		// TODO Auto-generated method stub
		return sed.ChangeStat(eub);
	}

	public int ChangePriority(EndUserBean eub) {
		// TODO Auto-generated method stub
		return sed.ChangePriority(eub);
	}
}
