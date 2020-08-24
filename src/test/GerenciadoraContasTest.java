package test;

import negocio.ContaCorrente;
import negocio.GerenciadoraContas;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GerenciadoraContasTest {

    @Test
    public void testeTransfereValor(){
        /*=============== Montagem do Cenário - Arrange ================*/
        int idConta1 = 1;
        int idConta2 = 2;
        ContaCorrente conta01 = new ContaCorrente(idConta1,200, true);
        ContaCorrente conta02 = new ContaCorrente(idConta1,0,true);
        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        contasDoBanco.add(conta01);
        contasDoBanco.add(conta02);
        GerenciadoraContas gerContas = new GerenciadoraContas(contasDoBanco);
        /*=============== Execução - Act ================*/
        gerContas.transfereValor(idConta1,100,idConta2);
        /*=============== Verificações - Verify ================*/
        assertThat(conta02.getSaldo(), is(100.0));
        assertThat(conta01.getSaldo(), is(100.0));
    }

}
