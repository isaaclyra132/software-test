package academico;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MatriculaTest {

    @ParameterizedTest
    @CsvSource(value = {
            "100, 7.5, 7.5, 7.5, APR",
            "100, 5.5, 5.5, 5.5, APRN",
            "100, 2.5, 5.5, 5.5, REC",
            "100, 2.5, 5.5, 8.5, REC",
            "100, 2.5, 2.5, 2.5, REP",
            "50, 5.5, 5.5, 5.5, REPF",
            "50, 2.5, 2.5, 2.5, REMF"
    })
    void consolidarParcialmente(
            BigDecimal frequencia,
            BigDecimal nota1,
            BigDecimal nota2,
            BigDecimal nota3,
            StatusAprovacao statusEsperado) {

        Matricula matricula = new Matricula();

        matricula.registrarFrequencia(frequencia);
        matricula.registrarPrimeiraNota(nota1);
        matricula.registrarSegundaNota(nota2);
        matricula.registrarTerceiraNota(nota3);

        matricula.consolidarParcialmente();

        StatusAprovacao statusRetornado = matricula.statusParcial();

        Assertions.assertEquals(statusEsperado, statusRetornado);
    }
}