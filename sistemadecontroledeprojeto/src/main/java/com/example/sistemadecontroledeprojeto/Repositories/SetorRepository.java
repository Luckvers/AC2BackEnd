package com.example.sistemadecontroledeprojeto.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.sistemadecontroledeprojeto.Models.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {
    @Query("SELECT s FROM Setor s JOIN s.funcionarios f WHERE f.id = :idFuncionario")
    List<Setor> findByIdFuncionario(Integer idFuncionario);

}
