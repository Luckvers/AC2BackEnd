package com.example.sistemadecontroledeprojeto.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.sistemadecontroledeprojeto.Models.Projeto;
import java.time.LocalDate;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
    @Query("SELECT p FROM Projeto p JOIN p.funcionario f WHERE f.id = :idFuncionario")
    List<Projeto> findByFuncionarioId(Integer idFuncionario);
    
    @Query("SELECT p FROM Projeto p WHERE p.dataInicio BETWEEN :dataInicio AND :dataFim")
    List<Projeto> findByDataInicioAndDataFim (LocalDate dataInicio, LocalDate dataFim);
} 

