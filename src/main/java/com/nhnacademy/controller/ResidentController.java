package com.nhnacademy.controller;

import com.nhnacademy.domain.ResidentView;
import com.nhnacademy.service.ResidentService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/resident")
public class ResidentController {

    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping ("/view")
    public String viewResidentList(@RequestParam("page") Integer page,
                                   Model model) {
        PageRequest pageRequest = PageRequest.of(page, 5);
        List<ResidentView> list = residentService.allResidents(pageRequest);
        model.addAttribute("lists", list);
        return "resident/residentView";
    }

    @PostMapping("/delete")
    public String deleteResident(@Param("sNum")int sNum) {
        residentService.residentDelete(sNum);
        return "redirect:/resident/view?page=0&size=5";
    }
}
