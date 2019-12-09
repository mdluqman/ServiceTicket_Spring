package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import beans.deptInfo;

public interface DeptRepository extends JpaRepository<deptInfo, Integer> {
	
	@Query("select e from deptInfo e where e.deptName= :uname ")
	List<deptInfo> getdepts(@Param("uname") String dname);
	
}
