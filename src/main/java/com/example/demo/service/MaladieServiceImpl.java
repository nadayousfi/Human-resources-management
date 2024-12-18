package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MaladieDto;
import com.example.demo.mapper.MaladieMapper;
import com.example.demo.modele.Maladie;
import com.example.demo.modele.User;
import com.example.demo.repository.MaldieRepository;
import com.example.demo.repository.UserRepos;

@Service
public class MaladieServiceImpl implements maladieService{
@Autowired
MaldieRepository repository;
@Autowired
UserRepos userRrepository;
public Maladie save(Maladie maladie) {
	return repository.save(maladie);
}
public Optional<Maladie> findById(long id){
	return repository.findById(id);
}
public List<MaladieDto> getAllMaladie(){
	return repository.findAll()
			.stream()
			.map(MaladieMapper::convertToDto)
			.collect(Collectors.toList());
}
public void deleteById(long id) {
	repository.deleteById(id);
}
public Maladie submitMaladie(Long userId,Maladie maladie) {
	User user=userRrepository.findById(userId)
			.orElseThrow(()-> new RuntimeException("user not found"));
	maladie.setUser(user);
	return repository.save(maladie);
			
}
public List<MaladieDto> getMaldieByUserId(Long user_id){
	return repository.findByUserId(user_id)
			.stream().map(MaladieMapper::convertToDto)
			.collect(Collectors.toList());
}
@Override
public List<Maladie> getMaladieByMonth(int month){
	return repository.findByMonth(month);
}
@Override
public List<Maladie> getAllMaladies() {
	return repository.findAll();
}
}
