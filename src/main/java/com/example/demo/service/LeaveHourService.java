package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LeaveHourDto;
import com.example.demo.mapper.LeaveHourMapper;
import com.example.demo.modele.Etat;
import com.example.demo.modele.LeaveHour;
import com.example.demo.modele.User;
import com.example.demo.repository.LeaveHourRepository;
import com.example.demo.repository.UserRepos;

@Service
public class LeaveHourService {
@Autowired
private LeaveHourRepository repository;
@Autowired
UserRepos userRepository;
public LeaveHour save(LeaveHour hour) {
	return repository.save(hour);
}
public Optional<LeaveHour>findById(long id){
	return repository.findById(id);
}
public List<LeaveHourDto>getAllLeave(){
	return repository.findAll()
			.stream()
			.map(LeaveHourMapper::convertToDto)
			.collect(Collectors.toList());
}
public void deleteById(long id) {
	repository.deleteById(id);
}
public List<LeaveHourDto> getRequestByUserIdAndEtt(Long user_id,Etat ett){
	return repository.findAllByUserIdAndEtt(user_id, ett)
		.stream().map(LeaveHourMapper::convertToDto)
		.collect(Collectors.toList());
}
public List<LeaveHourDto> getRequestByEtat(Etat ett){
	return repository.findByEtt(ett)
			.stream().map(LeaveHourMapper::convertToDto)
			.collect(Collectors.toList());
}
public LeaveHour submitLeaveRequest(Long userId,LeaveHour leaveHour) {
	User user=userRepository.findById(userId)
			.orElseThrow(()-> new RuntimeException("user not found"));
	leaveHour.setUser(user);
	leaveHour.setEtt(Etat.APPROVED);
	return repository.save(leaveHour);
}
}
