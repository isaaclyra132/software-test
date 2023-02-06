package br.ufrn.imd.pessoa.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.pessoa.entity.Pessoa;
import br.ufrn.imd.pessoa.service.PessoaService;

@RestController
public class PessoaController
{
	@Autowired
	PessoaService pessoaService;

	@PostMapping("/pessoa")
	public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa)
	{
		try
		{
			Pessoa pessoaSalva = pessoaService.save(pessoa);
			return new ResponseEntity<Pessoa>(pessoaSalva, HttpStatus.CREATED);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
		}
		catch (Exception e)
		{
			return new ResponseEntity<Pessoa>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/pessoa")
	public ResponseEntity<List<Pessoa>> findAll()
	{
		List<Pessoa> todasPessoas = pessoaService.findAll();
		if (todasPessoas.isEmpty())
		{
			return new ResponseEntity<List<Pessoa>>(todasPessoas, HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<List<Pessoa>>(todasPessoas, HttpStatus.OK);
		}
	}

	@PutMapping("/pessoa")
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa)
	{
		try
		{
			Pessoa pessoaSalva = pessoaService.save(pessoa);
			return new ResponseEntity<Pessoa>(pessoaSalva, HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/pessoa/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id)
	{
		try
		{
			pessoaService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch (EntityNotFoundException e)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
