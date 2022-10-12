package com.epam.elavator.domain;

import com.epam.elavator.domain.report.Operation;
import com.epam.elavator.domain.report.ReportMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorTest {
    public static final int EXCEEDED_CAPACITY = 1;
    public static final int CAPACITY = 5;
    private Elevator underTest;
    private List<ReportMovement> reports;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testLogMovementShouldContainsEveryReportMovement(){
        //given
        loadElevator(CAPACITY);
        //when
        loadReports();
        underTest.logMovements();
        //then
        assertTrue(reports.containsAll(underTest.getReports()));
    }

    @Test
    void testLogMovementShouldContainOnlyStandbyOperationWhenCapacityIsExceeded(){
        //given
        loadElevator(EXCEEDED_CAPACITY);
        //when
        underTest.logMovements();
        reports = underTest.getReports();
        //then
        for (ReportMovement movement: reports){
            assertEquals(Operation.STANDBY, movement.getState());
        }
    }

    private void loadElevator(int capacity){
        Movement move = Movement.builder()
                .from(1)
                .to(3)
                .people(2)
                .build();
        List<Movement> movements = List.of(move);
        underTest = Elevator.builder()
                .movements(movements)
                .capacity(capacity)
                .build();
    }

    private void loadReports(){
        ReportMovement report1 = ReportMovement.builder()
                .from(0)
                .State(Operation.STANDBY)
                .build();
        ReportMovement report2 = ReportMovement.builder()
                .from(0)
                .to(1)
                .State(Operation.MOVING)
                .build();
        ReportMovement report3 = ReportMovement.builder()
                .loadPeople(2)
                .State(Operation.LOAD)
                .build();
        ReportMovement report4 = ReportMovement.builder()
                .from(1)
                .to(3)
                .State(Operation.MOVING)
                .build();
        ReportMovement report5 = ReportMovement.builder()
                .unloadPeople(2)
                .State(Operation.UNLOAD)
                .build();
        ReportMovement report6 = ReportMovement.builder()
                .State(Operation.STANDBY)
                .build();
        reports = List.of(report1, report2, report3, report4, report5, report6);
    }
}