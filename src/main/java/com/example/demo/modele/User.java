package com.example.demo.modele;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties({"hibernateLazyInitialiser","handler"})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 private String name;
	    private String email;
	   // private int leaveBalance ; // Solde initial de congés
	    private String adresse;
	    private String cnr ;
	    private String role;
	    @JsonIgnoreProperties("user")
	    @OneToMany(mappedBy = "user")
	    private List<LeaveRequest> leaveRequests;
	    @JsonIgnoreProperties("user")
	    @OneToMany(mappedBy = "user")
	    private List<LeaveHour> leaveHours;
	    @OneToMany(mappedBy = "user")
	    private List<Maladie> maladies;
	    @OneToOne
	    private Image image;
}
