package BusinessClasses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Beans.ServiceEngineerBean;
import Beans.UserBean;
import Beans.deptInfo;
import Beans.usertypeinfo;
import DaoClasses.AdminDao;

@Component
public class AdminBusiness {

	@Autowired
	AdminDao ad = new AdminDao();
	
	public List<deptInfo> getdept()
	{
		return ad.getdept();
	}

	public String registerinbean(UserBean user) {
		return ad.registerinbean(user);
	}

	public String registerinsebean(ServiceEngineerBean se) {

		return ad.registerinsebean(se);
	}

	public List<usertypeinfo> getusertype() {
			return ad.getusertype();
			}

	public List<UserBean> getclient() {
		return ad.getclient();
	}

	public List<ServiceEngineerBean> getse() {
		return ad.getse();
	}

	public String delete(UserBean user) {
		return ad.delete(user);
	}

	public String deletese(ServiceEngineerBean se) {
		return ad.deletese(se);
	}
}
