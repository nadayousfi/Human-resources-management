package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.modele.AuthResponse;
import com.example.demo.modele.User;
import com.example.demo.service.AuthService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@Data

@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuhtController {
	private final  AuthService authService;


	    
    
}
