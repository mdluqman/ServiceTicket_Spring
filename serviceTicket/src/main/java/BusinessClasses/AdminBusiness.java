package BusinessClasses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import BeanClasses.ServiceEngineerBean;
import BeanClasses.UserBean;
import BeanClasses.deptInfo;
import BeanClasses.usertypeinfo;
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
		// TODO Auto-generated method stub
		return ad.registerinbean(user);
	}

	public String registerinsebean(ServiceEngineerBean se) {

		return ad.registerinsebean(se);
	}

	public List<usertypeinfo> getusertype() {
			return ad.getusertype();
			}

	public List<UserBean> getclient() {
		// TODO Auto-generated method stub
		return ad.getclient();
	}

	public List<ServiceEngineerBean> getse() {
		// TODO Auto-generated method stub
		return ad.getse();
	}

	public String delete(UserBean user) {
		// TODO Auto-generated method stub
		return ad.delete(user);
	}

	public String deletese(ServiceEngineerBean se) {
		// TODO Auto-generated method stub
		return ad.deletese(se);
	}
}
