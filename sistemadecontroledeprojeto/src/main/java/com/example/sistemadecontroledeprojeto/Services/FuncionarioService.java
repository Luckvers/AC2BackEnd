package com.example.sistemadecontroledeprojeto.Services;

import java.util.List;

import com.example.sistemadecontroledeprojeto.dtos.FuncionarioDTO;
import com.example.sistemadecontroledeprojeto.dtos.FuncionarioRequestDTO;

public interface FuncionarioService {

    void salvar(FuncionarioRequestDTO funcionarioRequestDTO);

    void remover(Integer id);

    void editar(Integer id, FuncionarioRequestDTO dto);

    FuncionarioDTO obterPorId(Integer id);

    List<FuncionarioDTO> obterTodos();
}
