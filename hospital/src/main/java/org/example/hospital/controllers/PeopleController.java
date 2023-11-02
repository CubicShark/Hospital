package org.example.hospital.controllers;

import javax.validation.Valid;
import org.example.hospital.models.Person;
import org.example.hospital.services.PeopleService;
import org.example.hospital.services.WardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final WardsService wardsService;

    @Autowired
    public PeopleController(PeopleService peopleService, WardsService wardsService) {
        this.peopleService = peopleService;
        this.wardsService = wardsService;
    }


    @GetMapping("/checkin")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/checkin";
    }

    @PostMapping("/checkin")
    public String checkInPerson(@ModelAttribute("person") @Valid Person person,
                                BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "people/checkin";

        for (int i = 0; i < wardsService.findAll().size(); i++) {

            if (!wardsService.findAll().get(i).isFull()){
                person.setWard(wardsService.findAll().get(i));
                peopleService.save(person);

                if(wardsService.findAll().get(i).getCapacity() == wardsService.findAll().get(i).getPeople().size()){
                    wardsService.updateIsFull(true,wardsService.findAll().get(i).getId());
                }
                return "redirect:/people/checkinConfirmed";
            }
        }

        return "redirect:/people/checkinNotConfirmed";
    }

    @GetMapping("/checkinNotConfirmed")
    public String checkinNotConfirmed(){
        return "people/checkinNotConfirmed";
    }

    @GetMapping("/checkinConfirmed")
    public String checkinConfirmed(Model model){
        model.addAttribute("person",peopleService.findAll().get(peopleService.findAll().size()-1));
        return "people/checkinConfirmed";
    }

    @GetMapping("/findPerson")
    public String findPerson(Model model){
        model.addAttribute("person",new Person());
        return "people/findPerson";
    }

    @PostMapping("/findPerson")
    public String findPerson(@ModelAttribute("person") @Valid Person person,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors())
            return "people/findPerson";

        redirectAttributes.addAttribute("name",person.getName());
        redirectAttributes.addAttribute("age",person.getAge());
        redirectAttributes.addAttribute("gender",person.getGender());
        return "redirect:/people/foundedPerson";
    }

    @GetMapping("/foundedPerson")
    public String foundedPerson(@RequestParam(name = "name") String name,
                                @RequestParam(name = "age") int age,
                                @RequestParam(name = "gender") String gender,Model model){
        model.addAttribute("people",peopleService.findByNameAndAgeAndGender(name,age,gender));
        return "people/foundedPerson";
    }

    @GetMapping("/{id}")
    public String person(@PathVariable("id") int id, Model model) {
        model.addAttribute("ward", wardsService.findById(id));
        return "wards/index";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        wardsService.updateIsFull(false,peopleService.findById(id).getWard().getId());
        peopleService.deleteById(id);
        return "redirect:/hospital";
    }

    @DeleteMapping("/A{id}")
    public String AdminDelete(@PathVariable("id") int id){
        wardsService.updateIsFull(false,peopleService.findById(id).getWard().getId());
        peopleService.deleteById(id);
        return "redirect:/wards";
    }

}
