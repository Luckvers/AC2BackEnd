package com.example.sistemadecontroledeprojeto.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemadecontroledeprojeto.Services.ProjetoService;
import com.example.sistemadecontroledeprojeto.dtos.ProjetoRequestDTO;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {
    private ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    public void adicionar(@RequestBody ProjetoRequestDTO projetoRequestDTO) {
        projetoService.salvar(projetoRequestDTO);
        
    }
}
