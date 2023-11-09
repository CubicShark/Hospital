package org.example.hospital.controllers;

import javax.validation.Valid;
import org.example.hospital.models.Person;
import org.example.hospital.models.Ward;
import org.example.hospital.services.PeopleService;
import org.example.hospital.services.WardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wards")
public class WardsController {

    private final WardsService wardsService;

    @Autowired
    public WardsController(WardsService wardsService) {
        this.wardsService = wardsService;
    }


    @GetMapping()
    public String wards(Model model) {
        model.addAttribute("wards",wardsService.findAll());
        return "wards/show";
    }

    @GetMapping("/{id}")
    public String ward(@PathVariable("id") int id, Model model) {
        model.addAttribute("ward", wardsService.findById(id));
        return "wards/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id,Model model){
        if(wardsService.findById(id).getPeople().isEmpty()){
            wardsService.deleteById(id);
            return "redirect:/wards";
        }
        model.addAttribute("ward", wardsService.findById(id));

        model.addAttribute("confmessage", "There is people in ward!");
            return "wards/index";
    }

    @GetMapping("/new")
    public String createWardG(Model model){

        if(wardsService.findAll().size() >= 8){
            model.addAttribute("wards",wardsService.findAll());
            model.addAttribute("confmessage", "Max wards number reached!");
            return "wards/show";
        }

        model.addAttribute("ward",new Ward());
        return "wards/new";
    }

    @PostMapping("/new")
    public String createWardP(@ModelAttribute("ward") @Valid Ward ward,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "wards/new";

        wardsService.save(ward);
        return"redirect:/wards";
    }

}
