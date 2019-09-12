package controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import beans.ServiceEngineerBean;
import beans.UserBean;
import beans.deptInfo;
import beans.usertypeinfo;

@Controller
public class AdminController {
	@Autowired
	org.springframework.core.env.Environment environment;

	/*
	 * -to registration page- this gets the list of deparments with there respective
	 * deptartment-id's so that they can be displayed on the registraion page....
	 * and it directs to registration page(Helping admin to perform registration
	 * functionality
	 */
	@RequestMapping(value = "/register")
	public ModelAndView getdept() {
		String port = environment.getProperty("local.server.port");
		final String uri = "http://localhost:" + port + "/Admin/registration";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<deptInfo>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<deptInfo>>() {
				});
		ModelAndView mv = new ModelAndView("/Registration");
		mv.addObject("dept", response.getBody());
		return mv;
	}

	/*
	 * -from registration page- Takes you to the RestController then to business
	 * class and then to DAO class that has function which is used to register the
	 * respective CLIENT(USER) ie. function that saves data in the table is called
	 * through this
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(UserBean user, ServiceEngineerBean se, deptInfo di, usertypeinfo uti) {
		String port = environment.getProperty("local.server.port");
		user.setUsertype(uti);
		final String uri = "http://localhost:" + port + "/Admin/registerclient";
		RestTemplate restTemplate = new RestTemplate();
		String message = restTemplate.postForObject(uri, user, String.class);
		if (message.equals("Registered") && uti.getUserTypeId() == 2) {
			final String url = "http://localhost:" + port + "/Admin/registerse";
			RestTemplate restTemplate1 = new RestTemplate();
			se.setDept(di);
			se.setCurrentHighPrioityTicketId("0");
			se.setPending(0);
			se.setSEusername(user);
			se.setTotalTickets(0);
			String notrequired = restTemplate1.postForObject(url, se, String.class);
			ModelAndView mv = new ModelAndView("/Admin");
			mv.addObject("message", notrequired);
			return mv;
		} else if (message.equals("Registered")) {
			ModelAndView mv = new ModelAndView("/Admin");
			mv.addObject("message", message);
			return mv;
		} else {
			ModelAndView mv = new ModelAndView("/Admin");
			mv.addObject("message", message);
			return mv;
		}
	}

	/*
	 * -to AdminOutput page- this is used to get the list of type of users from the
	 * usertypeinfo table in the database this list is needed so that admin can
	 * select the type of client/user he wants to register.
	 */
	@RequestMapping(value = "/viewlist")
	public ModelAndView view() {
		String port = environment.getProperty("local.server.port");
		final String uri = "http://localhost:" + port + "/Admin/view";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<usertypeinfo>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<usertypeinfo>>() {
				});
		ModelAndView mv = new ModelAndView("/AdminOutput");
		mv.addObject("usertypelist", response.getBody());
		mv.addObject("usertypeid", "5");
		return mv;
	}

	/*
	 * -to AdminOutput page- this is used to direct to the functions that'll display
	 * the list of clients according to the type selected
	 */
	@RequestMapping(value = "/viewclient")
	public ModelAndView viewclient(usertypeinfo u) {
		String port = environment.getProperty("local.server.port");
		final String urw = "http://localhost:" + port + "/Admin/viewclient";
		final String urx = "http://localhost:" + port + "/Admin/viewse";
		final String uri = "http://localhost:" + port + "/Admin/view";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<usertypeinfo>> response1 = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<usertypeinfo>>() {
				});
		RestTemplate restTemplate1 = new RestTemplate();
		if (u.getUserTypeId() == 1 || u.getUserTypeId() == 3) {
			ResponseEntity<List<UserBean>> response = restTemplate1.exchange(urw, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<UserBean>>() {
					});
			ModelAndView mv = new ModelAndView("/AdminOutput");
			mv.addObject("usertypelist", response1.getBody());
			mv.addObject("users", response.getBody());
			if (u.getUserTypeId() == 1)
				mv.addObject("usertypeid", "1");
			else
				mv.addObject("usertypeid", "3");
			return mv;
		} else {
			if (u.getUserTypeId() == 2) {
				ResponseEntity<List<ServiceEngineerBean>> response = restTemplate.exchange(urx, HttpMethod.GET, null,
						new ParameterizedTypeReference<List<ServiceEngineerBean>>() {
						});
				List<ServiceEngineerBean> s = response.getBody();
				List<usertypeinfo> ux = response1.getBody();
				ModelAndView mv = new ModelAndView("/AdminOutput");
				mv.addObject("usertypelist", ux);
				mv.addObject("serviceengineer", s);
				mv.addObject("usertypeid", "2");
				return mv;
			} else {
				return null;
			}
		}
	}

	/*
	 * this is used to call the delete function by which admin can either delte a
	 * serviceEngineer or a EndUser or an Admin
	 */
	@RequestMapping(value = "/delete")
	public ModelAndView delete(UserBean user, @RequestParam("name") String username, @RequestParam("id2") int y,
			@RequestParam("id1") String seid) {
		String port = environment.getProperty("local.server.port");
		final String uri = "http://localhost:" + port + "/Admin/delete";
		user.setUsername(username);
		String message1 = null;
		if (y == 2) {
			ServiceEngineerBean se = new ServiceEngineerBean();
			se.setSEusername(user);
			se.setServiceEngineerId(seid);
			final String ury = "http://localhost:" + port + "/Admin/deleteSE";
			RestTemplate restTemplate1 = new RestTemplate();
			message1 = restTemplate1.postForObject(ury, se, String.class);
			if (message1.equals("SE Deleted")) {
				usertypeinfo u = new usertypeinfo();
				u.setUserTypeId(y);
				user.setUsertype(u);
				RestTemplate restTemplate = new RestTemplate();
				String message = restTemplate.postForObject(uri, user, String.class);
				ModelAndView mv = new ModelAndView("/Admin");
				mv.addObject("message", message);
				return mv;
			} else {
				ModelAndView mv = new ModelAndView("/Admin");
				mv.addObject("message", message1);
				return mv;
			}
		} else {
			usertypeinfo u = new usertypeinfo();
			u.setUserTypeId(y);
			user.setUsertype(u);
			RestTemplate restTemplate = new RestTemplate();
			String message = restTemplate.postForObject(uri, user, String.class);
			ModelAndView mv = new ModelAndView("/Admin");
			mv.addObject("message", message);
			return mv;
		}
	}
}
