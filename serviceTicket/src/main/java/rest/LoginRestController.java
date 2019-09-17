package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import beans.UserBean;
import beans.usertypeinfo;
import business.LoginFunctions;

@RestController
@RequestMapping("/validate")
public class LoginRestController {
	@Autowired
	LoginFunctions lb;

	@RequestMapping(value = "/logincredentials", method = RequestMethod.POST)
	public String sample(@RequestBody UserBean user) throws Exception {
		usertypeinfo u = new usertypeinfo();
		u.setUserTypeId(2);
		user.setUsertype(u);
		String x = lb.getusertype(user);
		return x;
	}
}
