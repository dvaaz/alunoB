package com.senac.alunoB.service;

import com.senac.alunoB.entity.Pedido;
import com.senac.alunoB.entity.dto.pedido.PedidoDTORequest;
import com.senac.alunoB.entity.dto.pedido.PedidoDTOResponse;
import com.senac.alunoB.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PedidoService {
    private PedidoRepository repository;
    @Autowired
    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public PedidoDTOResponse create(PedidoDTORequest dtoRequest){
        Pedido pedido = new Pedido();
        pedido.setData(dtoRequest.data());
        pedido.setValorTotal(dtoRequest.valorTotal());
        pedido.setStatus(1);
        pedido.setUsuarioId(dtoRequest.usuarioId());

        Pedido pedidoSalvo = repository.save(pedido);

        PedidoDTOResponse pedidoDTOResponse = new PedidoDTOResponse();
        pedidoDTOResponse.setId(pedido.getId());
        pedidoDTOResponse.setData(pedido.getData());
        pedidoDTOResponse.setValorTotal(pedido.getValorTotal());

        return pedidoDTOResponse;
    }

    public List<PedidoDTOResponse> listByUser(Integer usuarioId){
        List<Pedido> pedidoUsuario = repository.findByUsuario(usuarioId);
        if(!pedidoUsuario.isEmpty()) {
            List<PedidoDTOResponse> dtoResponse = new ArrayList<>();
            for (Pedido pedido : pedidoUsuario) {
                PedidoDTOResponse item = new PedidoDTOResponse();
                item.setId(pedido.getId());
                item.setData(pedido.getData());
                item.setValorTotal(pedido.getValorTotal());
                item.setUsuario(pedido.getUsuarioId());
                dtoResponse.add(item);
            }
            return dtoResponse;
        }
        return null;
    }

    public List<PedidoDTOResponse> listAll(){
        List<Pedido> pedido = repository.findAll();
        List<PedidoDTOResponse> dtoResponse = new ArrayList<>();
        if(!pedido.isEmpty()) {
            for (Pedido pedido1 : pedido) {
                PedidoDTOResponse item = new PedidoDTOResponse();
                item.setId(pedido1.getId());
                item.setData(pedido1.getData());
                item.setValorTotal(pedido1.getValorTotal());
                item.setUsuario(pedido1.getUsuarioId());
                dtoResponse.add(item);
            }
            return dtoResponse;
        } return  null;
    }
}
