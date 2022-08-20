package com.apirestkosmos.kosmos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apirestkosmos.kosmos.etitty.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{

}
