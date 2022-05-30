package com.nhnacademy.controller;

import com.nhnacademy.domain.dto.family.FamilyCertResidentDTO;
import com.nhnacademy.domain.dto.family.FamilyCertificateDTO;
import com.nhnacademy.service.CertificateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/certificates")
public class CertificateIssueController {

    private final CertificateService cService;

    public CertificateIssueController(CertificateService service) {
        this.cService = service;
    }

    @GetMapping("/family")
    public String family(@RequestParam ("sNum") int sNum,
                         Model model) {
        FamilyCertificateDTO dto = cService.getFamilyCertificate(sNum);
        List<FamilyCertResidentDTO> residentDTOList = dto.getResidents();

        model.addAttribute("model", dto);

        model.addAttribute("list", residentDTOList);

        return "certificate/family";
    }

    @GetMapping("/registration")
    public String registration(@RequestParam("sNum") int sNum,
                               Model model) {
        return "certificate/registration";
    }

    @GetMapping("/birth")
    public String birth(@RequestParam("sNum") int sNum,
                        Model model) {
        return "certificate/birth";
    }

    @GetMapping("/death")
    public String death(@RequestParam("sNum") int sNum,
                        Model model) {
        return "certificate/death";
    }

    @GetMapping("/list")
    public String viewList() {
        return "certificate/list";
    }
}
