package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.UserDto;

public interface UserService {
public UserDto save(UserDto dto);
public List<UserDto> findall();
public UserDto getById(Long id);
}
