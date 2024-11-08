package com.jackson.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jackson.helpdesk.domain.Pessoa;

public interface ChamadoRepository extends JpaRepository<Pessoa, Integer>  {

}
