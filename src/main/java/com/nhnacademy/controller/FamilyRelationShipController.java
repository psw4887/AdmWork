package com.nhnacademy.controller;

import com.nhnacademy.domain.FamilyRelationshipModifyRequest;
import com.nhnacademy.domain.FamilyRelationshipRegisterRequest;
import com.nhnacademy.service.FamilyRelationShipService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/residents/{serialNumber}/relationship")
public class FamilyRelationShipController {
    private final FamilyRelationShipService service;

    public FamilyRelationShipController(FamilyRelationShipService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void familyRegister(@PathVariable("serialNumber")int sNum,
                               @RequestBody FamilyRelationshipRegisterRequest registerRequest) {
        service.FamilyRelationShipRegister(sNum, registerRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{familySerialNumber}")
    public void familyModify(@PathVariable("serialNumber")int sNum,
                             @PathVariable("familySerialNumber")int fNum,
                             @RequestBody FamilyRelationshipModifyRequest modifyRequest) {
        service.FamilyRelationShipModify(sNum, fNum, modifyRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{familySerialNumber}")
    public void familyDelete(@PathVariable("serialNumber")int sNum,
                             @PathVariable("familySerialNumber")int fNum) {
        service.FamilyRelationShipDelete(sNum, fNum);
    }
}
