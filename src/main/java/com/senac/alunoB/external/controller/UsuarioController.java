package com.senac.alunoB.external.controller;

import com.senac.alunoB.entity.dto.usuario.UsuarioDTORequest;
import com.senac.alunoB.entity.dto.usuario.UsuarioDtoResponse;
import com.senac.alunoB.external.client.UsuarioFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final UsuarioFeignClient client;
    @Autowired
    public UsuarioController(UsuarioFeignClient client) {
        this.client = client;
    }

    @PostMapping("/cadastrarFuncionario")
    public ResponseEntity<UsuarioDtoResponse> cadastrarFuncionario(@RequestBody UsuarioDTORequest dtoRequest) {
        UsuarioDTORequest novoUsuario = usuarioService.criarUsuario(dtoRequest)
        if (novoUsuario != null) {
            ResponseEntity.noContent().build();
        } else ResponseEntity.ok(novoUsuario);
    }
}
