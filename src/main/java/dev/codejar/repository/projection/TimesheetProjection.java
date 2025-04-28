package dev.codejar.repository.projection;

import java.util.Date;

public interface TimesheetProjection {

    Integer getId();

    Date getSubmissionDate();

    String getApprovalStatus();

}
