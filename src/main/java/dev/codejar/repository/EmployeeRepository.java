package dev.codejar.repository;


import dev.codejar.model.entity.EmployeeEntity;
import dev.codejar.repository.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    @Query(value = "SELECT a.employee_name AS employeeName, b.employee_name AS managerName " +
            "FROM employees a " +
            "JOIN employees b ON a.manager_id = b.emp_id", nativeQuery = true)
    List<EmployeeProjection> findEmployeeAndManager();

}
