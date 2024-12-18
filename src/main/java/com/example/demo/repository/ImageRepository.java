package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.modele.Image;
import com.example.demo.modele.LeaveRequest;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
