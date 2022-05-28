package com.nhnacademy.controller;

import com.nhnacademy.domain.HouseholdCompositionRegisterRequest;
import com.nhnacademy.domain.HouseholdRegisterRequest;
import com.nhnacademy.service.HouseholdCompositionService;
import com.nhnacademy.service.HouseholdService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/household")
public class HouseholdController {

    private final HouseholdService householdService;
    private final HouseholdCompositionService householdCompositionService;

    public HouseholdController(HouseholdService householdService, HouseholdCompositionService householdCompositionService) {
        this.householdService = householdService;
        this.householdCompositionService = householdCompositionService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void householdRegister(@RequestBody HouseholdRegisterRequest request) {
        householdService.householdRegister(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{householdSerialNumber}")
    public void householdDelete(@PathVariable("householdSerialNumber") int hNum) {
        householdService.householdDelete(hNum);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{serialNumber}/{householdSerialNumber}/composition")
    public void householdCompositionRegister(@PathVariable("serialNumber") int sNum,
                                             @PathVariable("householdSerialNumber") int hNum,
                                             @RequestBody HouseholdCompositionRegisterRequest request) {
        householdCompositionService.householdCompositionRegister(sNum, hNum, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{serialNumber}/{householdSerialNumber}/composition")
    public void householdCompositionDelete(@PathVariable("serialNumber") int sNum,
                                           @PathVariable("householdSerialNumber") int hNum) {
        householdCompositionService.householdCompositionDelete(sNum, hNum);
    }
}
