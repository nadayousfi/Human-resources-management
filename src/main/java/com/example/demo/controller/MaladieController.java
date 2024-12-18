package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MaladieDto;
import com.example.demo.modele.Maladie;
import com.example.demo.service.MaladieServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/maladie")
@CrossOrigin(origins="http://localhost:4200")
public class MaladieController {
@Autowired
private MaladieServiceImpl service;
@GetMapping
public ResponseEntity<List<MaladieDto>> getAll(){
	return ResponseEntity.ok(service.getAllMaladie());
}
@PostMapping("/{userId}")
public ResponseEntity<?>submitMaladie(@PathVariable Long userId,@RequestBody Maladie maladie){
	try {
		Maladie maladi=service.submitMaladie(userId, maladie);
		return ResponseEntity.ok(maladi);
	}catch (RuntimeException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
}
@GetMapping("{user_id}/history")
public ResponseEntity<List<MaladieDto>>getMaldie(@PathVariable Long user_id){
	return ResponseEntity.ok(service.getMaldieByUserId(user_id));
}
@DeleteMapping("/{id}")
public void delete(@PathVariable long id) {
	service.deleteById(id);
}
@GetMapping("/month")
public List<Maladie> getMaladies(@RequestParam int month){
	return service.getMaladieByMonth(month);
}
}