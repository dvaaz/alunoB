package com.senac.alunoB.controller;

import com.senac.alunoB.entity.dto.pedido.PedidoDTORequest;
import com.senac.alunoB.entity.dto.pedido.PedidoDTOResponse;
import com.senac.alunoB.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunoB/produto")
public class PedidoController {
    private final PedidoService service;
    @Autowired
    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping("/criar")
    @CrossOrigin(origins="*")
    public ResponseEntity<PedidoDTOResponse> criar(@RequestBody PedidoDTORequest pedidoDTORequest){
        PedidoDTOResponse dtoResponse = service.create(pedidoDTORequest);
        return  ResponseEntity.ok(dtoResponse);
    }

    @GetMapping("/listar/{id}")
    @CrossOrigin(origins="*")
    public ResponseEntity<List<PedidoDTOResponse>> listar(@PathVariable Integer id){
        List<PedidoDTOResponse> dtoResponse = service.listByUser(id);
        if(dtoResponse.isEmpty()){
            return ResponseEntity.notFound().build();
        } else return  ResponseEntity.ok(dtoResponse);
    }

    @GetMapping("/listar")
    @CrossOrigin(origins="*")
    public ResponseEntity<List<PedidoDTOResponse>> listar(){
        List<PedidoDTOResponse> dtoResponse = service.listAll();
        if(dtoResponse.isEmpty()){
            return ResponseEntity.notFound().build();
        } else return  ResponseEntity.ok(dtoResponse);

    }
}
