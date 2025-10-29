package com.example.sistemadecontroledeprojeto.Services;

import com.example.sistemadecontroledeprojeto.dtos.SetorDTO;
import com.example.sistemadecontroledeprojeto.dtos.SetorRequestDTO;

public interface SetorService {
    void salvar(SetorRequestDTO setorRequestDTO);

    SetorDTO obterPorId(Integer id);

}
