package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modele.Etat;
import com.example.demo.modele.LeaveHour;

public interface LeaveHourRepository extends JpaRepository<LeaveHour, Long> {
List<LeaveHour> findByEtt(Etat ett);
List<LeaveHour>findAllByUserIdAndEtt(long id,Etat ett);
}
