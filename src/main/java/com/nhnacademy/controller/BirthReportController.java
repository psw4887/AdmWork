package com.nhnacademy.controller;

import com.nhnacademy.domain.vo.BirthRequest;
import com.nhnacademy.service.BirthDeathReportResidentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents/{serialNumber}/birth")
public class BirthReportController {

    private final BirthDeathReportResidentService service;

    public BirthReportController(BirthDeathReportResidentService service) {
        this.service = service;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void birthReportRegister(@PathVariable("serialNumber")int sNum,
                                    @RequestBody BirthRequest request) {
        service.birthRegister(sNum, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{targetSerialNumber}")
    public void birthReportModify(@PathVariable("serialNumber")int sNum,
                                  @PathVariable("targetSerialNumber")int tNum,
                                  @RequestBody BirthRequest request) {
        service.birthModify(sNum, tNum, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{targetSerialNumber}")
    public void birthReportDelete(@PathVariable("serialNumber")int sNum,
                                  @PathVariable("targetSerialNumber")int tNum) {
        service.birthDelete(sNum, tNum);
    }

}
