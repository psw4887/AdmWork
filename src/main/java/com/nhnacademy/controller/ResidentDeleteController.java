package com.nhnacademy.controller;

import com.nhnacademy.service.ResidentService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/residents/delete")
public class ResidentDeleteController {

    private final ResidentService residentService;

    public ResidentDeleteController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @PostMapping
    public String deleteResident(@Param("sNum")int sNum) {
        residentService.residentDelete(sNum);
        return "redirect:/";
    }
}
