package com.jackson.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackson.helpdesk.domain.Tecnico;
import com.jackson.helpdesk.repositories.TecnicoRepository;
import com.jackson.helpdesk.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {

		@Autowired
		private TecnicoRepository repository;
		
		public Tecnico findById(Integer id) {
			Optional<Tecnico> obj = repository.findById(id);
			return obj.orElseThrow(() -> new ObjectnotFoundException("O Objeto não foi encontrado! Id: " + id));
		}
}
