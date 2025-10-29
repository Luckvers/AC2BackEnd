package com.example.sistemadecontroledeprojeto.Services;

import java.util.List;

import com.example.sistemadecontroledeprojeto.dtos.ProjetoDTO;
import com.example.sistemadecontroledeprojeto.dtos.ProjetoRequestDTO;

public interface ProjetoService {
    void salvar(ProjetoRequestDTO projetoRequestDTO);

    ProjetoDTO obterPorId(Integer id);

    List<ProjetoDTO> obterTodos();

    void vincularFuncionario(Integer idProjeto, Integer idFuncionario);
}
