package Business;

import Entities.LoginCredentials;
import Service.LoginService;

public class LoginOperations {

	
	public boolean checkUserExists(LoginCredentials credentials)
	{
		return new LoginService().checkUserExists(credentials);
		
	}

	public boolean validatePassword(LoginCredentials user) {
		// TODO Auto-generated method stub
		return new LoginService().validatePassword(user);
	}

	public String getUsertype(LoginCredentials user) {
		// TODO Auto-generated method stub
		return new LoginService().getUsertype(user);
	}
}
