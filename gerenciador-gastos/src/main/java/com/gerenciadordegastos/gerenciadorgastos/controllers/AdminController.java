package com.gerenciadordegastos.gerenciadorgastos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gerenciadordegastos.gerenciadorgastos.models.Adm;
import com.gerenciadordegastos.gerenciadorgastos.repositorio.AdmsRepo;

@Controller
public class AdminController {

    @Autowired
    private AdmsRepo repo;

    @GetMapping("/admin")
    public String index(Model model){
        List<Adm> administradores = (List<Adm>)repo.findAll();
        model.addAttribute("admins", administradores); //usado para configurar o front
        return "admin/index";
    }

    @GetMapping("/admin/novo")
    public String novo(){        
        return "admin/novo";
    }

    @PostMapping("/admin/criar")
    public String criar(Adm adm){
        repo.save(adm);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}")
    public String busca(@PathVariable int id, Model model){
        Optional<Adm> admin = repo.findById(id);
        try{
            model.addAttribute("admin", admin.get());
        } catch(Exception e) {
            return "redirect:/admin";
        }
        return "/admin/editar";
    }

    @PostMapping("/admin/{id}/atualizar")
    public String atualizar(@PathVariable int id, Adm admin){
        if (!repo.existsById(id)) {  
            return "redirect:/admin";
        }
        
        repo.save(admin);

        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/excluir")
    public String excluir(@PathVariable int id){
        repo.deleteById(id);
        return "redirect:/admin";
    }
}
