package controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import beans.UserBean;

@Controller
public class LoginController {
	@Autowired
	Environment environment;

	/*
	 * this is used to authenticate the login credentials and allows only if the
	 * user is valid(ie. details present in the authentication table)
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(UserBean user, HttpSession session) {
		String port = environment.getProperty("local.server.port");
		final String uri = "http://localhost:" + port + "/validate/logincredentials";
		session.setAttribute("username", user.getUsername());
		UserBean u = user;
		RestTemplate restTemplate = new RestTemplate();
		ModelAndView mv = new ModelAndView("/index");
		try
		{
			String result = restTemplate.postForObject(uri, u, String.class);			
			if (result.equals("Admin")) {
				mv = new ModelAndView("/Admin");
				mv.addObject("username", user.getUsername());
				return mv;
			} else if (result.equals("ServiceEngineer")) {
				mv = new ModelAndView("/ServiceEngineer");
				mv.addObject("username", user.getUsername());
				return mv;
			} else if (result.equals("EndUser")) {
				mv = new ModelAndView("/EndUser");
				mv.addObject("username", user.getUsername());
			}
		}
		catch(Exception e)
		{
			mv.addObject("result", "Please Provide Valid Username/Password");		
		}
		return mv;
	}

	/*
	 * only one session is used that too to hold only the logged in user and hence
	 * is removed here when a user logs out
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("username");
		ModelAndView mv = new ModelAndView("/index");
		return mv;
	}
}
