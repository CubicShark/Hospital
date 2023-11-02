package org.example.hospital.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hospital")
public class HospitalController {

    @RequestMapping()
    public String Home(){
        return "hospital/home";
    }

    @RequestMapping("/adminPanel")
    public String AdminPanel(){
        return "hospital/adminPanel";
    }

}
