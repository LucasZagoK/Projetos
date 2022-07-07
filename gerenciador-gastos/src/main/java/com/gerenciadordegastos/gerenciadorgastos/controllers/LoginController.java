package com.gerenciadordegastos.gerenciadorgastos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gerenciadordegastos.gerenciadorgastos.models.Adm;
import com.gerenciadordegastos.gerenciadorgastos.repositorio.AdmsRepo;

@Controller
public class LoginController {
    @Autowired
    private AdmsRepo repo;

    @GetMapping("/login")
    public String index(){
        return "login/index";
    }

    @PostMapping("/logar")
    public String logar(Model model, Adm admParam, String lembrar){
        Adm adm = this.repo.login(admParam.getEmail(), admParam.getSenha());
        if (adm != null) {
            return "redirect:/";
        } else {
            model.addAttribute("erro", "User ou senha invalido");
            return "login/index";
        }
    }
}
