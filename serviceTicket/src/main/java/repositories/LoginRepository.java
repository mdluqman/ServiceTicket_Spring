package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import beans.UserBean;

@Repository
public interface LoginRepository extends JpaRepository<UserBean,String>{
}
