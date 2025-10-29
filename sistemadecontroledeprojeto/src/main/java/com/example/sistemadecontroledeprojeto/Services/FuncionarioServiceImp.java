package com.example.sistemadecontroledeprojeto.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.sistemadecontroledeprojeto.Models.Funcionario;
import com.example.sistemadecontroledeprojeto.Models.Projeto;
import com.example.sistemadecontroledeprojeto.Models.Setor;
import com.example.sistemadecontroledeprojeto.Repositories.FuncionarioRepository;
import com.example.sistemadecontroledeprojeto.Repositories.ProjetoRepository;
import com.example.sistemadecontroledeprojeto.Repositories.SetorRepository;
import com.example.sistemadecontroledeprojeto.dtos.FuncionarioRequestDTO;
import com.example.sistemadecontroledeprojeto.dtos.ProjetoDTO;
import com.example.sistemadecontroledeprojeto.dtos.RegraNegocioException;

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
        public List<ProjetoDTO> buscarProjetosPorFuncionario(Integer idFuncionario) {
                Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado"));

                return funcionario.getProjeto().stream()
                                .map(proj -> ProjetoDTO.builder()
                                                .id(proj.getId())
                                                .descricao(proj.getDescricao())
                                                .dataInicio(proj.getDataInicio())
                                                .dataFim(proj.getDataFim())
                                                .build())
                                .collect(Collectors.toList());
        }

}
