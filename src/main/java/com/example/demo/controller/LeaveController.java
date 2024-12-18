package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.modele.Etat;
import com.example.demo.modele.LeaveRequest;
import com.example.demo.repository.LeaveRequestRepo;
import com.example.demo.service.LeaveServicee;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/leaves")
@CrossOrigin(origins = "http://localhost:4200")
public class LeaveController {
    @Autowired
    private LeaveServicee leaveService;
    @Autowired
    private LeaveRequestRepo leaveRequestRepository;
    @GetMapping("/{userId}/approved-days")
    public int getApprovedDays(@PathVariable Long userId) {
        return leaveRequestRepository.findAllByUserIdAndEtt(userId, Etat.PENDING)
                .stream()
                .mapToInt(LeaveRequest::getDaysRequested)
                .sum();
    }
@GetMapping
public ResponseEntity<List<LeaveRequestDto>> getAll(){
	return ResponseEntity.ok(leaveService.getAllRequest());
}
    @PostMapping("/{userId}")
    public ResponseEntity<?> submitLeaveRequest(@PathVariable Long userId, @RequestBody LeaveRequest leaveRequest) {
        try {
            LeaveRequest request = leaveService.submitLeaveRequest(userId, leaveRequest);
            return ResponseEntity.ok(request);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PatchMapping("/{id}/approve")
    public LeaveRequest approveLeaveRequest(@PathVariable Long id) {
        return leaveService.approveLeaveRequest(id);
    }

    @PatchMapping("/{id}/reject")
    public LeaveRequest rejectLeaveRequest(@PathVariable Long id) {
        return leaveService.rejectLeaveRequest(id);
    }

    @GetMapping("/etat/{etat}")
    public ResponseEntity<List<LeaveRequestDto> >getRequestsByEtat(@PathVariable Etat etat) {
        return ResponseEntity.ok(leaveService.getRequestsByEtat(etat));
    }
    @GetMapping("/{user_id}/etat/{status}")
    public ResponseEntity<List<LeaveRequestDto>> getLeavesByStatus(
        @PathVariable Long user_id,
        @PathVariable Etat status){
    return ResponseEntity.ok( leaveService.getRequestsByUserIdAndEtat(user_id, status)  );  
    }
    @GetMapping("/{user_id}/history")
    public ResponseEntity<List<LeaveRequestDto>> getApprovedLeaveHistory(@PathVariable Long user_id) {
        // Appel au service pour récupérer uniquement les congés approuvés
        List<LeaveRequestDto> approvedLeaves = leaveService.getRequestsByUserIdAndEtat(user_id, Etat.APPROVED);
        return ResponseEntity.ok(approvedLeaves);
    }
@DeleteMapping("/{id}")
public void delete(@PathVariable long id) {
	leaveService.deleteById(id);
}
@PutMapping("/{id}")
public ResponseEntity<LeaveRequest> updateLeaveRequest(@PathVariable Long id,@RequestBody LeaveRequest leave){
	LeaveRequest existingLeave=leaveService.findById(id)
			.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"leave request not found"));
	existingLeave.setEtt(leave.getEtt());
	leaveService.save(existingLeave);
	return ResponseEntity.ok(existingLeave);
}
@GetMapping("/leave-requests")
public List<LeaveRequest>getLeaveRequest(@RequestParam int month){
	return leaveService.getLeaveRequestsByMonth(month);
}
}

















