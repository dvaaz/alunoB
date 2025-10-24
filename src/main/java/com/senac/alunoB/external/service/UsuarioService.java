package com.senac.alunoB.external.service;

import com.senac.alunoB.external.client.UsuarioFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UsuarioService {
    private final UsuarioFeignClient client;
    @Autowired

    public UsuarioService(UsuarioFeignClient client) {
        this.client = client;
    }

    @PostMapping
}
