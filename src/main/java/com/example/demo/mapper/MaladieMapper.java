package com.example.demo.mapper;

import org.modelmapper.ModelMapper;

import com.example.demo.dto.MaladieDto;
import com.example.demo.modele.Maladie;

public class MaladieMapper {
public static final ModelMapper modelMapper=new ModelMapper();
public static MaladieDto convertToDto(Maladie maladie) {
	return modelMapper.map(maladie, MaladieDto.class);
}
public static Maladie convertToEntity(MaladieDto dto) {
	return modelMapper.map(dto, Maladie.class);
}
}
