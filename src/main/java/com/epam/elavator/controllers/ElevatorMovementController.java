package com.epam.elavator.controllers;

import com.epam.elavator.domain.Movement;
import com.epam.elavator.services.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerResponse;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping("/elevator")
public class ElevatorMovementController {

    @Autowired
    MovementService service;

    @PostMapping("/movements")
    public ResponseEntity<String> getMovements(@Valid @RequestBody List<Movement> movements) {
        service.setMovements(movements);
        return ResponseEntity.ok("Movement is valid"); //what to do if validation says not valid?
    }

    @GetMapping("/capacity")
    public ResponseEntity<String> getCapacity(@RequestParam @Min(1) @Max(10) int capacity) {
        service.setCapacity(capacity);
        return ResponseEntity.ok("Capacity is valid");
    }

    @GetMapping("/start") //lets have more nicer start of elevator movement
    public ResponseEntity.BodyBuilder start() {
        if (service.isMovementPresent()) {
            service.start();
            return ResponseEntity.ok();
        } else return ResponseEntity.badRequest();
    }
}
