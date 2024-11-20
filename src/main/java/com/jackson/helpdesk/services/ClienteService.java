package com.jackson.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackson.helpdesk.domain.Pessoa;
import com.jackson.helpdesk.domain.Cliente;
import com.jackson.helpdesk.domain.dtos.ClienteDTO;
import com.jackson.helpdesk.repositories.PessoaRepository;
import com.jackson.helpdesk.repositories.ClienteRepository;
import com.jackson.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.jackson.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class ClienteService {

		@Autowired
		private ClienteRepository repository;
		@Autowired
		private PessoaRepository pessoaRepository;
		
		public Cliente findById(Integer id) {
			Optional<Cliente> obj = repository.findById(id);
			return obj.orElseThrow(() -> new ObjectnotFoundException("O Objeto não foi encontrado! Id: " + id));
		}

		public List<Cliente> findAll() {
			return repository.findAll();
		}

		public Cliente create(ClienteDTO objDTO) {
			objDTO.setId(null);
			validaPorCpfEEmail(objDTO);
			Cliente newObj = new Cliente(objDTO);
			return repository.save(newObj);
		}
		
		public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
			objDTO.setId(id);
			Cliente oldObj = findById(id);
			validaPorCpfEEmail(objDTO);
			oldObj = new Cliente(objDTO);
			return repository.save(oldObj); 
		}
		
		public void delete(Integer id) {
			Cliente obj = findById(id);
			if (obj.getChamados().size() > 0) {
				throw new DataIntegrityViolationException("Cliente possui ordens de serviços e não pode ser deletado");
			} 
				repository.deleteById(id);
			
		}
 
		private void validaPorCpfEEmail(ClienteDTO objDTO) {
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