package Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entities.Absentees;
import Entities.LoginCredentials;
import Entities.StudentAndProfessorDetails;
import Entities.Subjects;

public class AbsenteesService {

	
	public String insert(List<Absentees> absenteeList) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentDataManagementAndProcessing");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		for(int i=0;i<absenteeList.size();i++)
		{
			LoginCredentials student = entityManager.find(LoginCredentials.class, absenteeList.get(i).getUsername().getUsername());
			Subjects subject = entityManager.find( Subjects.class,absenteeList.get(i).getSubject_ID().getSubject_ID());
			absenteeList.get(i).setUsername(student);
			absenteeList.get(i).setSubject_ID(subject);
			entityManager.persist(absenteeList.get(i));
		}
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return "Attendance Updated";
	}

	public List<Absentees> search(String string) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentDataManagementAndProcessing");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Absentees> students = entityManager.createQuery("SELECT s from Absentees s where s.date = ?1").setParameter(1, string).getResultList();
		entityManager.close();
		entityManagerFactory.close();
		System.out.println("date-" + string + " list-" +students);
		return students;
	}

	public List<Absentees> searchstud(String string,String username) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentDataManagementAndProcessing");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Absentees> students = entityManager.createQuery("SELECT s from Absentees s where s.date = ?1 and s.username=(Select user from LoginCredentials user where user.username=?2)").setParameter(1, string).setParameter(2, username).getResultList();
		entityManager.close();
		entityManagerFactory.close();
		System.out.println("date-" + string + " list-" +students);
		return students;
	}

}
