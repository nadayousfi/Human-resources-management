package com.example.demo.service;

import java.util.List;

import com.example.demo.modele.LeaveRequest;

public interface LeaveService {
List<LeaveRequest> getLeaveRequestsByMonth(int month);
}
