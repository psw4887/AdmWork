package com.nhnacademy.controller;

import com.nhnacademy.domain.dto.BirthDTO;
import com.nhnacademy.domain.dto.DeathDTO;
import com.nhnacademy.service.BirthDeathReportResidentService;
import com.nhnacademy.service.ResidentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/certificates")
public class CertificateIssueController {

    private final BirthDeathReportResidentService birthDeathReportResidentService;
    private final ResidentService residentService;

    public CertificateIssueController(BirthDeathReportResidentService birthDeathReportResidentService, ResidentService residentService) {
        this.birthDeathReportResidentService = birthDeathReportResidentService;
        this.residentService = residentService;
    }

    @PostMapping("/family")
    public String family(@RequestParam ("sNum") int sNum,
                         Model model) {
        return "certificate/family";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam("sNum") int sNum,
                               Model model) {
        return "certificate/registration";
    }

    @PostMapping("/birth")
    public String birth(@RequestParam("sNum") int sNum,
                        Model model) {
        List<BirthDTO> births = birthDeathReportResidentService.getBirthReport(sNum);
        model.addAttribute("births", births);
        return "certificate/birth";
    }

    @PostMapping("/death")
    public String death(@RequestParam("sNum") int sNum,
                        Model model) {
        List<DeathDTO> deaths = birthDeathReportResidentService.getDeathReport(sNum);
        model.addAttribute("Deaths", deaths);
        return "certificate/death";
    }

    @GetMapping("/list")
    public String viewList() {
        return "certificate/list";
    }
}
