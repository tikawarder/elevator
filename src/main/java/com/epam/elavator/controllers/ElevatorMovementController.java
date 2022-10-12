package com.epam.elavator.controllers;

import com.epam.elavator.domain.Movement;
import com.epam.elavator.services.ElevatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@Validated
@RequestMapping("/elevator")
public class ElevatorMovementController {

    @Autowired
    ElevatorService service;

    @PostMapping("/movements")
    public ResponseEntity.BodyBuilder getMovements(@Valid @RequestBody @NotEmpty List<Movement> movements) {
        service.startElevator(movements);
        return ResponseEntity.status(HttpStatus.ACCEPTED);
    }
}
