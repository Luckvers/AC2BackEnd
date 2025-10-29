package com.example.sistemadecontroledeprojeto.Services;


import org.springframework.stereotype.Service;

import com.example.sistemadecontroledeprojeto.Models.Setor;
import com.example.sistemadecontroledeprojeto.Repositories.SetorRepository;
import com.example.sistemadecontroledeprojeto.dtos.RegraNegocioException;
import com.example.sistemadecontroledeprojeto.dtos.SetorDTO;
import com.example.sistemadecontroledeprojeto.dtos.SetorRequestDTO;

@Service
public class SetorServiceImp implements SetorService {
private SetorRepository setorRepository;

    public SetorServiceImp(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }

    @Override
    public void salvar(SetorRequestDTO setorRequestDTO) {
        Setor setor = new Setor();
        setor.setNome(setorRequestDTO.getNome());
        setorRepository.save(setor);
    }

    @Override
    public SetorDTO obterPorId(Integer id) {
        return setorRepository.findById(id)
            .map(setor -> SetorDTO.builder()
                .id(setor.getId())
                .nome(setor.getNome())
                .build())
            .orElseThrow(() -> new RegraNegocioException("Setor não encontrado"));
    }
}
