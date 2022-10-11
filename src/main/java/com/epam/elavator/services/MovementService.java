package com.epam.elavator.services;

import com.epam.elavator.domain.Elevator;
import com.epam.elavator.domain.Movement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementService {
    private final static int DEFAULT_CAPACITY = 5;

    @Autowired
    private Elevator elevator;

    public void setCapacity (int capacity){
        elevator.builder().capacity(capacity).build();
    }

    public void setMovements (List<Movement> movements){
        elevator.builder().movements(movements)
                        .capacity(DEFAULT_CAPACITY) //overrides the capacity :-(
                        .build();
    }

    public boolean isMovementPresent(){ //maybe @Valid is enough
        return elevator.getMovements().size()>0 ? true : false;
    }

    public void start(){
        elevator.logMovements();
    }
}
