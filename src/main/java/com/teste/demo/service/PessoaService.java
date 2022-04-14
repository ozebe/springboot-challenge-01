package com.teste.demo.service;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.teste.demo.dto.PessoaDto;
import com.teste.demo.form.AtualizacaoPessoaForm;
import com.teste.demo.form.PessoaForm;
import com.teste.demo.model.Pessoa;
import com.teste.demo.repository.PessoaRepository;

@Service
public class PessoaService {
	final PessoaRepository pessoaRepository;

	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}
	
	public Page<PessoaDto> getPessoas(String cpf, String nome, Pageable paginacao){
		if(cpf != null) {
			Page<Pessoa> pessoas = pessoaRepository.findByCpf(cpf, paginacao);
			return PessoaDto.converter(pessoas);
		}
		
		if(nome != null) {
			Page<Pessoa> pessoas = pessoaRepository.findByNomeContaining(nome, paginacao);
			return PessoaDto.converter(pessoas);
		}
		
		Page<Pessoa> pessoas = pessoaRepository.findAll(paginacao);
		return PessoaDto.converter(pessoas);
		
//		if(cpf == null) {
//			Page<Pessoa> pessoas = pessoaRepository.findAll(paginacao);
//			return PessoaDto.converter(pessoas);
//		}else {
//			Page<Pessoa> pessoas = pessoaRepository.findByCpf(cpf, paginacao);
//			return PessoaDto.converter(pessoas);
//		}
	}
	
	@Transactional
	public ResponseEntity<?> savePessoa(PessoaForm pessoaForm, UriComponentsBuilder uriComponentsBuilder) {
        Pessoa pessoa = pessoaForm.converter(pessoaRepository);
        
        //realiza uma busca pelo CPF cadatrado, caso seja encontrado, devolve um erro
        Optional<Pessoa> pessoaBusca = pessoaRepository.findByCpf(pessoa.getCpf());
        if(pessoaBusca.isPresent()) {
        	return ResponseEntity.badRequest().body("{\"erro\":\"O CPF " + pessoa.getCpf() + " já está cadastrado\"}");
        }else {
            try {
                pessoaRepository.save(pessoa);

                URI uri = uriComponentsBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
                return ResponseEntity.created(uri).body(new PessoaDto(pessoa));
            }catch(Exception e) {
            	return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
	}
	
	public ResponseEntity<PessoaDto> getPessoaById(Long id){
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if(pessoa.isPresent()){
            return ResponseEntity.ok(new PessoaDto(pessoa.get()));
        }
        return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<?> calculaPesoIdealPessoa(Long id){
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        double pesoIdeal;
        if(pessoa.isPresent()){
        	if(pessoa.get().getSexo() == 'M') {
        		pesoIdeal = (pessoa.get().getPeso() * pessoa.get().getAltura()) - 58;
        	}else {
        		pesoIdeal = (pessoa.get().getPeso() * pessoa.get().getAltura()) - 44.7;
        	}
    		return ResponseEntity.ok("{\"pesoIdeal\":" +"\"" + pesoIdeal + "\""+  "}");
        }
        return ResponseEntity.notFound().build();	
	}
	
	@Transactional
	public ResponseEntity<PessoaDto> atualizarPessoaById(Long id, AtualizacaoPessoaForm atualizacaoPessoaForm){
        Optional<Pessoa> optional = pessoaRepository.findById(id);
        if(optional.isPresent()){
           Pessoa pessoa= atualizacaoPessoaForm.atualizar(id, pessoaRepository);
           return ResponseEntity.ok(new PessoaDto(pessoa));
        }
        return ResponseEntity.notFound().build(); 
	}
	
	@Transactional
	public ResponseEntity deleteById(Long id) {
        Optional<Pessoa> optional = pessoaRepository.findById(id);
        if(optional.isPresent()){
            pessoaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
	}


}
