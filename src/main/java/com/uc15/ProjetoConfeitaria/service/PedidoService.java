
package com.uc15.ProjetoConfeitaria.service;

import com.uc15.ProjetoConfeitaria.model.Cliente;
import com.uc15.ProjetoConfeitaria.model.Pedido;
import com.uc15.ProjetoConfeitaria.repository.PedidoRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    
    @Autowired
    private PedidoRepository pedidoRepository;
    
    
    public Pedido salvarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    
    public List<Pedido> listarTodosPedidos() {
        return pedidoRepository.findAll();
    }
    
    
    public Pedido buscarPorId(Integer id) {
       return pedidoRepository.findById(id).orElseThrow();
    }
 
    public void excluirPedido(Integer id) {
        Pedido encontrado = buscarPorId(id);
        pedidoRepository.deleteById(encontrado.getId());
    }
    
    public List<Pedido> buscarPorCliente(Cliente cliente) {
        return pedidoRepository.findByCliente(cliente);
    }
}
