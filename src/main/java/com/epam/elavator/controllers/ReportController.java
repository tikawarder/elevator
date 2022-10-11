package com.epam.elavator.controllers;

import com.epam.elavator.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    ReportService service;

    @GetMapping("/people")
    public ResponseEntity<Integer> getPeopleTraveled(){
        return ResponseEntity.ok(service.getPeopleTraveledTotal());
    }

    @GetMapping("/stories")
    public ResponseEntity<Integer> getStoriesTraveled(){
        return ResponseEntity.ok(service.getStoriesTraveledTotal());
    }
}
