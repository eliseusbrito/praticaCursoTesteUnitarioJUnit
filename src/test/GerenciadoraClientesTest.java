package test;

import negocio.Cliente;
import negocio.GerenciadoraClientes;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GerenciadoraClientesTest {

    @Test
    public void testPesquisaCliente() {
        /*=============== Montagem do Cenário ================*/
        //Criando alguns Clientes
        Cliente cliente01 = new Cliente(1, "Gustavo Farias", 31, "gugafarias@gmail.com", 1, true);
        Cliente cliente02 = new Cliente(2, "Felipe Augusto", 34, "felipeaugusto@gmail.com", 2, true);
        //Inserindo os clientes na lista do Banco
        List<Cliente> clientesDoBanco = new ArrayList<>();
        clientesDoBanco.add(cliente01);
        clientesDoBanco.add(cliente02);
        //Instancia uma GerenciadoraClientes
        GerenciadoraClientes gerClientes = new GerenciadoraClientes(clientesDoBanco);
        /*=============== Execução ================*/
        //Roda o metodo que se quer testar - Act
        Cliente cliente = gerClientes.pesquisaCliente(1);
        /*=============== Verificações ================*/
        // verificação - Verify
        assertThat(cliente.getId(),is(1));
        assertThat(cliente.getEmail(),is("gugafarias@gmail.com"));
        assertTrue(cliente.getEmail()=="gugafarias@gmail.com");
        assertEquals("gugafarias@gmail.com", cliente.getEmail());
        assertEquals(cliente.getEmail(),"gugafarias@gmail.com");

    }

    @Test
    public void testRemoveCliente() {
        /*=============== Montagem do Cenário ================*/
        //Criando alguns Clientes
        Cliente cliente01 = new Cliente(1, "Gustavo Farias", 31, "gugafarias@gmail.com", 1, true);
        Cliente cliente02 = new Cliente(2, "Felipe Augusto", 34, "felipeaugusto@gmail.com", 2, true);
        //Inserindo os clientes na lista do Banco
        List<Cliente> clientesDoBanco = new ArrayList<>();
        clientesDoBanco.add(cliente01);
        clientesDoBanco.add(cliente02);
        //Instancia uma GerenciadoraClientes
        GerenciadoraClientes gerClientes = new GerenciadoraClientes(clientesDoBanco);
        /*=============== Execução ================*/
        //Roda o metodo que se quer testar - Act
        boolean clienteRemovido = gerClientes.removeCliente(2);
        /*=============== Verificações ================*/
        // verificação - Verify
        assertThat(clienteRemovido, is(true));
        assertThat(gerClientes.getClientesDoBanco().size(),is(1));
        assertNull(gerClientes.pesquisaCliente(2));
    }

}
