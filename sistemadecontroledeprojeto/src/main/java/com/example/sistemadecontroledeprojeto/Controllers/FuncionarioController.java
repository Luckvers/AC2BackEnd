package com.example.sistemadecontroledeprojeto.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemadecontroledeprojeto.Services.FuncionarioService;
import com.example.sistemadecontroledeprojeto.dtos.FuncionarioRequestDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
    
    @PostMapping
    public void criarFuncionario(@RequestBody FuncionarioRequestDTO funcionarioRequestDTO) {
        funcionarioService.salvar(funcionarioRequestDTO);
        
    }
    
}
