package br.ufrn.imd.pessoa.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.ufrn.imd.pessoa.entity.Pessoa;
import br.ufrn.imd.pessoa.service.PessoaService;

@ExtendWith(MockitoExtension.class)
class PessoaControllerTest
{

	@InjectMocks
	PessoaController controller;

	@Mock
	PessoaService service;

	@Test
	void testeBasico()
	{
		assertNotNull(controller);
		assertNotNull(service);
	}

	@Test
	void testeUpdate_PessoaNaoEncontrada()
	{
		when(service.save(any())).thenThrow(new EntityNotFoundException());

		Pessoa pessoa = new Pessoa();
		ResponseEntity<Pessoa> response = controller.update(pessoa);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());
	}

}
