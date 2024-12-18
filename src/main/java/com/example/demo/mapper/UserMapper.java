package com.example.demo.mapper;

import org.modelmapper.ModelMapper;

import com.example.demo.dto.UserDto;
import com.example.demo.modele.User;

public class UserMapper {
public static final ModelMapper modelMapper=new ModelMapper();
public static UserDto convertToDto(User user) {
	return modelMapper.map(user,UserDto.class);
}
public static User convertToEntity(UserDto userdto) {
	return modelMapper.map(userdto, User.class);
}
}
