package com.jackson.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jackson.helpdesk.domain.Chamado;
import com.jackson.helpdesk.domain.Cliente;
import com.jackson.helpdesk.domain.Tecnico;
import com.jackson.helpdesk.domain.enums.Perfil;
import com.jackson.helpdesk.domain.enums.Prioridade;
import com.jackson.helpdesk.domain.enums.Status;
import com.jackson.helpdesk.repositories.ChamadoRepository;
import com.jackson.helpdesk.repositories.ClienteRepository;
import com.jackson.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public void instanciaDB() {
		
		Tecnico tec1 = new Tecnico(null, "Jackson", "31874636737", "jackson@uol.com", "123");
		tec1.addPerfil(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Scooby-Doo", "17111468708", "scooby.doo@hotmail.com", "123");
		Tecnico tec3 = new Tecnico(null, "Fred Flintstone", "92541720491", "fred.flintstone@gmail.com", "123");
		Tecnico tec4 = new Tecnico(null, "Homer Simpson", "28964101391", "homer.simpson@outlook.com", "123");
		Tecnico tec5 = new Tecnico(null, "Bart Simpson", "94829299428", "bart.simpson@yahoo.com", "123");
		Tecnico tec6 = new Tecnico(null, "Tom (Tom & Jerry)", "73175110053", "tom.jerry@icloud.com", "123");
		Tecnico tec7 = new Tecnico(null, "Jerry (Tom & Jerry)", "37118663360", "jerry.jerry@gmail.com", "123");
		Tecnico tec8 = new Tecnico(null, "Pikachu", "76949366060", "pikachu@outlook.com", "123");
		Tecnico tec9 = new Tecnico(null, "Bugs Bunny", "32165498765", "bugs.bunny@uol.com", "123");
		Tecnico tec10 = new Tecnico(null, "Daffy Duck", "01926762703", "daffy.duck@hotmail.com", "123");
		
		Cliente cli1 = new Cliente(null, "Luiz", "10414231104", "luiz@gmail.com", "123");
		Cliente cli2 = new Cliente(null, "Maria", "67442322310", "maria@outlook.com", "123");
		Cliente cli3 = new Cliente(null, "João", "48911372749", "joao@yahoo.com", "123");
		Cliente cli4 = new Cliente(null, "Mickey Mouse", "80523674686", "mickey.mouse@gmail.com", "123");
		Cliente cli5 = new Cliente(null, "Minnie Mouse", "50865652279", "minnie.mouse@uol.com", "123");
		Cliente cli6 = new Cliente(null, "Donald Duck", "61644636298", "donald.duck@icloud.com", "123");
		Cliente cli7 = new Cliente(null, "Goofy", "05713872688", "goofy@outlook.com", "123");
		Cliente cli8 = new Cliente(null, "Kalil Baract", "70915212340", "kalil.baract@gmail.com", "123");
		Cliente cli9 = new Cliente(null, "Tom Cat", "68885048676", "tom.cat@yahoo.com", "123");
		Cliente cli10 = new Cliente(null, "Jerry Mouse", "76263146893", "jerry.mouse@bol.com.br", "123");


		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
		Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ENCERRADO, "Chamado 02", "Problema no sistema", tec2, cli2);
		Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Chamado 03", "Verificação de rede", tec3, cli3);
		Chamado c4 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 04", "Erro no software", tec4, cli4);
		Chamado c5 = new Chamado(null, Prioridade.ALTA, Status.ENCERRADO, "Chamado 05", "Falha no servidor", tec5, cli5);
		Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Chamado 06", "Erro na impressão", tec6, cli6);
		Chamado c7 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 07", "Problema na rede Wi-Fi", tec7, cli7);
		Chamado c8 = new Chamado(null, Prioridade.ALTA, Status.ENCERRADO, "Chamado 08", "Erro de login", tec8, cli8);
		Chamado c9 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Chamado 09", "Problema no acesso ao banco de dados", tec9, cli9);
		Chamado c10 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 10", "Verificação de segurança", tec10, cli10);

		tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, tec6, tec7, tec8, tec9, tec10));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6, cli7, cli8, cli9, cli10));
		chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10));
	}
}
