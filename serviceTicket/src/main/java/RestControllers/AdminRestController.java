package RestControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import BeanClasses.ServiceEngineerBean;
import BeanClasses.UserBean;
import BeanClasses.deptInfo;
import BeanClasses.usertypeinfo;
import BusinessClasses.AdminBusiness;
import BusinessClasses.LoginBusiness;

@RestController
@RequestMapping("/Admin")
public class AdminRestController {


		@Autowired
		AdminBusiness ab;
		
		@RequestMapping(value = "/register", method = RequestMethod.GET)
		public List<deptInfo> sample() {
			List<deptInfo> l=ab.getdept();
			return l;
		}
		
		@RequestMapping(value = "/registerclient", method = RequestMethod.POST)
		public String registernonSE(@RequestBody UserBean user) {
			return ab.registerinbean(user);
		}
		
		@RequestMapping(value = "/registerse", method = RequestMethod.POST)
		public String registerSE(@RequestBody ServiceEngineerBean se) {
			 return ab.registerinsebean(se);
		}
		
		@RequestMapping(value = "/view", method = RequestMethod.GET)
		public List<usertypeinfo> viewlist() {
			List<usertypeinfo> l=ab.getusertype();
			return l;
		}
		
		@RequestMapping(value = "/viewclient", method = RequestMethod.GET)
		public List<UserBean> viewclientlist() {
			List<UserBean> l=ab.getclient();
			return l;
		}
		
		@RequestMapping(value = "/viewse", method = RequestMethod.GET)
		public List<ServiceEngineerBean> viewselist() {
			List<ServiceEngineerBean> l=ab.getse();
			return l;
		}
		
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public String delete(@RequestBody UserBean user) {
			return ab.delete(user);
		}
		
		@RequestMapping(value = "/deleteSE", method = RequestMethod.POST)
		public String deletese(@RequestBody ServiceEngineerBean se) {
			return ab.deletese(se);
		}
}
