package com.epam.elavator.services;

import com.epam.elavator.domain.Elevator;
import com.epam.elavator.domain.report.ReportMovement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Math.abs;

@Service
public class ReportService {

    @Autowired
    Elevator elevator;

    public int getPeopleTraveledTotal(){
        return elevator.getReports().stream()
                .mapToInt(ReportMovement::getUnloadPeople)
                .sum();
    }

    public int getStoriesTraveledTotal(){
        return elevator.getReports().stream()
                .mapToInt(movements->abs(movements.getFrom()- movements.getTo()))
                .sum();
    }
}
