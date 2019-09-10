package DaoClasses;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Beans.UserBean;
import repositories.Repo;

@Service
public class LoginDao{

	@Autowired
	Repo	 repo;
	
	
	public String validate(UserBean user2) {
		Optional<UserBean> l =repo.findById(user2.getUsername());
		if(l.isEmpty())
		{
			return "user not found";
		}
		else if(l.get().getPasswords().equals(user2.getPasswords()))
		{
			return l.get().getUsertype().getTypeOfUser();
		}
		else
		{
			return "Incorrect password";
		}
	}

}
