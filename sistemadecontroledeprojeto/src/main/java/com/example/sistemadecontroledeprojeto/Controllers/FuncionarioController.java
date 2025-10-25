package com.example.sistemadecontroledeprojeto.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemadecontroledeprojeto.Services.FuncionarioService;
import com.example.sistemadecontroledeprojeto.dtos.FuncionarioDTO;
import com.example.sistemadecontroledeprojeto.dtos.FuncionarioRequestDTO;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public FuncionarioDTO buscarFuncionarioPorId(@PathVariable Integer id) {
        return funcionarioService.obterPorId(id);
    }

    

}
