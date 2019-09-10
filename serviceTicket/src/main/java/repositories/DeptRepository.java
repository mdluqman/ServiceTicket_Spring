package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import beans.deptInfo;

public interface DeptRepository extends JpaRepository<deptInfo, Integer> {

}
