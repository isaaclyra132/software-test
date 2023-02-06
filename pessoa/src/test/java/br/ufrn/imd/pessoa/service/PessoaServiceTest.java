package br.ufrn.imd.pessoa.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.ufrn.imd.pessoa.entity.Pessoa;
import br.ufrn.imd.pessoa.repository.PessoaRepository;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest
{
	@InjectMocks
	PessoaService service;

	@Mock
	PessoaRepository repository;

	@Test
	void testeDeletarPessoaNaoEncontrada()
	{
		when(repository.findById(any())).thenReturn(Optional.empty());

		assertThrows(EntityNotFoundException.class, () ->
		{
			service.deleteById(1l);
		});

		verify(repository, times(1)).findById(1l);
	}

	@Test
	void testeDeletarPessoaEncontrada()
	{
		Optional<Pessoa> optionalPessoa = Optional.of(new Pessoa("José", "Silva"));

		when(repository.findById(any())).thenReturn(optionalPessoa);

		assertDoesNotThrow(() ->
		{
			service.deleteById(1l);
		});

		verify(repository, times(1)).findById(1l);
	}

	@Test
	void testeDeletarTudo()
	{
		assertDoesNotThrow(() ->
		{
			repository.deleteAll();
		});
		verify(repository, times(1)).deleteAll();
	}

	@Test
	void testListarTudoVazio()
	{
		List<Pessoa> resultado = new ArrayList<Pessoa>();
		when(repository.findAll()).thenReturn(resultado);
		assertDoesNotThrow(() ->
		{
			repository.findAll();
		});
		assertEquals(0, resultado.size());
		verify(repository, times(1)).findAll();
	}

	@Test
	void testListarTudo()
	{
		Pessoa p1 = new Pessoa("José", "Silva");
		Pessoa p2 = new Pessoa("José2", "Silva2");
		List<Pessoa> resultado = Arrays.asList(p1, p2);
		when(repository.findAll()).thenReturn(resultado);
		assertDoesNotThrow(() ->
		{
			repository.findAll();
		});
		assertEquals(2, resultado.size());
		verify(repository, times(1)).findAll();
	}

	@Test
	void testSalvarNovo()
	{
		Pessoa p = new Pessoa("jan", "jão");
		p.setId(null);

		when(repository.save(any())).thenReturn(p);

		assertDoesNotThrow(() ->
		{
			service.save(p);
		});

		verify(repository, times(1)).save(p);
	}

	@Test
	void testSalvarPessoaID()
	{
		Pessoa p = new Pessoa("jan", "jão");
		when(repository.save(any())).thenReturn(p);
		assertDoesNotThrow(() ->
		{
			repository.save(p);
		});
		assertEquals("jan", p.getPrimeiroNome());
		p.setPrimeiroNome("jão");
		p.setSobrenome("jan");
		assertDoesNotThrow(() ->
		{
			repository.save(p);
		});
		assertEquals("jão", p.getPrimeiroNome());
		verify(repository, times(2)).save(p);
	}

	@Test
	void testSalvarException()
	{
		Optional<Pessoa> optionalPessoa = Optional.empty();
		Pessoa p = new Pessoa("jan", "jão");
		p.setId(1l);
		when(repository.findById(any())).thenReturn(optionalPessoa);
		assertThrows(EntityNotFoundException.class, () ->
		{
			service.save(p);
		});
		verify(repository, times(1)).findById(1l);
	}

}
