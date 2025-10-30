package com.senac.alunoB.controller;


import com.senac.alunoB.entity.dto.usuario.UsuarioDtoResponse;
import com.senac.alunoB.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
  private final UsuarioService service;
  @Autowired
  public UsuarioController(UsuarioService service) {
    this.service = service;
  }

  @GetMapping("/listar")
  public ResponseEntity<List<UsuarioDtoResponse>> list()

  {
    return ResponseEntity.ok(service.list());
  }

  @GetMapping("/buscar/{id}")
  public ResponseEntity<UsuarioDtoResponse> listByUser(
      @PathVariable("id") Integer id){
    UsuarioDtoResponse dtoResponse = service.findById(id);
    return  ResponseEntity.ok(dtoResponse);
    }

}

