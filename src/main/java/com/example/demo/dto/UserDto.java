package com.example.demo.dto;

import java.util.List;

import com.example.demo.modele.Image;
import com.example.demo.modele.LeaveHour;
import com.example.demo.modele.LeaveRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Long id;
	 private String name;
	    private String email;
	  //  private int leaveBalance ; // Solde initial de congés
	    private String adresse;
	    private String cnr ;
	    private String role;
	    @JsonIgnoreProperties("user")
	    @OneToMany(mappedBy = "user")
	    private List<LeaveRequest> leaveRequests;
	    @JsonIgnoreProperties("user")
	    @OneToMany(mappedBy = "user")
	    private List<LeaveHour> leaveHours;
	    @OneToOne
	    private Image image;
}
