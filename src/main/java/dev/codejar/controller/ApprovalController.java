package dev.codejar.controller;


import dev.codejar.model.dto.ApprovalUpdateRequest;
import dev.codejar.model.entity.Approval;
import dev.codejar.repository.ApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/approvals")
public class ApprovalController {

    @Autowired
    private ApprovalRepository approvalRepository;

    @GetMapping("/pending")
    public List<Approval> getPendingApprovals() {
        return approvalRepository.findByRmStatus("Pending");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateApproval(@PathVariable Integer id, @RequestBody ApprovalUpdateRequest request) {
        Optional<Approval> optional = approvalRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Approval not found");
        }

        Approval approval = optional.get();
        approval.setRmStatus(request.getRmStatus());
        approval.setRmRemark(request.getRmRemark());

        approvalRepository.save(approval);
        return ResponseEntity.ok("Approval updated");
    }
}
