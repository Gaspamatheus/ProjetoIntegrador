// Variável global para armazenar o ID do cliente a ser excluído
let clienteIdToDelete = null;

// Carregar todos os clientes ao iniciar a página
document.addEventListener('DOMContentLoaded', function() {
    listarTodosClientes();
});

// Listar todos os clientes
async function listarTodosClientes() {
    try {
        const clientes = await fetchAPI('/clientes');
        renderizarClientes(clientes);
    } catch (error) {
        showMessage('Erro ao carregar clientes: ' + error.message, 'error');
    }
}

// Buscar clientes por nome
async function buscarClientes() {
    const nome = document.getElementById('searchNome').value.trim();

    if (!nome) {
        showMessage('Digite um nome para buscar', 'error');
        return;
    }

    try {
        const clientes = await fetchAPI(`/clientes/buscar?nome=${encodeURIComponent(nome)}`);
        renderizarClientes(clientes);

        if (clientes.length === 0) {
            showMessage('Nenhum cliente encontrado', 'error');
        }
    } catch (error) {
        showMessage('Erro ao buscar clientes: ' + error.message, 'error');
    }
}

// Renderizar tabela de clientes
function renderizarClientes(clientes) {
    const tbody = document.getElementById('clientesTableBody');

    if (!clientes || clientes.length === 0) {
        tbody.innerHTML = '<tr><td colspan="4" class="loading">Nenhum cliente encontrado</td></tr>';
        return;
    }

    tbody.innerHTML = clientes.map(cliente => `
        <tr>
            <td>${cliente.id}</td>
            <td>${cliente.nome}</td>
            <td>${formatCurrency(cliente.valorPago)}</td>
            <td class="action-buttons">
                <button class="btn btn-primary btn-small" onclick="editarCliente(${cliente.id})">
                    ✏️ Editar
                </button>
                <button class="btn btn-danger btn-small" onclick="prepararExclusao(${cliente.id})">
                    🗑️ Excluir
                </button>
            </td>
        </tr>
    `).join('');
}

// Abrir modal para novo cliente
function novoCliente() {
    clearForm('clienteForm');
    document.getElementById('modalTitle').textContent = 'Novo Cliente';
    openModal('clienteModal');
}

// Editar cliente
async function editarCliente(id) {
    try {
        const cliente = await fetchAPI(`/clientes/${id}`);

        document.getElementById('clienteId').value = cliente.id;
        document.getElementById('nome').value = cliente.nome;
        document.getElementById('valorPago').value = cliente.valorPago;

        document.getElementById('modalTitle').textContent = 'Editar Cliente';
        openModal('clienteModal');
    } catch (error) {
        showMessage('Erro ao carregar dados do cliente: ' + error.message, 'error');
    }
}

// Salvar cliente (criar ou atualizar)
async function salvarCliente(event) {
    event.preventDefault();

    const id = document.getElementById('clienteId').value;
    const clienteData = {
        nome: document.getElementById('nome').value,
        valorPago: parseFloat(document.getElementById('valorPago').value)
    };

    try {
        if (id) {
            // Atualizar cliente existente
            await fetchAPI(`/clientes/${id}`, {
                method: 'PUT',
                body: JSON.stringify(clienteData)
            });
            showMessage('Cliente atualizado com sucesso!', 'success');
        } else {
            // Criar novo cliente
            await fetchAPI('/clientes', {
                method: 'POST',
                body: JSON.stringify(clienteData)
            });
            showMessage('Cliente criado com sucesso!', 'success');
        }

        closeModal('clienteModal');
        clearForm('clienteForm');
        listarTodosClientes();
        scrollToTop();
    } catch (error) {
        showMessage('Erro ao salvar cliente: ' + error.message, 'error');
    }
}

// Preparar exclusão (abrir modal de confirmação)
function prepararExclusao(id) {
    clienteIdToDelete = id;
    openModal('deleteModal');
}

// Confirmar exclusão
async function confirmarExclusao() {
    if (!clienteIdToDelete) return;

    try {
        await fetchAPI(`/clientes/${clienteIdToDelete}`, {
            method: 'DELETE'
        });

        showMessage('Cliente excluído com sucesso!', 'success');
        closeModal('deleteModal');
        clienteIdToDelete = null;
        listarTodosClientes();
        scrollToTop();
    } catch (error) {
        showMessage('Erro ao excluir cliente: ' + error.message, 'error');
    }
}

// Event listener para busca com Enter
document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchNome');
    if (searchInput) {
        searchInput.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                buscarClientes();
            }
        });
    }
});