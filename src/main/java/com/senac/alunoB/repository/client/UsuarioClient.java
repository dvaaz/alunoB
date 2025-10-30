package com.senac.alunoB.repository.client;

import com.senac.alunoB.entity.dto.usuario.UsuarioDTORequest;
import com.senac.alunoB.entity.dto.usuario.UsuarioDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name="UsuarioProva", url="10.136.36.217:8080", path = "/api/usuario")
public interface UsuarioClient {
    @PostMapping("/cadastrarFuncionario")
    Optional<UsuarioDtoResponse> create(@RequestBody UsuarioDTORequest usuarioRequest);

    @GetMapping("/buscar/{id}")
    Optional<UsuarioDtoResponse> findById(@Param("id") Integer id);

    @GetMapping("/listar")
    List<UsuarioDtoResponse> list();
}
