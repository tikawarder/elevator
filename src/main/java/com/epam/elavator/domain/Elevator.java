package com.epam.elavator.domain;

import com.epam.elavator.domain.report.ReportMovement;
import com.epam.elavator.domain.report.Operation;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Slf4j
public class Elevator {
    List<ReportMovement> reports;

    @Min(1)
    @Max(10)
    int capacity;

    @NotEmpty(message = "At least 1 movement has to be")
    List<@Valid Movement> movements;

    public void logMovements(){
        reports = new ArrayList<>();
        int from = 0;
        reports.add(ReportMovement.builder()
                            .from(from)
                            .State(Operation.STANDBY)
                            .build());
        log.info("Elevator in {} at {} level.", Operation.STANDBY, from);

        for(Movement movement: movements){
            if(movement.getPeople()>capacity) {
                log.info("Capacity {} exceeded the required {} people load. Do not stop here.", capacity, movement.getPeople());
            }
            else {
                reports.add(ReportMovement
                        .builder()
                        .from(from)
                        .to(movement.getFrom())
                        .State(Operation.MOVING)
                        .build());
                log.info("{} to {} level", Operation.MOVING, movement.getFrom());
                reports.add(ReportMovement
                        .builder()
                        .loadPeople(movement.getPeople())
                        .State(Operation.LOAD)
                        .build());
                log.info("{} {} people", Operation.LOAD, movement.getPeople());
                reports.add(ReportMovement
                        .builder()
                        .from(movement.getFrom())
                        .to(movement.getTo())
                        .State(Operation.MOVING)
                        .build());
                log.info("{} to {} level", Operation.MOVING, movement.getTo());
                reports.add(ReportMovement
                        .builder()
                        .unloadPeople(movement.getPeople())
                        .State(Operation.UNLOAD)
                        .build());
                log.info("{} {} people", Operation.UNLOAD, movement.getPeople());
            }
            from = movement.getTo();
        }

        reports.add(ReportMovement.builder()
                           .State(Operation.STANDBY)
                           .build());
        log.info("Elevator in {}", Operation.STANDBY);
    }
}
