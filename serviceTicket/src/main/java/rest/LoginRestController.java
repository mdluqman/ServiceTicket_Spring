package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import beans.UserBean;
import beans.usertypeinfo;
import business.LoginFunctions;
@CrossOrigin
@RestController
@RequestMapping("/validate")
public class LoginRestController {
	@Autowired
	LoginFunctions loginfunction;

	@RequestMapping(value = "/logincredentials", method = RequestMethod.POST)
	public usertypeinfo sample(@RequestBody UserBean user) {
		usertypeinfo u = new usertypeinfo();
		u.setUserTypeId(2);
		user.setUsertype(u);
		return loginfunction.getusertype(user);
	}
}
