package com.example.sistemadecontroledeprojeto.Services;

import java.util.List;

import com.example.sistemadecontroledeprojeto.dtos.SetorDTO;
import com.example.sistemadecontroledeprojeto.dtos.SetorRequestDTO;

public interface SetorService {
    void salvar(SetorRequestDTO setorRequestDTO);

    void remover(Integer id);

    void editar(Integer id, SetorRequestDTO dto);

    SetorDTO obterPorId(Integer id);

    List<SetorDTO> obterTodos();

}
