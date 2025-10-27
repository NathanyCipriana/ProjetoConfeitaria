
package com.uc15.ProjetoConfeitaria.controller;

import org.springframework.ui.Model;
import com.uc15.ProjetoConfeitaria.model.Cliente;
import com.uc15.ProjetoConfeitaria.model.Pedido;
import com.uc15.ProjetoConfeitaria.service.ClienteService;
import com.uc15.ProjetoConfeitaria.service.PedidoService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfeitariaController {
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/")
    public String home() {
        return "login";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @PostMapping("/login")
    public String fazerLogin(@RequestParam String email, @RequestParam String senha, HttpSession session) {                  
        // Implementar validação
        if (email != null && !email.isEmpty()) {
            session.setAttribute("usuarioLogado", email);
            return "redirect:/principal";
        }
        return "redirect:/login?erro=true";
    }
    
    @GetMapping("/principal")
    public String principal(Model model) {
        return "principal";
    }
    
    @GetMapping("/cadastro-cliente")
    public String cadastrarCliente(Model model) {
        model.addAttribute("clientes", new Cliente());
        return "cadastro-cliente";
    }
    
    @PostMapping("/cadastrar")
    public String salvarCliente(@ModelAttribute Cliente cliente, Model model) {
        clienteService.salvarCliente(cliente);
        return "redirect:/principal";
    }
    
    @GetMapping("/cadastro-pedido")
    public String cadastroPedido(Model model) {
        
        model.addAttribute("pedido", new Pedido());
        List<Cliente> clientes = clienteService.listarTodosClientes();
        model.addAttribute("clientes", clientes);
        
        return "cadastro-pedido";
    }
    
    @PostMapping("/salvar-pedido")
    public String salvarPedido(@ModelAttribute Pedido pedido,@RequestParam("idcliente") Integer idcliente, Model model) {
                             
         Cliente cliente = clienteService.buscarPorId(idcliente);
         pedido.setCliente(cliente);
         pedidoService.salvarPedido(pedido);
        return "redirect:/listagem";
    }
    
    @GetMapping("/listagem")
    public String listagem(Model model) {
       List<Cliente> clientes = clienteService.listarTodosClientes();
        List<Pedido> pedidos = pedidoService.listarTodosPedidos();
        
        model.addAttribute("clientes", clientes);
        model.addAttribute("pedido", pedidos);
        return "listagem";
    }
    
    @GetMapping("/excluir-cliente/{id}") 
    public String excluirCliente(Model model, @RequestParam Integer idcliente) { 
        clienteService.excluirCliente(idcliente);
        return "redirect:/listagem";
    } 
    
    @GetMapping("/excluir-pedido/{id}") 
    public String excluirPedido(Model model,@ModelAttribute Pedido pedido , @RequestParam Integer id) { 
        pedidoService.excluirPedido(id);
        return "redirect:/listagem";
    } 
    
}

