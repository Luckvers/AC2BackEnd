package com.example.sistemadecontroledeprojeto.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemadecontroledeprojeto.Services.SetorService;
import com.example.sistemadecontroledeprojeto.dtos.SetorRequestDTO;

@RestController
@RequestMapping("/setores")
public class SetorController {
    private SetorService setorService;

    public SetorController(SetorService setorService) {
        this.setorService = setorService;
    }

     @PostMapping
    public void adicionar(@RequestBody SetorRequestDTO setorRequestDTO) {
        setorService.salvar(setorRequestDTO);
        
    }
}
