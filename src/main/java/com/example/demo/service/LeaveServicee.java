package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modele.LeaveRequest;
import com.example.demo.modele.User;
import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.mapper.leaveRequestMapper;
import com.example.demo.modele.Etat;
import com.example.demo.repository.LeaveRequestRepo;
import com.example.demo.repository.UserRepos;
@Service
public class LeaveServicee implements LeaveService{
    @Autowired
    private LeaveRequestRepo  leaveRequestRepository;

    @Autowired
    private UserRepos userRepository;

    public LeaveRequest submitLeaveRequest(Long userId, LeaveRequest leaveRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        int totalApprovedDays = leaveRequestRepository.findAllByUserIdAndEtt(userId, Etat.PENDING)
                .stream()
                .mapToInt(LeaveRequest::getDaysRequested)
                .sum();

// Vérification si la demande dépasse le solde disponible
if (totalApprovedDays + leaveRequest.getDaysRequested() >45 ) {
throw new RuntimeException("Vous avez dépassé votre solde de congés disponible.");
}

leaveRequest.setUser(user);
leaveRequest.setEtt(Etat.APPROVED); // Définit l'état initial
return leaveRequestRepository.save(leaveRequest);
    }

    public LeaveRequest approveLeaveRequest(Long requestId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        User user = leaveRequest.getUser();
        //user.setLeaveBalance(user.getLeaveBalance() - leaveRequest.getDaysRequested());
        leaveRequest.setEtt(Etat.APPROVED); // Définit l'état approuvé

        userRepository.save(user);
        return leaveRequestRepository.save(leaveRequest);
    }

    public LeaveRequest rejectLeaveRequest(Long requestId) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        leaveRequest.setEtt(Etat.REJECTED); // Définit l'état rejeté
        return leaveRequestRepository.save(leaveRequest);
    }

    public List<LeaveRequestDto> getRequestsByEtat(Etat etat) {
        return leaveRequestRepository.findByEtt(etat).
        		stream()
        		.map(leaveRequestMapper::converToDto)
        		.collect(Collectors.toList());
    }
    public List<LeaveRequestDto> getRequestsByUserIdAndEtat(Long user_id,Etat etat) {
        return leaveRequestRepository.findAllByUserIdAndEtt(user_id, etat)
        		.stream().map(leaveRequestMapper::converToDto)
        		.collect(Collectors.toList());
    }
    public List<LeaveRequestDto> getAllRequest(){
    	return leaveRequestRepository.findAll()
    			.stream()
    			.map(leaveRequestMapper::converToDto)
    			.collect(Collectors.toList());
    }
     public void deleteById(long id) {
    	 
    	 leaveRequestRepository.deleteById(id);
     }
     public Optional<LeaveRequest> findById(long id){
    	 return leaveRequestRepository.findById(id);
     }
     public LeaveRequest save(LeaveRequest leave) {
    	 return leaveRequestRepository.save(leave);
     }

	@Override
	public List<LeaveRequest> getLeaveRequestsByMonth(int month) {
		return leaveRequestRepository.findByMonth(month);
	}
}