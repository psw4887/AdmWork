package com.nhnacademy.controller;

import com.nhnacademy.domain.ResidentJoinRequest;
import com.nhnacademy.domain.ResidentView;
import com.nhnacademy.service.ResidentService;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("isEnd", 0);

        if(list.size() < 5) {
            model.addAttribute("isEnd", 1);
        }
        model.addAttribute("page", page);
        model.addAttribute("lists", list);

        return "resident/residentView";
    }

    @GetMapping("/joiner")
    public String readyLogin() {
        return "resident/joinForm";
    }

    @PostMapping("/joiner")
    public String registerJoin(@RequestParam("sNum") int sNum,
                             @RequestParam("id") String id,
                             @RequestParam("pw") String pw,
                             @RequestParam("email") String email) {
        residentService.residentRegisterForLogin(sNum, new ResidentJoinRequest(id, pw, email));
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteResident(@Param("sNum")int sNum) {
        residentService.residentDelete(sNum);
        return "redirect:/resident/view?page=0&size=5";
    }
}
