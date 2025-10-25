package com.senac.alunoB.entity.dto.pedido;

import com.senac.alunoB.entity.dto.usuario.UsuarioDtoResponse;

import java.util.List;

public record PedidosDeUsuarioDTO (
		UsuarioDtoResponse usuarioDto,
		List<PedidoDTOResponse> pedidoList
		){}
