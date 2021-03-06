package repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import beans.ServiceEngineerBean;
import beans.UserBean;
import beans.deptInfo;

public interface ServiceEngineerRepository extends JpaRepository<ServiceEngineerBean, String>{

	@Query("select s from ServiceEngineerBean s where s.dept= :deptid")
	List<ServiceEngineerBean> findSEbydept(@Param("deptid") deptInfo deptInfo);
	
	@Query("select s from ServiceEngineerBean s where s.SEusername= :seusername")
	List<ServiceEngineerBean> findSEbyname(@Param("seusername") UserBean userBean);
}
