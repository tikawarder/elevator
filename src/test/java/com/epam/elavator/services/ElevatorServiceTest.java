package com.epam.elavator.services;

import com.epam.elavator.domain.Elevator;
import com.epam.elavator.domain.Movement;
import com.epam.elavator.domain.report.Operation;
import com.epam.elavator.domain.report.ReportMovement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ElevatorServiceTest {
    public static final int PEOPLE_TRAVELED_TOTAL = 2;
    public static final int LEVELS_TRAVELED_TOTAL = 3;
    @InjectMocks
    private ElevatorService underTest;
    @Mock
    private Elevator elevator;
    private List<ReportMovement> reports;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testStartElevatorShouldCreateNewElevatorWithMovementAndCapacityAndRunLogMovements(){
        //given
        List<Movement> movements = loadMovements();
        //when
        underTest.startElevator(movements);
        Elevator elevator = underTest.getElevator();
        //then
        assertEquals(5, elevator.getCapacity());
        assertEquals(movements, elevator.getMovements());
    }

    @Test
    void testGetPeopleTraveledTotalShouldReturnSumOfTraveledPeopleFromReportMovement(){
        //given
        loadReports();
        when(elevator.getReports()).thenReturn(reports);
        //when
        verifyNoInteractions(elevator);
        int result = underTest.getPeopleTraveledTotal();
        //then
        verify(elevator).getReports();
        verifyNoMoreInteractions(elevator);
        assertEquals(PEOPLE_TRAVELED_TOTAL, result);
    }

    @Test
    void testGetStoriesTraveledTotalShouldReturnSumOfTraveledLevelsFromReportMovement(){
        //given
        loadReports();
        when(elevator.getReports()).thenReturn(reports);
        //when
        verifyNoInteractions(elevator);
        int result = underTest.getStoriesTraveledTotal();
        //then
        verify(elevator).getReports();
        verifyNoMoreInteractions(elevator);
        assertEquals(LEVELS_TRAVELED_TOTAL, result);
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

    private List<Movement> loadMovements(){
        Movement move = Movement.builder()
                                .from(1)
                                .to(3)
                                .people(2)
                                .build();
        return List.of(move);
    }
}