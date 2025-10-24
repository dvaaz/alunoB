package com.senac.alunoB.entity.dto.pedido;





import java.util.Date;

public record PedidoDTORequest(
    Date data,
    Double valorTotal,
    Integer usuarioId
) {}

