package com.nhnacademy.controller;

import com.nhnacademy.domain.vo.MovementRequest;
import com.nhnacademy.service.HouseholdMovementAddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/household/{householdSerialNumber}/movement")
public class HouseholdMovementController {
    private final HouseholdMovementAddressService service;

    public HouseholdMovementController(HouseholdMovementAddressService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void movementRegister(@PathVariable("householdSerialNumber") int hNum,
                                 @RequestBody MovementRequest movementRequest) {
        service.movementRegister(hNum, movementRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{reportDate}")
    public void movementModify(@PathVariable("householdSerialNumber") int hNum,
                               @PathVariable("reportDate") Date date,
                               @RequestBody MovementRequest movementRequest) {
        service.movementModify(hNum, date, movementRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{reportDate}")
    public void movementDelete(@PathVariable("householdSerialNumber") int hNum,
                               @PathVariable("reportDate") Date date) {
        service.movementDelete(hNum, date);
    }
}
