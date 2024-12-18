package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.LeaveHourDto;
import com.example.demo.modele.Etat;
import com.example.demo.modele.LeaveHour;
import com.example.demo.repository.LeaveHourRepository;
import com.example.demo.service.LeaveHourService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/hours")
@CrossOrigin(origins = "https://localhost:4200")
public class LeavHourController {
@Autowired
private LeaveHourService service;
@Autowired
private LeaveHourRepository repository;

@GetMapping
public ResponseEntity<List<LeaveHourDto>>getAll(){
	return ResponseEntity.ok(service.getAllLeave());
}
@GetMapping("etat/{etat}")
public ResponseEntity<List<LeaveHourDto>> getRequestByEtat(@PathVariable Etat etat){
	return ResponseEntity.ok(service.getRequestByEtat(etat));
}
@GetMapping("{user_id}/etat/{status}")
public ResponseEntity<List<LeaveHourDto>> getLeavesByStatus(@PathVariable Long user_id,@PathVariable Etat status){
	return ResponseEntity.ok(service.getRequestByUserIdAndEtt(user_id, status));
}
@GetMapping("{user_id}/history")
public ResponseEntity<List<LeaveHourDto>> getApprovedLeaveHistory(@PathVariable Long user_id){
	List<LeaveHourDto>approvedLeaves=service.getRequestByUserIdAndEtt(user_id, Etat.APPROVED);
	return ResponseEntity.ok(approvedLeaves);
}
@DeleteMapping("/{id}")
public void delete(@PathVariable long id) {
	service.deleteById(id);
}
@PutMapping("/{id}")
public ResponseEntity<LeaveHour>updateLeaveRequest( @PathVariable Long id,@RequestBody LeaveHour leave){
	LeaveHour existLeave=service.findById(id)
			.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"leave request"));
			existLeave.setEtt(leave.getEtt());
			service.save(existLeave);
			return ResponseEntity.ok(existLeave);
}

@GetMapping("/{userId}/approved-hours")
public int getApprovedHours(@PathVariable Long userId) {
return repository.findAllByUserIdAndEtt(userId, Etat.APPROVED)
		.stream()
		.mapToInt(LeaveHour::getHoursRequested)
		.sum();
}
@PostMapping("/{userId}")
public ResponseEntity<?>submitLeaveRequest(@PathVariable Long userId,@RequestBody LeaveHour leaveHour){
	try {
		LeaveHour request=service.submitLeaveRequest(userId, leaveHour);
		return ResponseEntity.ok(request);
	
	}
	catch (RuntimeException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
}
}
