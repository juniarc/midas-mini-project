package dev.codejar.repository;


import dev.codejar.model.entity.EmployeeEntity;
import dev.codejar.repository.projection.EmployeeManagerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    @Query(value = "SELECT EMPLOYEE_NAME AS employeeName, MANAGER_NAME AS managerName FROM  vw_employee_manager", nativeQuery = true)
    List<EmployeeManagerProjection> findEmployeeAndManager();

}
