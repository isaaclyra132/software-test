package utilitario;

import static java.util.Objects.isNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtils {
	private static final BigDecimal VALOR_3 = new BigDecimal("3");

	public static boolean eMenorQue(BigDecimal op1, BigDecimal op2) {
		return op1.compareTo(op2) < 0;
	}

	public static boolean eMenorQue(BigDecimal op, int i) {
		return eMenorQue(op, new BigDecimal(i));
	}

	public static boolean eMaiorQue(BigDecimal op1, BigDecimal op2) {
		return op1.compareTo(op2) > 0;
	}

	public static boolean eMaiorIgualQue(BigDecimal op1, BigDecimal op2) {
		return op1.compareTo(op2) >= 0;
	}

	public static boolean eMaiorIgualQue(BigDecimal op, int i) {
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
		BigDecimal somatorio = op1.add(op2).add(op3);
		BigDecimal media = somatorio.divide(VALOR_3, RoundingMode.HALF_EVEN);

		return media;
	}
}
