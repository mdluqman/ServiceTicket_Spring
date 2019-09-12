package controllers;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import beans.EndUserBean;
import beans.UserBean;
import beans.deptInfo;

@Controller
public class EndUserController {

	java.util.Date date = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(date.getTime());

	@Autowired
	Environment environment;

	/*
	 * EndUser page to RaiseTicket
	 * this is used to get the dept datials from deptInfo table
	 */
	@RequestMapping(value = "/raiseticket")
	public ModelAndView getdept() {
		String port = environment.getProperty("local.server.port");
		RestTemplate restTemplate = new RestTemplate();
		final String uri = "http://localhost:" + port + "/EndUser/getdept";
		ResponseEntity<List<deptInfo>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<deptInfo>>() {
				});
		ModelAndView mv = new ModelAndView("/RaiseTicket");
		mv.addObject("dept", response.getBody());
		return mv;
	}

	/*
	 * RaiseTicket to EndUserOutput 
	 * this function verifies if the details such as Requested-End-Date are given
	 * proper or not And if given correctly then the ticket for the user will be
	 * assigned to the appropriate ServiceEngineer
	 * 
	 */
	@RequestMapping(value = "/ticketraised")
	public ModelAndView raiseticket(EndUserBean eub, deptInfo di, HttpSession session) {
		String port = environment.getProperty("local.server.port");
		final String uri = "http://localhost:" + port + "/EndUser/verify";
		RestTemplate restTemplate = new RestTemplate();
		eub.setDateOfAction("--");
		eub.setDateOfCompletion("--");
		eub.setDateOfIssue(sqlDate.toString());
		eub.setDept(di);
		UserBean user = new UserBean();
		String username = (String) session.getAttribute("username");
		user.setUsername(username);
		eub.setusername(user);
		int x = restTemplate.postForObject(uri, eub, Integer.class);
		if (x == 8) {
			final String urz = "http://localhost:" + port + "/EndUser/getdept";
			ResponseEntity<List<deptInfo>> response = restTemplate.exchange(urz, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<deptInfo>>() {
					});

			ModelAndView mv = new ModelAndView("/RaiseTicket");
			mv.addObject("dept", response.getBody());
			mv.addObject("ch", 8);
			return mv;
		} else {
			final String urz = "http://localhost:" + port + "/EndUser/gettickets";
			HttpEntity<EndUserBean> requestEntity = new HttpEntity<>(eub);
			ResponseEntity<List<EndUserBean>> response = restTemplate.exchange(urz, HttpMethod.POST, requestEntity,
					new ParameterizedTypeReference<List<EndUserBean>>() {
					});

			ModelAndView mv = new ModelAndView("/EndUserOutput");
			mv.addObject("tickets", response.getBody());
			mv.addObject("ch", x);
			return mv;
		}

	}

	/*
	 * this function is used to display the logged in EndUsers ticket from the
	 * EndUserBean table(that has records of all the tickets ever raised)
	 */
	@RequestMapping(value = "/viewtickets")
	public ModelAndView VIEWticket(HttpSession session) {
		String port = environment.getProperty("local.server.port");
		RestTemplate restTemplate = new RestTemplate();
		UserBean user = new UserBean();
		String username = (String) session.getAttribute("username");
		user.setUsername(username);
		EndUserBean eub = new EndUserBean();
		eub.setusername(user);
		final String urz = "http://localhost:" + port + "/EndUser/gettickets";
		HttpEntity<EndUserBean> requestEntity = new HttpEntity<>(eub);
		ResponseEntity<List<EndUserBean>> response = restTemplate.exchange(urz, HttpMethod.POST, requestEntity,
				new ParameterizedTypeReference<List<EndUserBean>>() {
				});
		ModelAndView mv = new ModelAndView("/EndUserOutput");
		mv.addObject("tickets", response.getBody());
		mv.addObject("ch", 2);
		return mv;
	}
}
