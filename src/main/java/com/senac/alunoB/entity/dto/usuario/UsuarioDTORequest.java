package com.senac.alunoB.entity.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTORequest (
        @NotBlank String nome,

        @NotBlank String cpf,

        Integer status
){
}
