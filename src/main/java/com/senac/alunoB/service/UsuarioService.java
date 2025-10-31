package com.senac.alunoB.service;

import com.senac.alunoB.entity.dto.usuario.UsuarioDTORequest;
import com.senac.alunoB.entity.dto.usuario.UsuarioDtoResponse;
import com.senac.alunoB.repository.client.UsuarioClient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioService {
  private final UsuarioClient usuarioClient;
  @Autowired
  public UsuarioService(UsuarioClient usuarioClient) {
    this.usuarioClient = usuarioClient;
  }

  public UsuarioDtoResponse findById(Integer id){
    UsuarioDtoResponse dtoResponse= usuarioClient.findById(id)
        .orElseThrow(() -> new NoSuchElementException("Elemento não encontrado"));
    return dtoResponse;
  }

  public List<UsuarioDtoResponse> list() {
    List<UsuarioDtoResponse> dtoResponses = usuarioClient.list();
    if(dtoResponses.isEmpty()){
      return (List<UsuarioDtoResponse>) new NoSuchElementException("Lista vazia");
    } else return dtoResponses;
  }


  public UsuarioDtoResponse create(UsuarioDtoResponse dtoRequest) {
    UsuarioDtoResponse usuario = new UsuarioDtoResponse();
    usuario.setCpf(dtoRequest.getCpf());
    usuario.setNome(dtoRequest.getNome());

    Optional<UsuarioDtoResponse> save = usuarioClient.create(usuario);
    if (save.isPresent()) {
      usuario.setId(save.get().getId());
      usuario.setStatus(save.get().getStatus());

      return usuario;
    }
    return null;
  }

}
