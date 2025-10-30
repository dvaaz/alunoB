package com.senac.alunoB.controller;

import com.senac.alunoB.entity.dto.pedido.PedidosDeUsuarioDTO;
import com.senac.alunoB.entity.dto.usuario.UsuarioDtoResponse;
import com.senac.alunoB.external.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
  private final UsuarioClient usuarioClient;
  @Autowired
  public UsuarioController(UsuarioClient usuarioClient) {
    this.usuarioClient = usuarioClient;
  }

  @GetMapping("/listar")
  public ResponseEntity<List<UsuarioDtoResponse>> listar()

  {
    return ResponseEntity.ok(usuarioClient.list());
  }

  @GetMapping("/buscar/{id}")
  public ResponseEntity<Optional<UsuarioDtoResponse>> listByUser(
      @PathVariable("id") Integer id){
    Optional<UsuarioDtoResponse> dtoResponse = usuarioClient.findById(id);
    if(dtoResponse.isEmpty()){
      return ResponseEntity.notFound().build();
    } else  {
      return ResponseEntity.ok(dtoResponse);
    }
  }
}
