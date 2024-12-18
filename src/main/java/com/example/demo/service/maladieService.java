package com.example.demo.service;

import java.util.List;

import com.example.demo.modele.Maladie;

public interface maladieService {
List<Maladie> getMaladieByMonth(int month);
List<Maladie>getAllMaladies();
}
