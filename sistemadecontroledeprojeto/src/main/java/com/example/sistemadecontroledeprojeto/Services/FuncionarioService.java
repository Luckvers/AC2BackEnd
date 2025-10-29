package com.example.sistemadecontroledeprojeto.Services;

import java.util.List;

import com.example.sistemadecontroledeprojeto.dtos.FuncionarioRequestDTO;
import com.example.sistemadecontroledeprojeto.dtos.ProjetoDTO;

public interface FuncionarioService {

    void salvar(FuncionarioRequestDTO funcionarioRequestDTO);

    List<ProjetoDTO> buscarProjetosPorFuncionario(Integer idFuncionario);

}
