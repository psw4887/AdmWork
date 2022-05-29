package com.nhnacademy.controller;

import com.nhnacademy.domain.ResidentModifyRequest;
import com.nhnacademy.domain.ResidentRegisterRequest;
import com.nhnacademy.exception.ValidationFailedException;
import com.nhnacademy.service.ResidentService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/residents")
public class ResidentsController {

    private final ResidentService service;

    public ResidentsController(ResidentService residentService) {
        this.service = residentService;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void residentRegister(@Valid @RequestBody ResidentRegisterRequest residentRegisterRequest,
                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        service.residentRegister(residentRegisterRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{serialNumber}")
    public void residentModify(@PathVariable("serialNumber") int sNum,
                               @RequestBody ResidentModifyRequest residentModifyRequest) {
        service.residentModify(sNum, residentModifyRequest);
    }
}
