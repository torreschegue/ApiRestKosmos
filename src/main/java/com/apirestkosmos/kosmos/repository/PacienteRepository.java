package com.apirestkosmos.kosmos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirestkosmos.kosmos.etitty.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{

}
