package br.ufrn.imd.pessoa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.imd.pessoa.entity.Pessoa;
import br.ufrn.imd.pessoa.repository.PessoaRepository;

@Service
public class PessoaService
{

	@Autowired
	PessoaRepository repository;

	public Pessoa save(Pessoa pessoa)
	{
		// Lógica confusa só para ter o que testar: aqui misturam-se save e update
		// Uma implementação real não seria assim
		if (pessoa.getId() == null)
		{
			// Aqui salva um novo
			pessoa = repository.save(pessoa);
			return pessoa;
		}
		else
		{
			// Aqui atualiza um que já existe
			Optional<Pessoa> pessoaOptional = repository.findById(pessoa.getId());

			if (pessoaOptional.isPresent())
			{

				Pessoa novaPessoa = pessoaOptional.get();
				novaPessoa.setPrimeiroNome(pessoa.getPrimeiroNome());
				novaPessoa.setSobrenome(pessoa.getSobrenome());

				novaPessoa = repository.save(novaPessoa);

				return novaPessoa;
			}
			else
			{
				throw new EntityNotFoundException("Não existe pessoa com esse id.");
			}
		}
	}

	public List<Pessoa> findAll()
	{
		List<Pessoa> resultado = (List<Pessoa>) repository.findAll();

		// Alguma lógica aqui só para ter algo para testar
		if (resultado.size() > 0)
		{
			return resultado;
		}
		else
		{
			return new ArrayList<Pessoa>();
		}
	}

	public void deleteById(Long id)
	{
		Optional<Pessoa> pessoa = repository.findById(id);
		repository.findById(id);
		if (pessoa.isPresent())
		{
			repository.deleteById(id);
			repository.deleteById(id);
		}
		else
		{
			throw new EntityNotFoundException("Não existe pessoa com esse id.");
		}
	}

	void deleteAll()
	{
		repository.deleteAll();
	}
}
