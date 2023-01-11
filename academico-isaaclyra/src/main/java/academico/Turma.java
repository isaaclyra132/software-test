package academico;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class Turma
{
	private ComponenteCurricular componente;

	private Docente docente;

	private final Map<Discente, Matricula> matriculas;

	private final int numeroVagas;

	public Turma(ComponenteCurricular componente, Docente docente, int numeroVagas)
	{
		this.componente = componente;
		this.docente = docente;
		this.numeroVagas = numeroVagas;
		this.matriculas = new HashMap<Discente, Matricula>(this.numeroVagas * 3);
	}

	public void consolidarParcialmente()
	{
		matriculas.values().stream().forEach(Matricula::consolidarParcialmente);
	}

	public void matricular(Discente discente)
	{
		Matricula m = new Matricula(discente, this);
		this.matriculas.put(discente, m);
	}

	public void registrarPrimeiraNota(Discente discente, BigDecimal nota)
	{
		this.matriculas.get(discente).registrarPrimeiraNota(nota);
	}
	
	public void registrarSegundaNota(Discente discente, BigDecimal nota)
	{
		this.matriculas.get(discente).registrarSegundaNota(nota);
	}
	
	public void registrarTerceiraNota(Discente discente, BigDecimal nota)
	{
		this.matriculas.get(discente).registrarTerceiraNota(nota);
	}
	
	public void registrarFrequencia(Discente discente, BigDecimal frequencia) {
		this.matriculas.get(discente).registrarFrequencia(frequencia);
	}

	public ComponenteCurricular componenteCurricular()
	{
		return componente;
	}

	public Docente docente()
	{
		return docente;
	}

	public Collection<Matricula> matriculas()
	{
		return matriculas.values();
	}
}
