package com.teste.demo.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.teste.demo.dto.PessoaDto;
import com.teste.demo.form.AtualizacaoPessoaForm;
import com.teste.demo.form.PessoaForm;
import com.teste.demo.model.Pessoa;
import com.teste.demo.repository.PessoaRepository;
import com.teste.demo.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PessoaController {
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public Page<PessoaDto> getPessoas(@RequestParam(required = false) String cpf, @RequestParam(required = false) String nome, @PageableDefault(sort = "id", direction = Direction.ASC) Pageable paginacao){
		return pessoaService.getPessoas(cpf, nome, paginacao);
	}
	
	@GetMapping("/pesoIdeal/{id}")
	public ResponseEntity<?> getPesoIdealPessoa(@PathVariable("id") Long codigo){
		return pessoaService.calculaPesoIdealPessoa(codigo);
	}
	
    @PostMapping
    public ResponseEntity<?> cadastrarPessoa(@RequestBody @Valid PessoaForm pessoaForm, UriComponentsBuilder uriComponentsBuilder){
    	return pessoaService.savePessoa(pessoaForm, uriComponentsBuilder);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> getPesssoaById(@PathVariable("id") Long codigo){
    	return pessoaService.getPessoaById(codigo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PessoaDto> atualizarPessoaById(@PathVariable Long id, @RequestBody @Valid AtualizacaoPessoaForm atualizacaoPessoaForm){
    	return pessoaService.atualizarPessoaById(id, atualizacaoPessoaForm);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable Long id){
    	return pessoaService.deleteById(id);
    }
}
