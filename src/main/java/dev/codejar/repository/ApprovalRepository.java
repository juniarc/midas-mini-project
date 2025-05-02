package dev.codejar.repository;

import dev.codejar.model.entity.Approval;
import dev.codejar.model.entity.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovalRepository extends JpaRepository<Approval, Integer> {

    List<Approval> findByRmStatus(String status);



}
