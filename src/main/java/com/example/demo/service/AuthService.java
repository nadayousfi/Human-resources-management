package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modele.User;
import com.example.demo.repository.UserRepos;


@Service
public class AuthService {
	@Autowired
    private UserRepos userRepository;

    
}
