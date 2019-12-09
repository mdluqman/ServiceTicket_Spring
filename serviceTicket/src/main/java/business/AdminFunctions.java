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
	AdminServices adminservice = new AdminServices();

	public List<deptInfo> getdept() {
		return adminservice.getdept();
	}

	public String registerinbean(UserBean user) {
		return adminservice.registerinbean(user);
	}

	public String registerinsebean(ServiceEngineerBean se) {

		return adminservice.registerinsebean(se);
	}

	public List<usertypeinfo> getusertype() {
		return adminservice.getusertype();
	}

	public List<UserBean> getclient() {
		return adminservice.getclient();
	}

	public List<ServiceEngineerBean> getse() {
		return adminservice.getse();
	}

	public void delete(UserBean user) {
		adminservice.delete(user);
	}

	public void deletese(ServiceEngineerBean se) {
		adminservice.deletese(se);
	}

	public int checkse(ServiceEngineerBean se) {
		// TODO Auto-generated method stub
		return adminservice.checkse(se);
	}

	public String createdept(deptInfo dept) {
		// TODO Auto-generated method stub
		return adminservice.createdept(dept);
	}
}
