package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.modele.User;
import com.example.demo.repository.UserRepos;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@Autowired
	private  UserService service;
	
	@Autowired
    private UserRepos userRepository;

    @GetMapping
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                  
        		.map(UserMapper::convertToDto)
                    .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto>getUser(@PathVariable long id){
    	return ResponseEntity.ok(service.getById(id));
    }
   @PostMapping
   public UserDto createUser(@RequestBody UserDto userDto) {
	   return service.save(userDto);
   }
}
