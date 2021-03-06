package test;

import negocio.Cliente;
import negocio.GerenciadoraClientes;
import negocio.IdadeNaoPermitidaException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GerenciadoraClientesTest {

    GerenciadoraClientes gerClientes;
    int idCliente1 = 1;
    int idCliente2 = 2;

    @Before
    public void setUp(){
        /*=============== Montagem do Cenário ================*/
        //Criando alguns Clientes
        Cliente cliente01 = new Cliente(idCliente1, "Gustavo Farias", 31, "gugafarias@gmail.com", 1, true);
        Cliente cliente02 = new Cliente(idCliente2, "Felipe Augusto", 34, "felipeaugusto@gmail.com", 2, true);
        //Inserindo os clientes na lista do Banco
        List<Cliente> clientesDoBanco = new ArrayList<>();
        clientesDoBanco.add(cliente01);
        clientesDoBanco.add(cliente02);
        //Instancia uma GerenciadoraClientes
        gerClientes = new GerenciadoraClientes(clientesDoBanco);
    }

    @After
    public void tearDown(){
        gerClientes.limpa();
    }

    @Test
    public void testPesquisaCliente() {
        /*=============== Execução ================*/
        //Roda o metodo que se quer testar - Act
        Cliente cliente = gerClientes.pesquisaCliente(idCliente1);
        /*=============== Verificações ================*/
        // verificação - Verify
        assertThat(cliente.getId(),is(idCliente1));
        assertThat(cliente.getEmail(),is("gugafarias@gmail.com"));
        assertTrue(cliente.getEmail()=="gugafarias@gmail.com");
        assertEquals("gugafarias@gmail.com", cliente.getEmail());
        assertEquals(cliente.getEmail(),"gugafarias@gmail.com");
    }

    @Test
    public void testRemoveCliente() {
        /*=============== Execução ================*/
        //Roda o metodo que se quer testar - Act
        boolean clienteRemovido = gerClientes.removeCliente(idCliente2);
        /*=============== Verificações ================*/
        // verificação - Verify
        assertThat(clienteRemovido, is(true));
        assertThat(gerClientes.getClientesDoBanco().size(),is(1));
        assertNull(gerClientes.pesquisaCliente(idCliente2));
    }

    @Test
    public void testPesquisaClienteInexistente() {
        /*=============== Execução ================*/
        Cliente cliente = gerClientes.pesquisaCliente(1001);
        /*=============== Verificações ================*/
        assertNull(cliente);
    }

    @Test
    public void testRemoveClienteInexistente() {
        /*=============== Execução ================*/
        boolean clienteRemovido = gerClientes.removeCliente(1001);
        /*=============== Verificações ================*/
        assertThat(clienteRemovido, is(false));
        assertThat(gerClientes.getClientesDoBanco().size(),is(2));
    }

    @Test
    public void testValidaIdadeMinima() throws IdadeNaoPermitidaException {
        /*=============== Criação Cenário - Arrange =================*/
        Cliente cliente = new Cliente(idCliente1, "Gustavo", 18, "guga@gmail.com", 1, true);
        /*=============== Execução - Act =======++++++++++++=========*/
        boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
        /*=============== Verificações - Assert =====================*/
        assertTrue(idadeValida);
    }
    @Test
    public void testValidaIdadeMaxima() throws IdadeNaoPermitidaException {
        /*=============== Criação Cenário - Arrange =================*/
        Cliente cliente = new Cliente(idCliente1, "Gustavo", 65, "guga@gmail.com", 1, true);
        /*=============== Execução - Act =======++++++++++++=========*/
        boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());
        /*=============== Verificações - Assert =====================*/
        assertTrue(idadeValida);
    }

    @Test
    public void testValidaIdadeAbaixoMinima() throws IdadeNaoPermitidaException {
        /*=============== Criação Cenário - Arrange =================*/
        Cliente cliente = new Cliente(idCliente1, "Gustavo", 17, "guga@gmail.com", 1, true);
        /*=============== Execução - Act =======++++++++++++=========*/
        try{
            gerClientes.validaIdade(cliente.getIdade());
            fail();
        } catch (Exception e ){
            /*=============== Verificações - Assert =====================*/
            assertThat(e.getMessage(),is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
        }
    }

    @Test
    public void testValidaIdadeAcimaMaxima() throws IdadeNaoPermitidaException {
        /*=============== Criação Cenário - Arrange =================*/
        Cliente cliente = new Cliente(idCliente1, "Gustavo", 66, "guga@gmail.com", 1, true);
        /*=============== Execução - Act =======++++++++++++=========*/
        try{
            gerClientes.validaIdade(cliente.getIdade());
            fail();
        } catch (Exception e ){
            /*=============== Verificações - Assert =====================*/
            assertThat(e.getMessage(),is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
        }
    }

}
