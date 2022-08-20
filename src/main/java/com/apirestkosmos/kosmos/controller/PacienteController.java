package com.apirestkosmos.kosmos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirestkosmos.kosmos.etitty.Especialidad;
import com.apirestkosmos.kosmos.etitty.Paciente;
import com.apirestkosmos.kosmos.exception.ResourceNotFoundException;
import com.apirestkosmos.kosmos.repository.PacienteRepository;

@RestController
@RequestMapping("/api/v1")
public class PacienteController {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@PostMapping("/pacientes")
    public Paciente createPacientes(@Valid @RequestBody Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

	
	@GetMapping("/pacientes")
	public List<Paciente> getAllPacientes(){
		return pacienteRepository.findAll();
	}
	
	@GetMapping("/pacientes/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable(value = "id") Long pacienteId)
    throws ResourceNotFoundException {
		Paciente paciente = pacienteRepository.findById(pacienteId)
            .orElseThrow(() -> new ResourceNotFoundException("Paciente not found for this id :: " + pacienteId));
        return ResponseEntity.ok().body(paciente);
    }
	
	@PutMapping("/pacientes/{id}")
	public ResponseEntity <Paciente> updatePaciente(@PathVariable(value = "id") Long pacienteId,
			@Valid @RequestBody Paciente pacienteDetalle) throws ResourceNotFoundException{
		Paciente paciente = pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente not found for this id :: " + pacienteId));
		
		paciente.setNombre(pacienteDetalle.getNombre());
		paciente.setApellidoPaterno(pacienteDetalle.getApellidoPaterno());
		paciente.setApellidoMaterno(pacienteDetalle.getApellidoMaterno());
		paciente.setEdad(pacienteDetalle.getEdad());
		paciente.setTelefono(pacienteDetalle.getTelefono());
			
		Paciente updatePaciente =  pacienteRepository.save(paciente);
		return ResponseEntity.ok(updatePaciente);
		
	}
	
	@DeleteMapping("/paciente/{id}")
	public Map< String, Boolean > deletePaciente(@PathVariable(value = "id") Long pacienteId)
	throws ResourceNotFoundException {
		Paciente paciente = pacienteRepository.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente not found for this id :: " + pacienteId));
		
		pacienteRepository.delete(paciente);
		Map < String, Boolean > response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
        return response;
	}
	
	
	
	
}
