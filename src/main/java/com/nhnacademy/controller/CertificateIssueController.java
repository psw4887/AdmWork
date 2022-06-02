package com.nhnacademy.controller;

import com.nhnacademy.domain.dto.birth.BirthDTO;
import com.nhnacademy.domain.dto.death.DeathDTO;
import com.nhnacademy.domain.dto.birth.family.FamilyCertResidentDTO;
import com.nhnacademy.domain.dto.birth.family.FamilyCertificateDTO;
import com.nhnacademy.domain.dto.registration.RegistrationDTO;
import com.nhnacademy.entity.CertificateIssue;
import com.nhnacademy.service.CertificateService;
import java.util.Objects;
import org.springframework.data.domain.PageRequest;
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
        RegistrationDTO dto = cService.getRegistrationCertificate(sNum);
        if(Objects.isNull(dto)) {
            return "redirect:/resident/view?page=0";
        }
        for(int i = 0; i < dto.getResidents().size(); i++) {
            if (dto.getResidents().get(i).getRelationshipCode().equals("본인")) {
                model.addAttribute("holder", dto.getResidents().get(i));
            }
        }
        model.addAttribute("dto", dto);
        model.addAttribute("list", dto.getResidents());
        model.addAttribute("address", dto.getAddresses());

        return "certificate/registration";
    }

    @GetMapping("/birth")
    public String birth(@RequestParam("sNum") int sNum,
                        Model model) {
        BirthDTO dto = cService.getBirthCertificate(sNum);
        model.addAttribute("dto", dto);
        return "certificate/birth";
    }

    @GetMapping("/death")
    public String death(@RequestParam("sNum") int sNum,
                        Model model) {
        DeathDTO dto = cService.getDeathCertificate(sNum);
        model.addAttribute("dto", dto);
        return "certificate/death";
    }

    @GetMapping("/list")
    public String viewList(@RequestParam("sNum") int sNum,
                           @RequestParam("page") Integer page,
                           Model model) {

        PageRequest pageRequest = PageRequest.of(page, 5);
        List<CertificateIssue> list = cService.getCertificateList(sNum, pageRequest);
        model.addAttribute("isEnd", 0);

        if(list.size() < 5) {
            model.addAttribute("isEnd", 1);
        }
        model.addAttribute("sNum", sNum);
        model.addAttribute("page", page);
        model.addAttribute("list", list);

        return "certificate/list";
    }
}
