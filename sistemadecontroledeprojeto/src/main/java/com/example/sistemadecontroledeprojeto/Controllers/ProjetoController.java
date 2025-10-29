package com.example.sistemadecontroledeprojeto.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping
    public List<ProjetoDTO> obterTodos() {
        return projetoService.obterTodos();
    }

    @PostMapping("/{idProjeto}/funcionarios/{idFuncionario}")
    public void vincularFuncionario(@PathVariable Integer idProjeto, @PathVariable Integer idFuncionario) {
        projetoService.vincularFuncionario(idProjeto, idFuncionario);
    }

}
