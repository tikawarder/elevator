package com.epam.elavator.controllers;

import com.epam.elavator.domain.report.ReportMovement;
import com.epam.elavator.services.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/databaseconnection")
public class DatabaseConnectionController {

    @Autowired
    MovementService service;

    @GetMapping ("/getmovements")
    public ResponseEntity<List<ReportMovement>> getMovements(){
        return ResponseEntity.ok(service.getMovements());
    }
}
