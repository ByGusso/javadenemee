package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.Kullanici;
import com.example.demo.repo.KullaniciRepository;

import java.util.List;

@Controller
@RequestMapping("/kullanicilar")
public class KullaniciController {

    @Autowired
    private KullaniciRepository kullaniciRepository;

    @GetMapping("/")
    public String listKullanicilar(Model model) {
        List<Kullanici> kullanicilar = kullaniciRepository.findAll();
        model.addAttribute("kullanicilar", kullanicilar);
        return "kullanicilar";
    }

    @GetMapping("/yeni")
    public String yeniKullanici(Model model) {
        model.addAttribute("kullanici", new Kullanici());
        return "kullanici_form";
    }

    @PostMapping("/kaydet")
    public String kaydetKullanici(@ModelAttribute Kullanici kullanici) {
        kullaniciRepository.save(kullanici);
        return "redirect:/kullanicilar/";
    }

    @GetMapping("/duzenle/{id}")
    public String duzenleKullanici(@PathVariable Long id, Model model) {
        Kullanici kullanici = kullaniciRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Geçersiz kullanıcı id:" + id));
        model.addAttribute("kullanici", kullanici);
        return "kullanici_form";
    }

    @GetMapping("/sil/{id}")
    public String silKullanici(@PathVariable Long id) {
        kullaniciRepository.deleteById(id);
        return "redirect:/kullanicilar/";
    }
}
