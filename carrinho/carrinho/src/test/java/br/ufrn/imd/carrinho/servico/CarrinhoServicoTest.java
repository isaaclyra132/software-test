package br.ufrn.imd.carrinho.servico;

import br.ufrn.imd.carrinho.dominio.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class CarrinhoServicoTest {
	CarrinhoServico service;

	CarrinhoServicoTest(CarrinhoServico service) {
		this.service = service;
	}

	@DisplayName("Deve ser cobrado valores de frete por kg de carga diferentes e por destino")
	@ParameterizedTest(name = "[{index}]''{0}'' => Preço esperado do frete: {5}")
	@CsvSource({
			"PesoMenor10RegiaoSul, 3.3, 3.3, 3.3, SC, 0.0",
			"PesoMenor10RegiaoNordeste, 3.3, 3.3, 3.3, RN, 0.0",
			"PesoIgual10RegiaoSul, 3.4, 3.3, 3.3, RS, 0.0",
			"PesoIgual10RegiaoNordeste, 3.4, 3.3, 3.3, BA, 0.0",
			"PesoMaior10RegiaoSul, 4, 4, 4, RS, 0.0",
			"PesoMaior10RegiaoNordeste, 4, 4, 4, CE, 0.0",
			"PesoMenor40RegiaoSul",
			"PesoMenor40RegiaoSudeste",
			"PesoIgual40RegiaoCentroOeste",
			"PesoIgual40RegiaoSudeste",
			"PesoMaior40RegiaoSul",
			"PesoMaior40RegiaoNordeste",
			"PesoMenor100RegiaoNorte",
			"PesoMenor100RegiaoSudeste",
			"PesoIgual100RegiaoCentroOeste",
			"PesoIgual100RegiaoNordeste",
			"PesoMaior100RegiaoSul",
			"PesoMaior100RegiaoSudeste"
	})
	public void TestePrecoFrete(
			String nomeTeste,
			Double pesoItem1,
			Double pesoItem2,
			Double pesoItem3,
			Estado estado,
			Double precoFreteEsperado) {
		Endereco endereco =  new Endereco("", 25, "", "", "", estado);
		Usuario usuario = new Usuario("", "", "", endereco);
		Item item1 = new Item("", "", 200.0, pesoItem1, ItemTipo.ELETRONICO);
		Item item2 = new Item("", "", 50.0, pesoItem2, ItemTipo.ROUPAS);
		Item item3 = new Item("", "", 30.0, pesoItem3, ItemTipo.LIVROS);

		Pedido pedido = service.finalizar(usuario, Arrays.asList(item1, item2, item3), LocalDate.now());

		Assertions.assertEquals(pedido.getPrecoFrete(), precoFreteEsperado);
	}

	@DisplayName("Deve ser cobrado valores de frete por kg de carga diferentes e por destino")
	@ParameterizedTest(name = "{index} => ''{0}''")
	@CsvSource({
			"TestePesoMenor10RegiaoSul",
			"TestePesoMenor10RegiaoNordeste",
			"TestePesoIgual10",
			"TestePesoMaior10",
			"TestePesoMenor40",
			"TestePesoIgual40",
			"TestePesoMaior40RegiaoSul",
			"TestePesoMaior40RegiaoNordeste",
			"TestePesoMenor100",
			"TestePesoIgual100",
			"TestePesoMaior100"
	})
	public void TestePrecoItens(String nomeTeste) {
		Assertions.fail("Teste ainda não foi implementado.");
	}

	@DisplayName("Deve ser cobrado valores de frete por kg de carga diferentes e por destino")
	@ParameterizedTest(name = "{index} => ''{0}''")
	@CsvSource({
			"TestePesoMenor10RegiaoSul",
			"TestePesoMenor10RegiaoNordeste",
			"TestePesoIgual10",
			"TestePesoMaior10",
			"TestePesoMenor40",
			"TestePesoIgual40",
			"TestePesoMaior40RegiaoSul",
			"TestePesoMaior40RegiaoNordeste",
			"TestePesoMenor100",
			"TestePesoIgual100",
			"TestePesoMaior100"
	})
	public void TestePrecoTotal(String nomeTeste) {
		Assertions.fail("Teste ainda não foi implementado.");
	}
}
