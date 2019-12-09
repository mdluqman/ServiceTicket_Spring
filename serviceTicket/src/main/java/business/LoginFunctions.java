package business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import beans.UserBean;
import beans.usertypeinfo;
import services.LoginServices;

@Component
public class LoginFunctions {
	@Autowired
	LoginServices loginservice;
	
	public usertypeinfo  getusertype(UserBean user1) 
	{
		return loginservice.validate(user1);
		
	}
}
