package com.senac.alunoB.service;

import com.senac.alunoB.entity.Pedido;
import com.senac.alunoB.entity.dto.UsuarioPedidoDTO;
import com.senac.alunoB.entity.dto.pedido.PedidoDTORequest;
import com.senac.alunoB.entity.dto.pedido.PedidoDTOResponse;
import com.senac.alunoB.entity.dto.pedido.PedidosDeUsuarioDTO;
import com.senac.alunoB.entity.dto.usuario.UsuarioDTORequest;
import com.senac.alunoB.entity.dto.usuario.UsuarioDtoResponse;
import com.senac.alunoB.repository.client.UsuarioClient;
import com.senac.alunoB.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PedidoService {
    private final PedidoRepository repository;
		private final UsuarioClient usuarioClient;
    private final UsuarioService usuarioService;

    @Autowired
  public PedidoService(PedidoRepository repository, UsuarioClient usuarioClient, UsuarioService usuarioService) {
    this.repository = repository;
    this.usuarioClient = usuarioClient;
    this.usuarioService = usuarioService;
  }




    @Transactional
    public PedidoDTOResponse create(PedidoDTORequest dtoRequest){
		    UsuarioDtoResponse usuario = usuarioClient.findById(dtoRequest.usuarioId())
				    .orElseThrow(() -> new IllegalArgumentException("Usuário ID " + dtoRequest.usuarioId() + " não encontrado."));

        Pedido pedido = new Pedido();
        pedido.setData(dtoRequest.data());
        pedido.setValorTotal(dtoRequest.valorTotal());
        pedido.setStatus(1);
        pedido.setUsuarioId(usuario.getId());

        Pedido pedidoSalvo = repository.save(pedido);

        PedidoDTOResponse pedidoDTOResponse = new PedidoDTOResponse();
        pedidoDTOResponse.setId(pedidoSalvo.getId());
        pedidoDTOResponse.setData(pedidoSalvo.getData());
        pedidoDTOResponse.setValorTotal(pedidoSalvo.getValorTotal());

        return pedidoDTOResponse;
		}

    public List<Pedido> findByUser(Integer usuarioId){
      return repository.findByUsuario(usuarioId);
    }

    public PedidosDeUsuarioDTO listByUser(Integer usuarioId){
				UsuarioDtoResponse usuario = usuarioClient.findById(usuarioId)
						.orElseThrow(() -> new IllegalArgumentException("Usuário ID " + usuarioId + " não encontrado."));

						List<Pedido> pedidoUsuario = repository.findByUsuario(usuarioId);
						if (!pedidoUsuario.isEmpty()) {
								List<PedidoDTOResponse> dtoPedidoList = new ArrayList<>();
								for (Pedido pedido : pedidoUsuario) {
										PedidoDTOResponse item = new PedidoDTOResponse();
										item.setId(pedido.getId());
										item.setData(pedido.getData());
										item.setValorTotal(pedido.getValorTotal());
										item.setUsuario(pedido.getUsuarioId());
										dtoPedidoList.add(item);
								}
								// Para fins de exercicio apenas criei um DTO com os dados do usuario e a lista de pedidos
								return new PedidosDeUsuarioDTO(usuario, dtoPedidoList);
						}
						else return null;
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

    public Pedido findById (Integer id){
      return repository.findById(id)
          .orElseThrow(() -> new RuntimeException("Id não encontrada"));
    }


    public PedidoDTOResponse createWithoutUserVerificarion(Pedido dtoRequest, Integer userId){
      Pedido pedido = new Pedido();
      pedido.setData(dtoRequest.getData());
      pedido.setValorTotal(dtoRequest.getValorTotal());
      pedido.setStatus(1);
      pedido.setUsuarioId(userId);

      Pedido pedidoSalvo = repository.save(pedido);

      PedidoDTOResponse pedidoDTOResponse = new PedidoDTOResponse();
      pedidoDTOResponse.setId(pedidoSalvo.getId());
      pedidoDTOResponse.setData(pedidoSalvo.getData());
      pedidoDTOResponse.setValorTotal(pedidoSalvo.getValorTotal());

      return pedidoDTOResponse;
    }

    @Transactional
    public UsuarioDtoResponse pedidoDeUsuarioNaoCadastrado(UsuarioPedidoDTO usuarioPedidoDTO){
      // cria novo usuario

      UsuarioDtoResponse usuarioDTO = new UsuarioDtoResponse();
      usuarioDTO.setNome(usuarioPedidoDTO.nomeUsuario());
      usuarioDTO.setCpf(usuarioPedidoDTO.cpfUsuario());

      UsuarioDtoResponse usuarioSave = usuarioService.create(usuarioDTO);

      // em caso de problema na conexao do banco
      if(usuarioSave == null) {
        throw new RuntimeException("Não foi possivel criar novo usuário");
      }
      // obtem id do usuario
      Integer usuarioId = usuarioSave.getId();
      // cria pedido utilizando o id do usuario recem criado
      Pedido pedido = new Pedido();
      pedido.setData(usuarioPedidoDTO.dataPedido());
      pedido.setUsuarioId(usuarioSave.getId());
      pedido.setValorTotal(usuarioPedidoDTO.valorTotalPedido());
      PedidoDTOResponse pedidoNovo =  this.createWithoutUserVerificarion(pedido, usuarioId);

      return usuarioSave;
    }

}
