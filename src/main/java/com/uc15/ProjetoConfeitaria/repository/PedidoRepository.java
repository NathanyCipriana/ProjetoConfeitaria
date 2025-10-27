
package com.uc15.ProjetoConfeitaria.repository;

import com.uc15.ProjetoConfeitaria.model.Cliente;
import com.uc15.ProjetoConfeitaria.model.Pedido;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
