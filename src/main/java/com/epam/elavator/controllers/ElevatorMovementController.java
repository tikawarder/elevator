package com.epam.elavator.controllers;

import com.epam.elavator.domain.Movement;
import com.epam.elavator.services.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/elevator")
public class ElevatorMovementController {

    @Autowired
    ElevatorService service;

    @PostMapping("/movements")
    public ResponseEntity<String> getMovements(@Valid @RequestBody List<Movement> movements) {
        service.startElevator(movements);
        return ResponseEntity.ok("Movement is valid"); //what to do if validation says not valid?
    }

//    @PostMapping("/capacity")
//    public ResponseEntity<String> getCapacity(@RequestBody @Min(1) @Max(10) int capacity) {
//        service.setCapacity(capacity);
//        return ResponseEntity.ok("Capacity is valid");
//    }

//    @GetMapping("/start") //lets have more nicer start of elevator movement
//    public ResponseEntity.BodyBuilder start() {
//        if (service.isMovementPresent()) {
//            service.start();
//            return ResponseEntity.ok();
//        } else return ResponseEntity.badRequest();
//    }
}
