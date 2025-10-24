package com.senac.alunoB.repository;

import com.senac.alunoB.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Query("SELECT p FROM Pedido p WHERE p.usuarioId = :usuarioId")
    public List<Pedido>  findByUsuario(@Param("usuarioId") Integer usuarioId);

    @Query("SELECT p FROM Pedido p WHERE p.id = :id")
    public Optional<Pedido> findById(@Param("id") int id);
}
