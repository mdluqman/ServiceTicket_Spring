package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import BeanClasses.deptInfo;

public interface DeptRepository extends JpaRepository<deptInfo, Integer> {

}
