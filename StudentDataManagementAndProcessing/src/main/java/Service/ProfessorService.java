package Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Entities.Marks;
import Entities.StudentAndProfessorDetails;
import embedded.MarksEmbeddedKey;

public class ProfessorService {

	@SuppressWarnings("unchecked")
	public List<StudentAndProfessorDetails> getProfessorClasses(String username) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentDataManagementAndProcessing");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<StudentAndProfessorDetails> classList = new ArrayList<StudentAndProfessorDetails>();
		classList = entityManager.createQuery("SELECT s from StudentAndProfessorDetails s where s.username=(SELECT l from LoginCredentials l where l.username=?1)").setParameter(1, username).getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return classList;
	}

	@SuppressWarnings("unchecked")
	public List<String> getProfessorStudents(StudentAndProfessorDetails classDetails, String username) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentDataManagementAndProcessing");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<String> students = new ArrayList<String>();
		students = entityManager.createQuery("SELECT students.username.username from StudentAndProfessorDetails students where students.section=?1 and students.subject_ID=(SELECT subject from Subjects subject where subject.subject_ID=?2) and students.branch_ID=(SELECT branch from Branch branch where branch.branch_ID=?3) and students.semester=?4 and students.username.username!=?5").setParameter(1, classDetails.getSection()).setParameter(2, classDetails.getSubject_ID().getSubject_ID()).setParameter(3, classDetails.getBranch_ID().getBranch_ID()).setParameter(4, classDetails.getSemester()).setParameter(5,username).getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return students;
	}

	@SuppressWarnings("unchecked")
	public List<Marks> getMarks(StudentAndProfessorDetails details, String username) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentDataManagementAndProcessing");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Marks> marks = entityManager.createQuery("SELECT m from Marks m where m.subject_ID=(SELECT subject from Subjects subject where subject.subject_ID=?1) and m.semester=?2 and m.section=?3 and m.branch=(SELECT branch from Branch branch where branch.branch_ID=?4)").setParameter(1, details.getSubject_ID().getSubject_ID()).setParameter(2, details.getSemester()).setParameter(3, details.getSection()).setParameter(4, details.getBranch_ID().getBranch_ID()).getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return marks;
	}

	public static String updateMarks(Marks studentmarks) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentDataManagementAndProcessing");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		MarksEmbeddedKey mek = new MarksEmbeddedKey(studentmarks.getUsername().getUsername(),studentmarks.getSubject_ID().getSubject_ID());
		Marks marks = entityManager.find(Marks.class,mek);
		marks.setMid1(studentmarks.getMid1());
		marks.setMid2(studentmarks.getMid2());
		marks.setLabInternals(studentmarks.getLabInternals());
		marks.setLabExternals(studentmarks.getLabExternals());
		marks.setSemGrades(studentmarks.getSemGrades());
		System.out.println(marks);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return "Marks Updated";
	}

	public static List<StudentAndProfessorDetails> getProfessordetails(StudentAndProfessorDetails profdetails) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentDataManagementAndProcessing");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<StudentAndProfessorDetails> profdetailsList = entityManager.createQuery("SELECT s from StudentAndProfessorDetails s where s.username=(SELECT user from LoginCredentials user where user.username=?1)").setParameter(1,profdetails.getUsername().getUsername()).getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return profdetailsList;
	}

	public static List<Marks> getMarksforsubjects(Marks profstudMarks) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("StudentDataManagementAndProcessing");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<Marks> marksList = entityManager.createQuery("SELECT m from Marks m where m.subject_ID=(SELECT subject from Subjects subject where subject.subject_ID=?1) and m.semester=?2 and m.section=?3 and m.branch=(SELECT branch from Branch branch where branch.branch_ID=?4)").setParameter(1, profstudMarks.getSubject_ID().getSubject_ID()).setParameter(2, profstudMarks.getSemester()).setParameter(3, profstudMarks.getSection()).setParameter(4, profstudMarks.getBranch().getBranch_ID()).getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return marksList;
	}

}
