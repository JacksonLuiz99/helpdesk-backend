package com.jackson.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jackson.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>  {

}
