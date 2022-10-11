package com.epam.elavator.domain;

import com.epam.elavator.domain.report.ReportMovement;
import com.epam.elavator.domain.report.State;
import lombok.Builder;
import lombok.Value;
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
    ReportMovement report;
    List<ReportMovement> reports;

    @Min(1)
    @Max(10)
    int capacity;

    @NotEmpty(message = "At least 1 movement has to be")
    List<@Valid Movement> movements;

    public void logMovements(){ //handle out of capacity cases
        int from = 0;
        log.info("Elevator in standby at {} level.", from);
        addToReports(report.builder()
                            .from(from)
                            .State(State.STANDBY)
                            .build());
        for(Movement movement: movements){
            addToReports(report.builder()
                               .from(from)
                               .to(movement.getFrom())
                               .loadPeople(movement.getPeople())
                               .State(getState(from, movement.getFrom()))
                               .build());
            log.info("Moving to {} level",movement.getFrom());
            log.info("Loading {} people", movement.getPeople());
            addToReports(report.builder()
                               .from(movement.getFrom())
                               .to(movement.getTo())
                               .unloadPeople(movement.getPeople())
                               .State(getState(movement.getFrom(), movement.getTo()))
                               .build());
            log.info("Moving to {} level",movement.getTo());
            log.info("Unloading {} people",movement.getPeople());
            from = movement.getTo();
        }
        log.info("Elevator in standby");
    }

    private void addToReports(ReportMovement report){
        reports.add(report);
    }

    private State getState (int from, int to){
       if ((to-from)>0) return State.UP;
       if ((to-from)<0) return State.DOWN;
       return State.STANDBY; //think over this
    }
}
