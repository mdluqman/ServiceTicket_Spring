package rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import beans.UserBean;
import beans.usertypeinfo;
import businessLogic.LoginFunctions;

@RestController
@RequestMapping("/valid")
public class LoginRestController {

	@Autowired
	LoginFunctions lb;
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String sample(@RequestBody UserBean user) {
		usertypeinfo u = new usertypeinfo();
		u.setUserTypeId(2);
		user.setUsertype(u);
		String x=lb.getusertype(user);
		return x;
	}
	
}
