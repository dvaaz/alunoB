package com.senac.alunoB.external.client;

import com.senac.alunoB.entity.dto.usuario.UsuarioDTORequest;
import com.senac.alunoB.entity.dto.usuario.UsuarioDtoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="viacep", url="172.20.208.1", path = "/api/usuario")
public interface UsuarioFeignClient {
    @PostMapping("/cadastrarFuncionario")
    UsuarioDtoResponse create(@RequestBody UsuarioDTORequest usuarioRequest);

    @GetMapping("/listar/{id}")
    UsuarioDtoResponse findById(@Param("id") Integer id);

    @GetMapping("/listar")
    List<UsuarioDtoResponse> list();
}
