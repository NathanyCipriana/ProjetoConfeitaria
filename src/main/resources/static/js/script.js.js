const API_JS = '/api';

// Cliente API
class ClienteAPI {
    static async listar() {
        const response = await fetch(`${API_JS}/clientes/listar`);
        return await response.json();
    }
    
    static async buscar(id) {
        const response = await fetch(`${API_JS}/clientes/buscar/${id}`);
        return await response.json();
    }
    
    static async cadastrar(cliente) {
        const response = await fetch(`${API_JS}/clientes/cadastrar`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(cliente)
        });
        return await response.json();
    }
    
    static async atualizar(id, cliente) {
        const response = await fetch(`${API_JS}/clientes/atualizar/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(cliente)
        });
        return await response.json();
    }
    
    static async excluir(id) {
        const response = await fetch(`${API_JS}/clientes/excluir/${id}`, {
            method: 'DELETE'
        });
        return response;
    }
}

// Pedido API
class PedidoAPI {
    static async listar() {
        const response = await fetch(`${API_JS}/pedidos/listar`);
        return await response.json();
    }
    
    static async cadastrar(pedido) {
        const response = await fetch(`${API_JS}/pedidos/cadastrar`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(pedido)
        });
        return await response.json();
    }
}

// Funções para o front-end
document.addEventListener('DOMContentLoaded', function() {
    // Cadastro de Cliente
    const clienteForm = document.getElementById('cliente-form');
    if (clienteForm) {
        clienteForm.addEventListener('submit', async function(e) {
            e.preventDefault();
            
            const cliente = {
                nome: document.getElementById('nome').value,
                email: document.getElementById('email').value,
                telefone: document.getElementById('telefone').value,
                endereco: document.getElementById('endereco').value
            };
            
            try {
                await ClienteAPI.cadastrar(cliente);
                alert('Cliente cadastrado com sucesso!');
                clienteForm.reset();
            } catch (error) {
                alert('Erro ao cadastrar cliente: ' + error.message);
            }
        });
    }
    
    // Carregar clientes para o select de pedidos
    const clienteSelect = document.getElementById('cliente-pedido');
    if (clienteSelect) {
        CarregarClientesParaSelect();
    }
    
    // Cadastro de Pedido
    const pedidoForm = document.getElementById('pedido-form');
    if (pedidoForm) {
        pedidoForm.addEventListener('submit', async function(e) {
            e.preventDefault();
            
            const pedido = {
                produto: document.getElementById('produto').value,
                quantidade: parseInt(document.getElementById('quantidade').value),
                valorUnitario: parseFloat(document.getElementById('valor').value),
                dataEntrega: document.getElementById('data-entrega').value,
                observacoes: document.getElementById('observacoes').value,
                cliente: {
                    id: parseInt(document.getElementById('cliente-pedido').value)
                }
            };
            
            try {
                await PedidoAPI.cadastrar(pedido);
                alert('Pedido cadastrado com sucesso!');
                pedidoForm.reset();
            } catch (error) {
                alert('Erro ao cadastrar pedido: ' + error.message);
            }
        });
    }
    
    // Listagem
    if (window.location.pathname.includes('listagem')) {
        carregarListagens();
    }
});

async function CarregarClientesParaSelect() {
    try {
        const clientes = await ClienteAPI.listar();
        const select = document.getElementById('cliente-pedido');
        
        select.innerHTML = '<option value="">Selecione um cliente</option>';
        clientes.forEach(cliente => {
            const option = document.createElement('option');
            option.value = cliente.id;
            option.textContent = cliente.nome;
            select.appendChild(option);
        });
    } catch (error) {
        console.error('Erro ao carregar clientes:', error);
    }
}

async function carregarListagens() {
    try {
        // Carregar clientes
        const clientes = await ClienteAPI.listar();
        const tbodyClientes = document.querySelector('#tabela-clientes tbody');
        tbodyClientes.innerHTML = '';
        
        clientes.forEach(cliente => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${cliente.nome}</td>
                <td>${cliente.email}</td>
                <td>${cliente.telefone}</td>
                <td>${cliente.endereco}</td>
            `;
            tbodyClientes.appendChild(tr);
        });
        
        // Carregar pedidos
        const pedidos = await PedidoAPI.listar();
        const tbodyPedidos = document.querySelector('#tabela-pedidos tbody');
        tbodyPedidos.innerHTML = '';
        
        pedidos.forEach(pedido => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${pedido.cliente.nome}</td>
                <td>${pedido.produto}</td>
                <td>${pedido.quantidade}</td>
                <td>R$ ${pedido.getValorTotal ? pedido.getValorTotal() : (pedido.valorUnitario * pedido.quantidade).toFixed(2)}</td>
                <td>${new Date(pedido.dataEntrega).toLocaleDateString('pt-BR')}</td>
                <td>${pedido.observacoes || '-'}</td>
            `;
            tbodyPedidos.appendChild(tr);
        });
    } catch (error) {
        console.error('Erro ao carregar listagens:', error);
    }
}