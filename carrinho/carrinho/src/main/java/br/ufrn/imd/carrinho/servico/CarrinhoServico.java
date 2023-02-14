package br.ufrn.imd.carrinho.servico;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import br.ufrn.imd.carrinho.dominio.*;

public class CarrinhoServico {

	final List<Estado> regiaoNordesteSudeste = new ArrayList<>(
			Arrays.asList(
					//REGIAO NORDESTE
					Estado.RN,
					Estado.BA,
					Estado.CE,
					Estado.PE,
					Estado.PB,
					Estado.PI,
					Estado.AL,
					Estado.SE,
					Estado.MA,

					//REGIAO SUDESTE
					Estado.ES,
					Estado.MG,
					Estado.RJ,
					Estado.SP
			));

	Double calcularPrecoItens(List<Item> itens) {
		var total = 0.0;

		for (Item item: itens) {
			total += item.getPreco();
		}

		if(total > 1000.0) {
			total *= 1.2;
		} else if(total > 500.0) {
			total *= 1.1;
		} else if (elegivelDescontoItemTipo(itens)) {
			total *= 1.05;
		}

		return total;
	}

	Double calcularFrete(Estado estadoDoUsuario, List<Item> itens) {
		var pesoTotal = 0.0;
		var precoFrete = 0.0;

		for(Item item : itens) {
			pesoTotal += item.getPeso();
		}

		if(pesoTotal <= 10.0) {
			precoFrete = 0.0;
		} else if (pesoTotal > 10.0 || pesoTotal<= 40.0) {
			precoFrete = pesoTotal * 0.5;
		} else if (pesoTotal > 40.0 || pesoTotal <= 100.0) {
			precoFrete = pesoTotal * 0.75;
		} else {
			precoFrete = pesoTotal;
		}

		if (elegivelTaxaRegiao(estadoDoUsuario)) {
			precoFrete *= 1.1;
		}

		return precoFrete;
	}

	Boolean elegivelTaxaRegiao(Estado estadoDoUsuario) {
		List<Estado> testeRegiao =  regiaoNordesteSudeste.stream()
				.filter(estado -> estado == estadoDoUsuario)
				.collect(Collectors.toList());

		if (testeRegiao.isEmpty()) {
			return true;
		}
		return false;
	}

	Boolean elegivelDescontoItemTipo(List<Item> itens) {
		Set<ItemTipo> tiposDuplicados = new HashSet<>();

		if(itens.stream()
				.filter(item -> !tiposDuplicados.add(item.getTipo()))
				.collect(Collectors.toList()).isEmpty()) {
			return false;
		}

		return true;
	}

	public Pedido finalizar(Usuario usuario, List<Item> itens, LocalDate checkoutDate) {
//		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
//		decimalFormat.setRoundingMode(RoundingMode.UP);

		var precoItens = calcularPrecoItens(itens);
		var precoFrete = calcularFrete(usuario.getEndereco().getEstado(), itens);
		var precoTotal = precoItens + precoFrete;

		Pedido pedido = new Pedido(
				usuario,
				itens,
				precoTotal,
				precoItens,
				precoFrete,
				checkoutDate);


		return pedido;
	}
}
