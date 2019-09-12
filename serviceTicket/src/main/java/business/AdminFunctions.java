package business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import beans.ServiceEngineerBean;
import beans.UserBean;
import beans.deptInfo;
import beans.usertypeinfo;
import services.AdminServices;

@Component
public class AdminFunctions {
	@Autowired
	AdminServices ad = new AdminServices();

	public List<deptInfo> getdept() {
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
