package academico;

import static java.util.Objects.isNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Matricula
{
	private  Discente discente;

	private  Turma turma;

	private BigDecimal nota1;

	private BigDecimal nota2;

	private BigDecimal nota3;

	private BigDecimal frequencia;

	private StatusAprovacao statusParcial;

	public Matricula(Discente discente, Turma turma)
	{
		this.discente = discente;
		this.turma = turma;

		this.nota1 = null;
		this.nota2 = null;
		this.nota3 = null;

		this.frequencia = null;

		this.statusParcial = null;
	}

	public Matricula() {

	}

	protected void consolidarParcialmente()
	{
		BigDecimal somatorioDeNotas = nota1().add(nota2()).add(nota3());
		BigDecimal media = somatorioDeNotas.divide(BigDecimal.valueOf(3.0), RoundingMode.HALF_EVEN);
		if () {
			this.statusParcial = StatusAprovacao.APR;
		} else if () {
			this.statusParcial = StatusAprovacao.APRN;
		} else if () {
			this.statusParcial = StatusAprovacao.REC;
		} else if () {
			this.statusParcial = StatusAprovacao.REP;
		} else if () {
			this.statusParcial = StatusAprovacao.REPF;
		} else if () {
			this.statusParcial = StatusAprovacao.REMF;
		} else{
			throw new IllegalStateException("Funcionalidade ainda não foi implementada.");
		}
	}

	public BigDecimal nota1()
	{
		return this.nota1;
	}

	public BigDecimal nota2()
	{
		return this.nota2;
	}

	public BigDecimal nota3()
	{
		return this.nota3;
	}

	public BigDecimal frequencia()
	{
		return this.frequencia;
	}

	public StatusAprovacao statusParcial()
	{
		return this.statusParcial;
	}

	public Discente discente()
	{
		return this.discente;
	}

	public Turma turma()
	{
		return this.turma;
	}

	protected void registrarPrimeiraNota(BigDecimal nota)
	{
		validarNota(nota);
		
		this.nota1 = nota;
	}

	private void validarNota(BigDecimal nota)
	{
		if (isNull(nota))
		{
			throw new IllegalArgumentException();
		}

		if (nota.compareTo(BigDecimal.ZERO) < 0 || nota.compareTo(BigDecimal.valueOf(10l)) > 0)
		{
			throw new IllegalArgumentException();
		}
	}

	protected void registrarSegundaNota(BigDecimal nota)
	{
		validarNota(nota);
		
		this.nota2 = nota;
	}

	protected void registrarTerceiraNota(BigDecimal nota)
	{
		validarNota(nota);
		
		this.nota3 = nota;
	}

	protected void registrarFrequencia(BigDecimal frequencia)
	{
		if (isNull(frequencia))
		{
			throw new IllegalArgumentException();
		}

		if (frequencia.compareTo(BigDecimal.ZERO) < 0 || frequencia.compareTo(BigDecimal.valueOf(100l)) > 0)
		{
			throw new IllegalArgumentException();
		}
		
		this.frequencia = frequencia;
	}

}
