package services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import beans.UserBean;
import beans.usertypeinfo;
import exceptions.UnAuthorizedException;
import repositories.LoginRepository;

@Service
public class LoginServices {
	@Autowired
	LoginRepository repo;

	public usertypeinfo validate(UserBean user2) {
		Optional<UserBean> l = repo.findById(user2.getUsername());
		if (l.isEmpty()) {
			throw new UnAuthorizedException("user not found");
		} else if (l.get().getPasswords().equals(user2.getPasswords())) {
			return l.get().getUsertype();
		} else {
			throw new UnAuthorizedException("Incorrect password");
		}
	}
}
