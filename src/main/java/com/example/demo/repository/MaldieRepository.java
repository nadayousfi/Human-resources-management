package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modele.Maladie;

public interface MaldieRepository extends JpaRepository<Maladie, Long> {
	@Query("select lr from Maladie lr WHERE MONTH(lr.startDate) =:month")
	List<Maladie> findByMonth(@Param("month")int month);
	List<Maladie> findByUserId(Long id);
}
