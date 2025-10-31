package com.senac.alunoB.entity.dto;

import jakarta.persistence.Column;

import java.util.Date;

public record UsuarioPedidoDTO (
   Date dataPedido,
   Double valorTotalPedido,
   Integer statusPedido,
   String nomeUsuario,
   String cpfUsuario,
   Integer statusUsuario
){}
