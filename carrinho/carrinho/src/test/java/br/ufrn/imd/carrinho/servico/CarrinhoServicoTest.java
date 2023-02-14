package br.ufrn.imd.carrinho.servico;

import br.ufrn.imd.carrinho.dominio.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

public class CarrinhoServicoTest {

	final CarrinhoServico service = new CarrinhoServico();

	@Test
	@DisplayName("Deve ser cobrado valores de frete por kg de carga diferentes e por destino")
	@ParameterizedTest(name = "[{index}]''{0}'' => Preço esperado do frete: {5}")
	@CsvSource({
			"PesoMenor10RegiaoSul, 3.3, 3.3, 3.3, SC, 0.0",
			"PesoMenor10RegiaoNordeste, 3.3, 3.3, 3.3, RN, 0.0",
			"PesoIgual10RegiaoSul, 3.4, 3.3, 3.3, RS, 0.0",
			"PesoIgual10RegiaoNordeste, 3.4, 3.3, 3.3, BA, 0.0",
			"PesoMaior10RegiaoSul, 4, 4, 4, RS, 6.6",
			"PesoMaior10RegiaoNordeste, 4, 4, 4, CE, 6.0",
			"PesoMenor40RegiaoSul, 34.5, 4.5, 0.9, SC, 21.95",
			"PesoMenor40RegiaoSudeste, 34.5, 4.5, 0.9, SP, 19.95",
			"PesoIgual40RegiaoCentroOeste, 34.5, 4.5, 1, MT, 22",
			"PesoIgual40RegiaoSudeste, 34.5, 4.5, 1, RJ, 20",
			"PesoMaior40RegiaoSul, 35, 4, 2, RS, 33.83",
			"PesoMaior40RegiaoNordeste, 35, 4, 2, BA, 30.75",
			"PesoMenor100RegiaoNorte, 45, 45, 9, AC, 81.68",
			"PesoMenor100RegiaoSudeste, 45, 45, 9, RJ, 74.25",
			"PesoIgual100RegiaoCentroOeste, 45, 45, 10, GO, 82.5",
			"PesoIgual100RegiaoNordeste, 45, 45, 10, PI, 75",
			"PesoMaior100RegiaoSul, 45, 45, 11, PR, 111.1",
			"PesoMaior100RegiaoSudeste, 45, 45, 11, SE, 101"
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

		Assertions.assertEquals(precoFreteEsperado, pedido.getPrecoFrete());
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
