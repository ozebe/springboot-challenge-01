package com.teste.demo.form;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.teste.demo.model.Pessoa;
import com.teste.demo.repository.PessoaRepository;


//exemplo de form para atualização, onde o CPF não pode ser alterado após o cadastro, servindo igualmente para outros campos da tabela
public class AtualizacaoPessoaForm {
	@NotBlank
	@NotEmpty
	@Size(min = 2)
	private String nome;
	
	@NotNull
	private Date data_nasc;
	
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
	
	public Pessoa atualizar(Long id, PessoaRepository pessoaRepository) {
	    Pessoa pessoa = pessoaRepository.getById(id);
	    pessoa.setNome(this.nome);
	    pessoa.setData_nasc(this.data_nasc);
	    pessoa.setSexo(this.sexo);
	    pessoa.setAltura(this.altura);
	    pessoa.setPeso(this.peso);
	    return pessoa;
	}
}
