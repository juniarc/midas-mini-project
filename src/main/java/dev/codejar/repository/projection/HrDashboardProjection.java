package dev.codejar.repository.projection;

import java.util.Date;

public interface HrDashboardProjection {

    String getEmployeeName();
    String getManagerName();
    Date getSubmissionDate();
    String getApprovalStatus();



}
