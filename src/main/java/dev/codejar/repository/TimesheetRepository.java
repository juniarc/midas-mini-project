package dev.codejar.repository;


import dev.codejar.model.entity.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {




}
