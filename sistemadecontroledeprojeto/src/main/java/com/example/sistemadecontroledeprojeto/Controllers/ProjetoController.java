package com.example.sistemadecontroledeprojeto.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistemadecontroledeprojeto.Services.ProjetoService;
import com.example.sistemadecontroledeprojeto.dtos.ProjetoDTO;
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
    @GetMapping("/{id}")
    public ProjetoDTO buscarProjetoPorId(@PathVariable Integer id) {
        return projetoService.obterPorId(id);
    }   

}
