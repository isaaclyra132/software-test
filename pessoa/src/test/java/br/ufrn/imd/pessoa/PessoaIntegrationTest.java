package br.ufrn.imd.pessoa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufrn.imd.pessoa.controller.PessoaController;
import br.ufrn.imd.pessoa.entity.Pessoa;
import br.ufrn.imd.pessoa.repository.PessoaRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PessoaIntegrationTest
{
	@Autowired
	PessoaController controller;

	@Autowired
	TestRestTemplate api;

	@Autowired
	PessoaRepository repository;

	@Value("${local.server.port}")
	private int port;

	@BeforeEach
	public void setUp()
	{
//		List<Pessoa> all = new ArrayList<>();
//
//		all.add(new Pessoa("A", "B"));
//		all.add(new Pessoa("A", "B"));
//		all.add(new Pessoa("A", "B"));
//		all.add(new Pessoa("A", "B"));
//		all.add(new Pessoa("A", "B"));
//
//		repository.saveAll(all);
	}

	@AfterEach
	public void cleanUp()
	{
		repository.deleteAll();
	}

	@Test
	public void testeControllerDireto()
	{
		Pessoa pessoa = new Pessoa("Joao", "Souza");

		ResponseEntity<Pessoa> response = this.controller.create(pessoa);
		Pessoa pessoaCriada = response.getBody();

		assertEquals(pessoa.getPrimeiroNome(), pessoaCriada.getPrimeiroNome());
		assertEquals(pessoa.getSobrenome(), pessoaCriada.getSobrenome());

		ResponseEntity<List<Pessoa>> responseFindAll = this.controller.findAll();

		List<Pessoa> todasPessoas = responseFindAll.getBody();

		assertEquals(1, todasPessoas.size());

		Pessoa primeiraPessoa = todasPessoas.get(0);

		assertEquals(primeiraPessoa.getPrimeiroNome(), pessoaCriada.getPrimeiroNome());
		assertEquals(primeiraPessoa.getSobrenome(), pessoaCriada.getSobrenome());
	}

	@Test
	public void testeViaApi() throws Exception
	{
		String endereco = "http://localhost:" + port + "/pessoa";

		Pessoa pessoa = new Pessoa("José", "Silva");

		var responsePost = api.postForEntity(endereco, pessoa, Pessoa.class);

		assertEquals(HttpStatus.CREATED, responsePost.getStatusCode());

		var responsePost2 = api.postForEntity(endereco, new Pessoa("A", "B"), Pessoa.class);

		assertEquals(HttpStatus.CREATED, responsePost2.getStatusCode());

		var responseGet = api.getForEntity(endereco, List.class);

		var todasPessoas = responseGet.getBody();

		assertEquals(2, todasPessoas.size());
	}

}
