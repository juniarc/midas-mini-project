package dev.codejar.repository;


import dev.codejar.model.entity.EmployeeEntity;
import dev.codejar.repository.projection.HrDashboardProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {


}
