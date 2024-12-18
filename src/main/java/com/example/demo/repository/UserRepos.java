package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modele.User;

public interface UserRepos extends JpaRepository<User, Long> {
	Optional<User>  findByEmail(String email);

}
