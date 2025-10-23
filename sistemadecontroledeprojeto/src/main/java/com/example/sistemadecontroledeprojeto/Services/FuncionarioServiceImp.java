package com.example.sistemadecontroledeprojeto.Services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.sistemadecontroledeprojeto.Models.Funcionario;
import com.example.sistemadecontroledeprojeto.Models.Projeto;
import com.example.sistemadecontroledeprojeto.Models.Setor;
import com.example.sistemadecontroledeprojeto.Repositories.FuncionarioRepository;
import com.example.sistemadecontroledeprojeto.Repositories.ProjetoRepository;
import com.example.sistemadecontroledeprojeto.Repositories.SetorRepository;
import com.example.sistemadecontroledeprojeto.dtos.FuncionarioDTO;
import com.example.sistemadecontroledeprojeto.dtos.FuncionarioRequestDTO;
import com.example.sistemadecontroledeprojeto.dtos.ProjetoDTO;
import com.example.sistemadecontroledeprojeto.dtos.RegraNegocioException;
import com.example.sistemadecontroledeprojeto.dtos.SetorDTO;

@Service
public class FuncionarioServiceImp implements FuncionarioService {
    private FuncionarioRepository funcionarioRepository;
    private ProjetoRepository projetoRepository;
    private SetorRepository setorRepository;

    public FuncionarioServiceImp(FuncionarioRepository funcionarioRepository, ProjetoRepository projetoRepository,
            SetorRepository setorRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.projetoRepository = projetoRepository;
        this.setorRepository = setorRepository;
    }

    @Override
    public void salvar(FuncionarioRequestDTO funcionarioRequestDTO) {
        Setor setor = setorRepository.findById(funcionarioRequestDTO.getIdSetor())
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado"));

        List<Projeto> projetos = projetoRepository.findAllById(funcionarioRequestDTO.getIdsProjetos());

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioRequestDTO.getNome());
        funcionario.setSetor(setor);
        funcionario.setProjeto(projetos);
        funcionarioRepository.save(funcionario);
    }

    @Override
    public FuncionarioDTO obterPorId(Integer id) {
        return funcionarioRepository.findById(id).map(funcionario -> FuncionarioDTO.builder()
                .id(funcionario.getId())
                .nome(funcionario.getNome())
                .setor(SetorDTO.builder()
                        .id(funcionario.getSetor().getId())
                        .nome(funcionario.getSetor().getNome())
                        .build())
                .projetos(funcionario.getProjeto().stream()
                        .map(proj -> ProjetoDTO.builder()
                                .id(proj.getId())
                                .descricao(proj.getDescricao())
                                .dataInicio(proj.getDataInicio())
                                .dataFim(proj.getDataFim())
                                .build())
                        .toList())
                .build())
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado"));
    }

    @Override
    public void remover(Integer id) {
        funcionarioRepository.deleteById(id);
    }

    @Override
    public void editar(Integer id, FuncionarioRequestDTO funcionarioRequestDTO) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado"));
        Setor setor = setorRepository.findById(funcionarioRequestDTO.getIdSetor())
                .orElseThrow(() -> new RegraNegocioException("Setor não encontrado"));

        List<Projeto> projetos = projetoRepository.findAllById(funcionarioRequestDTO.getIdsProjetos());

        funcionario.setNome(funcionarioRequestDTO.getNome());
        funcionario.setSetor(setor);
        funcionario.setProjeto(projetos);
        funcionarioRepository.save(funcionario);
    }

    @Override
    public List<FuncionarioDTO> obterTodos(){
        return funcionarioRepository.findAll().stream().map((Funcionario f) -> {
            return FuncionarioDTO.builder()
            .id(f.getId())
                .nome(f.getNome())
                .setor(SetorDTO.builder()
                        .id(f.getSetor().getId())
                        .nome(f.getSetor().getNome())
                        .build())
                .projetos(f.getProjeto().stream()
                        .map(proj -> ProjetoDTO.builder()
                                .id(proj.getId())
                                .descricao(proj.getDescricao())
                                .dataInicio(proj.getDataInicio())
                                .dataFim(proj.getDataFim())
                                .build())
                        .toList())
                .build();
        }).collect(Collectors.toList());
    }
}
