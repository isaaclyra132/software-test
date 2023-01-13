package utilitario;

import static java.util.Objects.isNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtils {
	private static final BigDecimal VALOR_3 = new BigDecimal("3");
	private static final BigDecimal SOMA_MEDIAS = new BigDecimal(4+5+6);

	public static boolean eMenorQue(BigDecimal op1, BigDecimal op2) {
		return op1.compareTo(op2) < 0;
	}

	public static boolean eMenorQue(BigDecimal op, double i) {
		return eMenorQue(op, new BigDecimal(i));
	}

	public static boolean eMaiorQue(BigDecimal op1, BigDecimal op2) {
		return op1.compareTo(op2) > 0;
	}

	public static boolean eMaiorIgualQue(BigDecimal op1, BigDecimal op2) {
		return op1.compareTo(op2) >= 0;
	}

	public static boolean eMaiorIgualQue(BigDecimal op, double i) {
		return eMaiorIgualQue(op, new BigDecimal(i));
	}

	public static void validarIntervalo(BigDecimal op, BigDecimal limiteInferior, BigDecimal limiteSuperior) {
		if (eMenorQue(op, limiteInferior) || eMaiorQue(op, limiteSuperior)) {
			throw new IllegalArgumentException();
		}
	}

	public static void validarNulo(BigDecimal op) {
		if (isNull(op)) {
			throw new IllegalArgumentException();
		}
	}

	public static BigDecimal calcularMedia(BigDecimal op1, BigDecimal op2, BigDecimal op3) {
		BigDecimal somatorio = op1.multiply(BigDecimal.valueOf(4.0))
				.add(op2.multiply(BigDecimal.valueOf(5.0)))
				.add(op3.multiply(BigDecimal.valueOf(6.0)));
		BigDecimal media = somatorio.divide(SOMA_MEDIAS, RoundingMode.HALF_UP);

		return media;
	}
}
