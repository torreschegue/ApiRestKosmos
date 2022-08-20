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
import com.apirestkosmos.kosmos.exception.ResourceNotFoundException;
import com.apirestkosmos.kosmos.repository.EspecialidadRepository;

@RestController
@RequestMapping("/api/v1")
public class EspecialidadController {

	@Autowired
	private EspecialidadRepository especialidadRepository;
	
	
	@GetMapping("/especialidades")
	public List<Especialidad> getAllEspecialidades(){
		return especialidadRepository.findAll();
	}
	
	@GetMapping("/especialidades/{id}")
    public ResponseEntity<Especialidad> getEspacialidadById(@PathVariable(value = "id") Long especialidadId)
    throws ResourceNotFoundException {
		Especialidad especialidad = especialidadRepository.findById(especialidadId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + especialidadId));
        return ResponseEntity.ok().body(especialidad);
    }
	
	@PostMapping("/especialidades")
    public Especialidad createEspacialidad(@Valid @RequestBody Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }
	
	@PutMapping("/especialidades/{id}")
	public ResponseEntity <Especialidad> updateEspecialidad(@PathVariable(value = "id") Long especialidadId,
			@Valid @RequestBody Especialidad especialidadDetalle) throws ResourceNotFoundException{
		Especialidad especialidad = especialidadRepository.findById(especialidadId)
				.orElseThrow(() -> new ResourceNotFoundException("Especialidad not found for this id :: " + especialidadId));
		especialidad.setEspecialidad(especialidadDetalle.getEspecialidad());
		
		Especialidad updateEspecialidad =  especialidadRepository.save(especialidad);
		return ResponseEntity.ok(updateEspecialidad);
		
	}
	
	@DeleteMapping("/especialidad/{id}")
	public Map< String, Boolean > deleteEspecialidad(@PathVariable(value = "id") Long especialidadId)
	throws ResourceNotFoundException {
		Especialidad especialidad = especialidadRepository.findById(especialidadId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + especialidadId));
		
		especialidadRepository.delete(especialidad);
		Map < String, Boolean > response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
        return response;
	}
	
	
}
