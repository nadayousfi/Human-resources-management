package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepos;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
private final UserRepos repository;
	@Override
	public UserDto save(UserDto dto) {
		return UserMapper.convertToDto(repository.save(UserMapper.convertToEntity(dto)));
	}

	@Override
	public List<UserDto> findall() {
		return repository.findAll()
				.stream()
				.map(UserMapper::convertToDto)
				.collect(Collectors.toList());
	}

	@Override
	public UserDto getById(Long id) {
		return repository.findById(id)
				.map(UserMapper::convertToDto)
				.orElseThrow(()-> new EntityNotFoundException("pas de user avec ce id"));
	}

}
