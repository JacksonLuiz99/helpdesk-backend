package com.jackson.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jackson.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>  {

}
