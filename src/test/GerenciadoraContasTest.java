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
        ContaCorrente conta01 = new ContaCorrente(1,200, true);
        ContaCorrente conta02 = new ContaCorrente(2,0,true);
        List<ContaCorrente> contasDoBanco = new ArrayList<>();
        contasDoBanco.add(conta01);
        contasDoBanco.add(conta02);
        GerenciadoraContas gerContas = new GerenciadoraContas(contasDoBanco);
        /*=============== Execução - Act ================*/
        gerContas.transfereValor(1,100,2);
        /*=============== Verificações - Verify ================*/
        assertThat(conta02.getSaldo(), is(100.0));
        assertThat(conta01.getSaldo(), is(100.0));
    }

}
