package br.ufrn.imd.pessoa.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pessoa
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String primeiroNome;

	private String sobrenome;

	public Pessoa()
	{
		super();
	}

	public Pessoa(String primeiroNome, String sobrenome)
	{
		this.id = null;
		this.primeiroNome = primeiroNome;
		this.sobrenome = sobrenome;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getPrimeiroNome()
	{
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome)
	{
		this.primeiroNome = primeiroNome;
	}

	public String getSobrenome()
	{
		return sobrenome;
	}

	public void setSobrenome(String sobrenome)
	{
		this.sobrenome = sobrenome;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, primeiroNome, sobrenome);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id) && Objects.equals(primeiroNome, other.primeiroNome)
				&& Objects.equals(sobrenome, other.sobrenome);
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Pessoa [id=");
		builder.append(id);
		builder.append(", primeiroNome=");
		builder.append(primeiroNome);
		builder.append(", sobrenome=");
		builder.append(sobrenome);
		builder.append("]");
		return builder.toString();
	}
}
