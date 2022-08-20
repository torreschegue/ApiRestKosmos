package com.apirestkosmos.kosmos.etitty;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name = "Doctores")
public class Doctor {
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_doctor")
	private Long id;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	
	// Estas Entidad Sera para una Relacion
	//private Especialidad especialidadId;
	
}
