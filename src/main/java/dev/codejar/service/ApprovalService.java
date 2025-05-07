package dev.codejar.service;


import dev.codejar.model.dto.ApprovalUpdateRequest;
import dev.codejar.model.entity.Approval;
import dev.codejar.model.entity.Timesheet;
import dev.codejar.repository.ApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApprovalService {

    @Autowired
    private ApprovalRepository approvalRepository;

//    public Approval createFromTimesheet(Timesheet timesheet) {
//        Approval approval = new Approval();
//        approval.setUsername(timesheet.getUsername());
//        approval.setRm(timesheet.getRManager());
//        approval.setRmStatus("Pending");
//        approval.setRmRemark(null);
//        return approvalRepository.save(approval);
//    }

    public List<Approval> getPendingApprovals() {
        return approvalRepository.findByRmStatus("Pending");
    }

    public Approval updateApproval(Integer id, ApprovalUpdateRequest request) {
        Approval approval = approvalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval not found"));

        approval.setRmStatus(request.getRmStatus());
        approval.setRmRemark(request.getRmRemark());

        return approvalRepository.save(approval);
    }

}
