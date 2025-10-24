package com.senac.alunoB.entity.dto.pedido;





import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record PedidoDTORequest(
    Date data,
    @NotNull @Min(0) Double valorTotal,
    @NotNull @Min(1) Integer usuarioId
) {}

