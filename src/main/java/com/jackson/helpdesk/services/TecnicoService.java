package com.jackson.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackson.helpdesk.domain.Pessoa;
import com.jackson.helpdesk.domain.Tecnico;
import com.jackson.helpdesk.domain.dtos.TecnicoDTO;
import com.jackson.helpdesk.repositories.PessoaRepository;
import com.jackson.helpdesk.repositories.TecnicoRepository;
import com.jackson.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.jackson.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {

		@Autowired
		private TecnicoRepository repository;
		@Autowired
		private PessoaRepository pessoaRepository;
		
		public Tecnico findById(Integer id) {
			Optional<Tecnico> obj = repository.findById(id);
			return obj.orElseThrow(() -> new ObjectnotFoundException("O Objeto não foi encontrado! Id: " + id));
		}

		public List<Tecnico> findAll() {
			return repository.findAll();
		}

		public Tecnico create(TecnicoDTO objDTO) {
			objDTO.setId(null);
			validaPorCpfEEmail(objDTO);
			Tecnico newObj = new Tecnico(objDTO);
			return repository.save(newObj);
		}
 
		private void validaPorCpfEEmail(TecnicoDTO objDTO) {
			Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
			if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
				throw new DataIntegrityViolationException("Este CPF já está cadastrado no sistema, por favor insira um novo CPF!");
			}
			
			obj = pessoaRepository.findByEmail(objDTO.getEmail());
			if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
				throw new DataIntegrityViolationException("Este E-mail já está cadastrado no sistema, por favor insira um novo E-mail!");
			}
		}
}
