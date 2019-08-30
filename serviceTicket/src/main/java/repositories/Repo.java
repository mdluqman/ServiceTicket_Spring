package repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import BeanClasses.UserBean;

@Repository
public interface Repo extends JpaRepository<UserBean,String>{

//	@PersistenceContext
	
}
