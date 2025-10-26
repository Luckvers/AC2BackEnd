package com.example.sistemadecontroledeprojeto.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.sistemadecontroledeprojeto.Models.Funcionario;
import com.example.sistemadecontroledeprojeto.Models.Projeto;
import com.example.sistemadecontroledeprojeto.Repositories.FuncionarioRepository;
import com.example.sistemadecontroledeprojeto.Repositories.ProjetoRepository;
import com.example.sistemadecontroledeprojeto.dtos.FuncionarioDTO;
import com.example.sistemadecontroledeprojeto.dtos.ProjetoDTO;
import com.example.sistemadecontroledeprojeto.dtos.ProjetoRequestDTO;
import com.example.sistemadecontroledeprojeto.dtos.RegraNegocioException;

@Service
public class ProjetoServiceImp implements ProjetoService {
        private FuncionarioRepository funcionarioRepository;
        private ProjetoRepository projetoRepository;

        public ProjetoServiceImp(FuncionarioRepository funcionarioRepository, ProjetoRepository projetoRepository) {
                this.funcionarioRepository = funcionarioRepository;
                this.projetoRepository = projetoRepository;
        }

        @Override
        public void salvar(ProjetoRequestDTO projetoRequestDTO) {

                List<Funcionario> funcionarios = funcionarioRepository
                                .findAllById(projetoRequestDTO.getIdsFuncionarios());

                Projeto projeto = new Projeto();
                projeto.setDescricao(projetoRequestDTO.getDescricao());
                projeto.setDataInicio(projetoRequestDTO.getDataInicio());
                projeto.setDataFim(projetoRequestDTO.getDataFim());
                projeto.setFuncionario(funcionarios);

                projetoRepository.save(projeto);
        }

        @Override
        public ProjetoDTO obterPorId(Integer id) {
                return projetoRepository.findById(id)
                                .map(projeto -> ProjetoDTO.builder()
                                                .id(projeto.getId())
                                                .descricao(projeto.getDescricao())
                                                .dataInicio(projeto.getDataInicio())
                                                .dataFim(projeto.getDataFim())
                                                .funcionarios(projeto.getFuncionario().stream()
                                                                .map(func -> FuncionarioDTO.builder()
                                                                                .id(func.getId())
                                                                                .nome(func.getNome())
                                                                                .build())
                                                                .toList())
                                                .build())
                                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));
        }

        @Override
        public void remover(Integer id) {
                projetoRepository.deleteById(id);
        }

        @Override
        public void editar(Integer id, ProjetoRequestDTO projetoRequestDTO) {
                Projeto projeto = projetoRepository.findById(id)
                                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));

                List<Funcionario> funcionarios = funcionarioRepository
                                .findAllById(projetoRequestDTO.getIdsFuncionarios());

                projeto.setDescricao(projetoRequestDTO.getDescricao());
                projeto.setDataInicio(projetoRequestDTO.getDataInicio());
                projeto.setDataFim(projetoRequestDTO.getDataFim());
                projeto.setFuncionario(funcionarios);

                projetoRepository.save(projeto);
        }

        @Override
        public List<ProjetoDTO> obterTodos() {
                return projetoRepository.findAll().stream()
                                .map(p -> ProjetoDTO.builder()
                                                .id(p.getId())
                                                .descricao(p.getDescricao())
                                                .dataInicio(p.getDataInicio())
                                                .dataFim(p.getDataFim())
                                                .funcionarios(p.getFuncionario().stream()
                                                                .map(f -> FuncionarioDTO.builder()
                                                                                .id(f.getId())
                                                                                .nome(f.getNome())
                                                                                .build())
                                                                .toList())
                                                .build())
                                .collect(Collectors.toList());
        }

        @Override
        public void vincularFuncionario(Integer idProjeto, Integer idFuncionario) {
                Projeto projeto = projetoRepository.findById(idProjeto)
                                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));
                Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                                .orElseThrow(() -> new RegraNegocioException("Funcionário não encontrado"));
                projeto.getFuncionario().add(funcionario);
                projetoRepository.save(projeto);
        }
}
