package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.demo.modele.Etat;
import com.example.demo.modele.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaveHourDto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties("leaveHours")
    private User user;

    private int hoursRequested;
    private String cause;

    @Enumerated(EnumType.STRING)
    private Etat ett; // PENDING, APPROVED, REJECTED
    private LocalTime startHour;
    private LocalTime endHour;
}
