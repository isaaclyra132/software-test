package br.ufrn.imd.carrinho.servico;

import br.ufrn.imd.carrinho.dominio.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.Arrays;

public class CarrinhoServicoTest {

	final CarrinhoServico service = new CarrinhoServico();

	@DisplayName("Deve ser aplicado descontos de acordo com valor total ou repetição de categoria")
	@ParameterizedTest(name = "[{index}]''{0}'' => Preço esperado com desconto: {7}")
	@CsvSource({
			"PrecoItensMenor500SemRepeticao, 200, 200, 99, CASA, COZINHA, ELETRONICO, 499.0",
			"PrecoItensMenor500ComRepeticao, 200, 200, 99, LIVROS, LIVROS, LIVROS, 474.05",
			"PrecoItensIgual500SemRepeticao, 200, 200, 100, COZINHA, ELETRONICO, LIVROS, 500.0",
			"PrecoItensIgual500ComRepeticao, 200, 200, 100, ROUPAS, ROUPAS, ROUPAS, 475.0",
			"PrecoItensMaior500SemRepeticao, 200, 200, 101, ELETRONICO, LIVROS, ROUPAS, 450.9",
			"PrecoItensMaior500ComRepeticao, 200, 200, 101, CASA, CASA, CASA, 450.9",
			"PrecoItensMenor1000SemRepeticao, 400, 400, 199, LIVROS, ROUPAS, CASA, 899.1",
			"PrecoItensMenor1000ComRepeticao, 400, 400, 199, COZINHA, COZINHA, COZINHA, 899.1",
			"PrecoItensIgual1000SemRepeticao, 400, 400, 200, ROUPAS, CASA, COZINHA, 900",
			"PrecoItensIgual1000ComRepeticao, 400, 400, 200, ELETRONICO, ELETRONICO, ELETRONICO, 900",
			"PrecoItensMaior1000SemRepeticao, 400, 400, 201, CASA, COZINHA, ELETRONICO, 800.8",
			"PrecoItensMaior1000ComRepeticao, 400, 400, 201, LIVROS, LIVROS, LIVROS, 800.8",
	})
	public void TestePrecoItens(String nomeTeste,
								Double precoItem1,
								Double precoItem2,
								Double precoItem3,
								ItemTipo itemTipo1,
								ItemTipo itemTipo2,
								ItemTipo itemTipo3,
								Double precoItensEsperado) {
		Endereco endereco =  new Endereco("", 25, "", "", "", Estado.RN);
		Usuario usuario = new Usuario("", "", "", endereco);
		Item item1 = new Item("", "", precoItem1, 10.0, itemTipo1);
		Item item2 = new Item("", "", precoItem2, 10.0, itemTipo2);
		Item item3 = new Item("", "", precoItem3, 10.0, itemTipo3);

		Pedido pedido = service.finalizar(usuario, Arrays.asList(item1, item2, item3), LocalDate.now());

		Assertions.assertEquals(precoItensEsperado, pedido.getPrecoItens());
	}

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
			"PesoMaior100RegiaoNordeste, 45, 45, 11, SE, 101"
	})
	public void TestePrecoFrete(String nomeTeste,
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

	@DisplayName("Deve ser somado o preço dos itens com desconto aplicado e preco do frete com taxas")
	@ParameterizedTest(name = "[{index}]''{0}'' => Preço Total Esperado: {11}")
	@CsvSource({
			"PrecoMenor500SemRepeticaoPesoMaior40Nordeste, 200, 200, 99, CASA, COZINHA, ELETRONICO, 35, 4, 2, BA, 529.75",
			"PrecoIgual500ComRepeticaoPesoMenor100Norte, 200, 200, 100, ROUPAS, ROUPAS, ROUPAS, 45, 45, 9, AC, 556.68",
			"PrecoMaior500SemRepeticaoPesoMenor10Sul, 200, 200, 101, ELETRONICO, LIVROS, ROUPAS, 3.3, 3.3, 3.3, SC, 450.9",
			"PrecoMenor1000SemRepeticaoPesoMaior10Sul, 400, 400, 199, LIVROS, ROUPAS, CASA, 4, 4, 4, RS, 905.7",
			"PrecoIgual1000SemRepeticaoPesoMaior40Nordeste, 400, 400, 200, ROUPAS, CASA, COZINHA, 35, 4, 2, BA, 930.75",
			"PrecoMaior1000SemRepeticaoPesoMaior100Sudeste, 400, 400, 201, CASA, COZINHA, ELETRONICO, 45, 45, 11, SE, 901.8"
	})
	public void TestePrecoTotal(String nomeTeste,
								Double precoItem1,
								Double precoItem2,
								Double precoItem3,
								ItemTipo itemTipo1,
								ItemTipo itemTipo2,
								ItemTipo itemTipo3,
								Double pesoItem1,
								Double pesoItem2,
								Double pesoItem3,
								Estado estado,
								Double precoTotalEsperado
								) {
		Endereco endereco =  new Endereco("", 25, "", "", "", estado);
		Usuario usuario = new Usuario("", "", "", endereco);
		Item item1 = new Item("", "", precoItem1, pesoItem1, itemTipo1);
		Item item2 = new Item("", "", precoItem2, pesoItem2, itemTipo2);
		Item item3 = new Item("", "", precoItem3, pesoItem3, itemTipo3);

		Pedido pedido = service.finalizar(usuario, Arrays.asList(item1, item2, item3), LocalDate.now());

		Assertions.assertEquals(precoTotalEsperado, pedido.getPrecoTotal());
	}
}
