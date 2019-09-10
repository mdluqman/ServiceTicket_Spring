package controllers;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

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
	 * this is used  to authenticate the login credentials and 
	 * allows only if the user is valid(ie. details present in the authentication table)
	 * */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView sample(UserBean user,HttpSession session) {
		String port = environment.getProperty("local.server.port");
		final String uri = "http://localhost:"+port+"/valid/insert";
		session.setAttribute("username", user.getUsername());
		UserBean u = user;
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.postForObject(uri, u, String.class);
		if(result.equals("Admin"))
		{
		ModelAndView mv = new ModelAndView("/Admin");
		mv.addObject("username",user.getUsername());
		return mv;
		}
		else if(result.equals("ServiceEngineer"))
				{
			ModelAndView mv = new ModelAndView("/ServiceEngineer");
			mv.addObject("username",user.getUsername());
			return mv;
				}
		else if(result.equals("EndUser"))
		{
			ModelAndView mv = new ModelAndView("/EndUser");
			mv.addObject("username",user.getUsername());
			return mv;
		}
		else
		{
			ModelAndView mv = new ModelAndView("/index");
			mv.addObject("result",result);
			return mv;
		}
	}
	
	/*
	 * only one session is used that too to hold only the logged in user
	 * and hence is removed here when a user logs out
	 * */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ModelAndView logout(HttpSession session) {
	session.removeAttribute("username");	
	ModelAndView mv = new ModelAndView("/index");
	return mv;
	}
}
