package com.jackson.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		@Autowired
		private BCryptPasswordEncoder encoder;
		
		public Tecnico findById(Integer id) {
			Optional<Tecnico> obj = repository.findById(id);
			return obj.orElseThrow(() -> new ObjectnotFoundException("O Objeto não foi encontrado! Id: " + id));
		}

		public List<Tecnico> findAll() {
			return repository.findAll();
		}

		public Tecnico create(TecnicoDTO objDTO) {
			objDTO.setId(null);
			objDTO.setSenha(encoder.encode(objDTO.getSenha()));
			validaPorCpfEEmail(objDTO);
			Tecnico newObj = new Tecnico(objDTO);
			return repository.save(newObj);
		}
		
		public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
			objDTO.setId(id);
			Tecnico oldObj = findById(id);
			validaPorCpfEEmail(objDTO);
			oldObj = new Tecnico(objDTO);
			return repository.save(oldObj); 
		}
		
		public void delete(Integer id) {
			Tecnico obj = findById(id);
			if (obj.getChamados().size() > 0) {
				throw new DataIntegrityViolationException("Tecnico possui ordens de serviços e não pode ser deletado");
			} 
				repository.deleteById(id);
			
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
