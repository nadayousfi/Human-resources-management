package com.example.demo.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modele.Etat;
import com.example.demo.modele.LeaveRequest;

public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, Long>{
    List<LeaveRequest> findByEtt(Etat ett);
    List<LeaveRequest> findAllByUserIdAndEtt(Long id, Etat ett);
   // Optional<LeaveRequest> findById(Long id);
    @Query("select lr from LeaveRequest lr WHERE MONTH (lr.startDate) =:month")
	List<LeaveRequest> findByMonth(@Param("month") int month);
}
