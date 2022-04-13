package com.teste.demo.dto;

import java.util.Date;

import org.springframework.data.domain.Page;

import com.teste.demo.model.Pessoa;

public class PessoaDto {
	private Long id;
	private String nome;
	private Date data_nasc;
	private String cpf;
	private char sexo;
	private Double altura;
	private Double peso;
	
	public PessoaDto(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.data_nasc = pessoa.getData_nasc();
		this.cpf = pessoa.getCpf();
		this.sexo = pessoa.getSexo();
		this.altura = pessoa.getAltura();
		this.peso = pessoa.getPeso();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Date getData_nasc() {
		return data_nasc;
	}

	public String getCpf() {
		return cpf;
	}

	public char getSexo() {
		return sexo;
	}

	public Double getAltura() {
		return altura;
	}

	public Double getPeso() {
		return peso;
	}
	
	public static Page<PessoaDto> converter(Page<Pessoa> pessoas){
		return pessoas.map(PessoaDto::new);
	}
	
}
