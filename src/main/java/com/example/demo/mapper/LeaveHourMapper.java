package com.example.demo.mapper;

import org.modelmapper.ModelMapper;

import com.example.demo.dto.LeaveHourDto;
import com.example.demo.modele.LeaveHour;

public class LeaveHourMapper {
public static final ModelMapper modelMapper=new ModelMapper(); 
public static LeaveHourDto convertToDto(LeaveHour leavehour) {
	return modelMapper.map(leavehour, LeaveHourDto.class);
}
public static LeaveHour convertToEntity(LeaveHourDto leave) {
	return modelMapper.map(leave, LeaveHour.class);
}
}
