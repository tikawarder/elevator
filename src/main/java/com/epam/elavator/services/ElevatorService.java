package com.epam.elavator.services;

import com.epam.elavator.domain.Elevator;
import com.epam.elavator.domain.Movement;
import com.epam.elavator.domain.report.ReportMovement;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.abs;

@Service
public class ElevatorService {
    private final static int DEFAULT_CAPACITY = 5;

    //use dependeny injection later
    private Elevator elevator;

    public Elevator getElevator() {
        return elevator;
    }

    public void startElevator(List<Movement> movements){
        elevator = Elevator.builder()
                .movements(movements)
                .capacity(DEFAULT_CAPACITY)
                .build();
        elevator.logMovements();
    }

    public int getPeopleTraveledTotal(){
        return elevator.getReports().stream()
                       .mapToInt(ReportMovement::getUnloadPeople)
                       .sum();
    }

    public int getStoriesTraveledTotal(){
        return elevator.getReports().stream()
                       .mapToInt(movements->abs(movements.getTo()- movements.getFrom()))
                       .sum();
    }
//    public boolean isMovementPresent(){ //maybe @Valid is enough
//        return elevator
//                .getMovements()
//                .size() > 0;
//    }
//
//    public void start(){
//        elevator.logMovements();
//    }

    public List<ReportMovement> getMovements(){
        return elevator.getReports();
    }
}
