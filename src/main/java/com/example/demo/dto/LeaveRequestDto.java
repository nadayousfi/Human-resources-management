package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


import com.example.demo.modele.Etat;
import com.example.demo.modele.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestDto {
	 private Long id;

	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)

	    @JsonIgnoreProperties("leaveRequests")
	    private User user;

	    private int daysRequested;
	    @Enumerated(EnumType.STRING)
	    private Etat ett; // PENDING, APPROVED, REJECTED

	    private LocalDate startDate;
	    private LocalDate endDate;
}
