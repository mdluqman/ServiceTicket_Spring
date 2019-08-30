package RestControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import BeanClasses.UserBean;
import BeanClasses.usertypeinfo;
import BusinessClasses.LoginBusiness;

@RestController
@RequestMapping("/valid")
public class LoginRestController {

	@Autowired
	LoginBusiness lb;
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String sample(@RequestBody UserBean user) {
		usertypeinfo u = new usertypeinfo();
		u.setUserTypeId(2);
		user.setUsertype(u);
		String x=lb.getusertype(user);
		return x;
	}
	
}
