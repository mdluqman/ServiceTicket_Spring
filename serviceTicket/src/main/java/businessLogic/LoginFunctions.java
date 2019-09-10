package businessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import beans.UserBean;
import services.LoginDao;

@Component
public class LoginFunctions {

	@Autowired
	LoginDao ld;
	
	
	public String  getusertype(UserBean user1)
	{
		String x = ld.validate(user1);
		return x;
		
	}
}
