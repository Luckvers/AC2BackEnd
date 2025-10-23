package com.example.sistemadecontroledeprojeto.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.sistemadecontroledeprojeto.Models.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    @Query("SELECT f FROM Funcionario f JOIN f.projeto p WHERE p.id = :idProjeto")
    List<Funcionario> findByIdProjeto(Integer idProjeto);

}
