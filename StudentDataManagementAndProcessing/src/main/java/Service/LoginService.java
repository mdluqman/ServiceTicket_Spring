package Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entities.LoginCredentials;

public class LoginService {

	public boolean checkUserExists(LoginCredentials credentials) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentDataManagementAndProcessing");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		LoginCredentials user = entityManager.find(LoginCredentials.class, credentials.getUsername());
		if(user!=null)
			return true;
		return false;
	}

	public boolean validatePassword(LoginCredentials credentials) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentDataManagementAndProcessing");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		LoginCredentials user = entityManager.find(LoginCredentials.class, credentials.getUsername());
		
		if(user.getPassword().equals(credentials.getPassword()))
			return true;
		
		return false;
	}

	public String getUsertype(LoginCredentials credentials) {		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentDataManagementAndProcessing");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		LoginCredentials user = entityManager.find(LoginCredentials.class, credentials.getUsername());
		
		return user.getUsertype();
	}

}
