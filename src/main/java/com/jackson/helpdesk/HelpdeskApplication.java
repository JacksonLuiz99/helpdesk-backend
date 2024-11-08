package com.jackson.helpdesk;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jackson.helpdesk.domain.Chamado;
import com.jackson.helpdesk.domain.Cliente;
import com.jackson.helpdesk.domain.Tecnico;
import com.jackson.helpdesk.domain.enums.Perfil;
import com.jackson.helpdesk.domain.enums.Prioridade;
import com.jackson.helpdesk.domain.enums.Status;
import com.jackson.helpdesk.repositories.ChamadoRepository;
import com.jackson.helpdesk.repositories.ClienteRepository;
import com.jackson.helpdesk.repositories.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Tecnico tec1 = new Tecnico(null,
				"Jackson", 
				"07717556982", 
				"jackson@email.com", 
				"123");
				tec1.addPerfil(Perfil.ADMIN);
				
		Cliente cli1 = new Cliente(null,
				"Luiz",
				"46685156194",
				"luiz@gmail.com",
				"123");
		
		Chamado c1 = new Chamado(null,
				Prioridade.MEDIA,
				Status.ANDAMENTO, 
				"Chamado 01", 
				"Primeiro chmado",
				tec1,
				cli1);
		
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}

}
