
package com.uc15.ProjetoConfeitaria.service;

import com.uc15.ProjetoConfeitaria.model.Cliente;
import com.uc15.ProjetoConfeitaria.repository.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente salvarCliente(Cliente cliente) {
        cliente.setId(null);
        clienteRepository.save(cliente);
            return cliente;
    }
    
    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }
    
    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id).orElseThrow();
    }
    
 
    public void excluirCliente(Integer id) {
        Cliente encontrado= buscarPorId(id);
        clienteRepository.deleteById(encontrado.getId());
    }
    
    
}
