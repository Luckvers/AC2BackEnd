package com.example.sistemadecontroledeprojeto.dtos;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuncionarioDTO {
    private Integer id;
    private String nome;
    private List<ProjetoDTO> projetos;
    private SetorDTO setor;
    
}
