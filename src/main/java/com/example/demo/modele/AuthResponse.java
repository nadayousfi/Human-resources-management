package com.example.demo.modele;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
	   private String message;
	    private boolean success;
}
