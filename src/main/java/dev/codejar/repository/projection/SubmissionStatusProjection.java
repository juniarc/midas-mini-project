package dev.codejar.repository.projection;

import java.util.Date;

public interface SubmissionStatusProjection {

    Integer getId();

    Date getSubmissionDate();

    String getApprovalStatus();

}
