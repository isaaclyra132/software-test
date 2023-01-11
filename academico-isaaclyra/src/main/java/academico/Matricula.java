package academico;

import static utilitario.BigDecimalUtils.calcularMedia;
import static utilitario.BigDecimalUtils.eMaiorIgualQue;
import static utilitario.BigDecimalUtils.eMenorQue;
import static utilitario.BigDecimalUtils.validarIntervalo;
import static utilitario.BigDecimalUtils.validarNulo;

import java.math.BigDecimal;

public final class Matricula {
	private static final BigDecimal NOTA_LIMITE_INFERIOR = new BigDecimal("0");

	private static final BigDecimal NOTA_LIMITE_SUPERIOR = new BigDecimal("10");

	private static final BigDecimal FREQUENCIA_LIMITE_INFERIOR = new BigDecimal("0");

	private static final BigDecimal FREQUENCIA_LIMITE_SUPERIOR = new BigDecimal("100");

	private  Discente discente;

	private  Turma turma;

	private BigDecimal nota1;

	private BigDecimal nota2;

	private BigDecimal nota3;

	private BigDecimal frequencia;

	private StatusAprovacao statusParcial;

	public Matricula(Discente discente, Turma turma) {
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
		var media = calcularMedia(nota1(), nota2(), nota3());
		
		if (eMenorQue(frequencia, 75)) 
		{
			if(eMenorQue(media, 5)) 
			{
				this.statusParcial = StatusAprovacao.REMF;
			}
			else 
			{
				this.statusParcial = StatusAprovacao.REPF;
			}
		} 
		else 
		{
			if(eMaiorIgualQue(media, 7)) 
			{
				this.statusParcial = StatusAprovacao.APR;
			}
			else if(eMaiorIgualQue(media, 5))
			{
				
				if(eMaiorIgualQue(nota1, 3) && eMaiorIgualQue(nota2, 3) && eMaiorIgualQue(nota3, 3)) 
				{
					this.statusParcial = StatusAprovacao.APRN;
				}
				else {
					this.statusParcial = StatusAprovacao.REC;
				}
			}
			else if(eMaiorIgualQue(media, 3)) 
			{
				this.statusParcial = StatusAprovacao.REC;
			}
			else
			{
				this.statusParcial = StatusAprovacao.REP;
			}
		}

	}

	public BigDecimal nota1() {
		return this.nota1;
	}

	public BigDecimal nota2() {
		return this.nota2;
	}

	public BigDecimal nota3() {
		return this.nota3;
	}

	public BigDecimal frequencia() {
		return this.frequencia;
	}

	public StatusAprovacao statusParcial() {
		return this.statusParcial;
	}

	public Discente discente() {
		return this.discente;
	}

	public Turma turma() {
		return this.turma;
	}

	protected void registrarPrimeiraNota(BigDecimal nota) {
		validarNota(nota);

		this.nota1 = nota;
	}

	private void validarNota(BigDecimal nota) {
		validarNulo(nota);

		validarIntervalo(nota, NOTA_LIMITE_INFERIOR, NOTA_LIMITE_SUPERIOR);
	}

	protected void registrarSegundaNota(BigDecimal nota) {
		validarNota(nota);

		this.nota2 = nota;
	}

	protected void registrarTerceiraNota(BigDecimal nota) {
		validarNota(nota);

		this.nota3 = nota;
	}

	protected void registrarFrequencia(BigDecimal frequencia) {
		validarFrequencia(frequencia);

		this.frequencia = frequencia;
	}

	private void validarFrequencia(BigDecimal frequencia) 
	{
		validarNulo(frequencia);

		validarIntervalo(frequencia, FREQUENCIA_LIMITE_INFERIOR, FREQUENCIA_LIMITE_SUPERIOR);
	}
}
