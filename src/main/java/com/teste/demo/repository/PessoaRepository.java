package com.teste.demo.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.demo.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	Optional<Pessoa> findByCpf(String cpf);
	Page<Pessoa> findByCpf(String cpf, Pageable paginacao);
}
