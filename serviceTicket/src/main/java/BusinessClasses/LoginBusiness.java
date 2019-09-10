package BusinessClasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Beans.UserBean;
import DaoClasses.LoginDao;

@Component
public class LoginBusiness {

	@Autowired
	LoginDao ld;
	
	
	public String  getusertype(UserBean user1)
	{
		String x = ld.validate(user1);
		return x;
		
	}
}
