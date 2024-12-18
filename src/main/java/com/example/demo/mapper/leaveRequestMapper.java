package com.example.demo.mapper;

import org.modelmapper.ModelMapper;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.modele.LeaveRequest;

public class leaveRequestMapper {
public static final ModelMapper modelMapper=new ModelMapper();
public static LeaveRequestDto converToDto(LeaveRequest leaveRequest) {
	return modelMapper.map(leaveRequest, LeaveRequestDto.class);
	
}
public static LeaveRequest convertToEntity(LeaveRequestDto leaveRequestDto) {
	return modelMapper.map(leaveRequestDto, LeaveRequest.class);
}
}
