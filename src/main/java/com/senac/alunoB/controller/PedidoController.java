package com.senac.alunoB.controller;

import com.senac.alunoB.entity.Pedido;
import com.senac.alunoB.entity.dto.pedido.PedidoDTORequest;
import com.senac.alunoB.entity.dto.pedido.PedidoDTOResponse;
import com.senac.alunoB.entity.dto.pedido.PedidosDeUsuarioDTO;
import com.senac.alunoB.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunoB/produto")
@CrossOrigin(origins="*")
public class PedidoController {
    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criar(@Valid @RequestBody PedidoDTORequest pedidoDTORequest){
        PedidoDTOResponse dtoResponse = service.create(pedidoDTORequest);
        if(dtoResponse == null){
            return ResponseEntity.badRequest()
                    .body("Não foi possível criar o pedido. Verifique os dados.");
        }
        return  ResponseEntity.ok(dtoResponse);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Pedido> findById(
        @PathVariable Integer id){
		    Pedido dtoResponse = service.findById(id);
        if(dtoResponse == null ){
            return ResponseEntity.notFound().build();
        } else return  ResponseEntity.ok(dtoResponse);
    }

    @GetMapping("/buscar/usuario/{id}")
    public ResponseEntity<PedidosDeUsuarioDTO> listByUser(@PathVariable Integer id){
        PedidosDeUsuarioDTO dtoResponse = service.listByUser(id);
        if(dtoResponse == null){
            return ResponseEntity.notFound().build();
        } else return  ResponseEntity.ok(dtoResponse);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PedidoDTOResponse>> listAll(){
        List<PedidoDTOResponse> dtoResponse = service.listAll();
        if(dtoResponse.isEmpty()){
            return ResponseEntity.notFound().build();
        } else return  ResponseEntity.ok(dtoResponse);

    }
}
