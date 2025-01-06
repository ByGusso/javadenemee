package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Model.Person;
import com.example.demo.repo.PersonRepository;

@Controller
@RequestMapping("/Personlar")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    @GetMapping("/")
    public String listPersonlar(Model model) {
        List<Person> Personlar = personRepository.findAll();
        model.addAttribute("Personlar", Personlar);
        return "Personlar";
    }

    @GetMapping("/yeni")
    public String yeniPerson(Model model) {
        model.addAttribute("Person", new Person());
        return "kullanici_form";
    }

    @PostMapping("/kaydet")
    public String kaydetPerson(@ModelAttribute Person Person) {
        personRepository.save(Person);
        return "redirect:/Personlar/";
    }

    @GetMapping("/duzenle/{id}")
    public String duzenlePerson(@PathVariable Long id, Model model) {
        Person Person = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Geçersiz kullanıcı id:" + id));
        model.addAttribute("Person", Person);
        return "Person_form";
    }

    @GetMapping("/sil/{id}")
    public String silPerson(@PathVariable Long id) {
        personRepository.deleteById(id);
        return "redirect:/Personlar/";
    }
    }

    

