package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Beans.deptInfo;

public interface DeptRepository extends JpaRepository<deptInfo, Integer> {

}
