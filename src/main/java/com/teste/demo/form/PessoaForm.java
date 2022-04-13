package com.teste.demo.form;

import java.util.Date;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.teste.demo.model.Pessoa;
import com.teste.demo.repository.PessoaRepository;

public class PessoaForm {
	
	@NotBlank
	@NotEmpty
	@Size(min = 2)
	private String nome;
	
	@NotNull
	private Date data_nasc;
	
	
	@NotBlank
	@NotEmpty
	@Size(min = 11, max = 11)
	private String cpf;
	
	private char sexo;
	
	private Double altura;
	
	private Double peso;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData_nasc() {
		return data_nasc;
	}

	public void setData_nasc(Date data_nasc) {
		this.data_nasc = data_nasc;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	public Pessoa converter(PessoaRepository pessoaRepository) {
	    
	    return new Pessoa(nome,data_nasc, cpf, sexo, altura, peso);
	}
	

}
