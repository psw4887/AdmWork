package com.nhnacademy.controller;

import com.nhnacademy.domain.DeathRequest;
import com.nhnacademy.service.BirthDeathReportResidentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents/{serialNumber}/death")
public class DeathReportController {
    private final BirthDeathReportResidentService service;

    public DeathReportController(BirthDeathReportResidentService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void deathReportRegister(@PathVariable("serialNumber")int sNum,
                                    @RequestBody DeathRequest request) {
        service.deathRegister(sNum, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{targetSerialNumber}")
    public void birthReportModify(@PathVariable("serialNumber")int sNum,
                                  @PathVariable("targetSerialNumber")int tNum,
                                  @RequestBody DeathRequest request) {
        service.deathModify(sNum, tNum, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{targetSerialNumber}")
    public void birthReportDelete(@PathVariable("serialNumber")int sNum,
                                  @PathVariable("targetSerialNumber")int tNum) {
        service.deathDelete(sNum, tNum);
    }
}
