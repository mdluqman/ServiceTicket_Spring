package Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entities.Absentees;
import Entities.Marks;
import Entities.Subjects;

public class StudentService {

	@SuppressWarnings("unchecked")
	public List<Marks> getMarks(String username) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentDataManagementAndProcessing");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Marks> marks = entityManager.createQuery("SELECT m from Marks m where m.username = (SELECT l from LoginCredentials l where l.username = ?1) order by m.date desc").setParameter(1, username).getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return marks;
	}

	public List<Absentees> getAttendance(String username) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentDataManagementAndProcessing");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Absentees> absentdates = entityManager.createQuery("SELECT m from Absentees m where m.username = (SELECT l from LoginCredentials l where l.username = ?1) order by m.date desc").setParameter(1, username).getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return absentdates;
	}
}
