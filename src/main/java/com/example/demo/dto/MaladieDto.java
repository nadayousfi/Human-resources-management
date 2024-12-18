package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.modele.Etat;
import com.example.demo.modele.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class MaladieDto {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
@JoinColumn(name = "user_id", nullable = false)
@JsonIgnoreProperties("maladies")
private User user;
private int daysRequested;
private LocalDate startDate;
private LocalDate endDate; 
}
