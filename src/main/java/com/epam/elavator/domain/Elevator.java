package com.epam.elavator.domain;

import lombok.Builder;
import lombok.Value;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Value
@Builder
@Slf4j
public class Elevator {


    @Min(1)
    @Max(10)
    int capacity;

    @NotEmpty(message = "At least 1 movement has to be")
    List<@Valid Movement> movements;

    public void startMoving(){
        log.info("Elevator in standby");
        for(Movement movement: movements){
            //first move to the first item
            log.info("Moving from {} to {}",movement.getFrom(), movement.getTo());
            log.info("Unloading {} persons",movement.getPeople());
        }
    }
}
