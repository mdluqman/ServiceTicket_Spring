package rest;

import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import beans.ServiceEngineerBean;
import beans.UserBean;
import beans.deptInfo;
import beans.usertypeinfo;
import business.AdminFunctions;

//@CrossOrigin
@RestController
@RequestMapping("/Admin")
public class AdminRestController {
	@Autowired
	AdminFunctions adminfunction;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public List<deptInfo> sample() {
		List<deptInfo> l = adminfunction.getdept();
		return l;
	}

	@RequestMapping(value = "/registerclient", method = RequestMethod.POST)
	public String registernonSE(@RequestBody UserBean user) {
		return adminfunction.registerinbean(user);
	}

	@RequestMapping(value = "/checkse", method = RequestMethod.POST)
	public int checkse(@RequestBody ServiceEngineerBean se) {
		int x = adminfunction.checkse(se);
		System.out.println(x);
		return x;
	}
	
	@RequestMapping(value = "/registerse", method = RequestMethod.POST)
	public String registerSE(@RequestBody ServiceEngineerBean se) {
		return adminfunction.registerinsebean(se);
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public List<usertypeinfo> viewlist() {
		List<usertypeinfo> l = adminfunction.getusertype();
		return l;
	}

	@RequestMapping(value = "/viewclient", method = RequestMethod.GET)
	public List<UserBean> viewclientlist() {
		List<UserBean> l = adminfunction.getclient();
		return l;
	}

	@RequestMapping(value = "/viewse", method = RequestMethod.GET)
	public List<ServiceEngineerBean> viewselist() {
		List<ServiceEngineerBean> l = adminfunction.getse();
		return l;
	}

	@RequestMapping(value = "/delete/{usern}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "usern") String username) {
		UserBean user = new UserBean();
		user.setUsername(username);
		System.out.println(user.toString());
		adminfunction.delete(user);
	}

	@RequestMapping(value = "/deleteSE/{engineer}/{seid}", method = RequestMethod.DELETE)
	public void deletese(@PathVariable(value="engineer") String engineer ,@PathVariable(value="seid") String seid  ) {
		ServiceEngineerBean se = new ServiceEngineerBean();
		UserBean sEusername = new UserBean();
		sEusername.setUsername(engineer);
		se.setSEusername(sEusername);
		se.setServiceEngineerId(seid);
		adminfunction.deletese(se);
	}
	
	@RequestMapping(value = "/getdept", method = RequestMethod.GET)
	public List<deptInfo> dept() {
		List<deptInfo> l = adminfunction.getdept();
		return l;
	}
	
	@RequestMapping(value = "/createdept", method = RequestMethod.POST)
	public String createdept(@RequestBody deptInfo dept) {
		return adminfunction.createdept(dept);
		
	}
}


