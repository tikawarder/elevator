package com.epam.elavator.controllers;

import com.epam.elavator.domain.report.ReportMovement;
import com.epam.elavator.integration.OperationsIntegration;
import com.epam.elavator.services.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/databaseconnection")
public class DatabaseConnectionController {

    @Autowired
    ElevatorService service;

    @Autowired
    OperationsIntegration integration;

    @PostMapping ("/postmovements")
    public ResponseEntity<List<ReportMovement>> postMovements() {
        List<ReportMovement> movements = service.getMovements();
        return ResponseEntity.ok(integration.postOperations(movements));
    }
}
