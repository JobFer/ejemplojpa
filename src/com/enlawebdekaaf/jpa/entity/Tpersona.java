package com.enlawebdekaaf.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="tpersona")
@NamedQueries
({
	@NamedQuery(name="Tpersona.findAll", query="SELECT t FROM Tpersona t"),
	@NamedQuery(name="Tpersona.findByDocumentoIdentidad", query="SELECT t FROM Tpersona t WHERE t.documentoIdentidad = :parametroDocumentoIdentidad")
})
public class Tpersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(insertable=false, updatable=false, nullable=false)
	private int idPersona;
	private String correoElectronico;
	private String documentoIdentidad;
	private boolean estado;
	private String fechaNacimiento;
	private String fechaRegistro;
	private String nombre;

	public Tpersona() {
	}

	public Tpersona(int idPersona, String correoElectronico, String documentoIdentidad, boolean estado,
			String fechaNacimiento, String fechaRegistro, String nombre) {
		super();
		this.idPersona = idPersona;
		this.correoElectronico = correoElectronico;
		this.documentoIdentidad = documentoIdentidad;
		this.estado = estado;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaRegistro = fechaRegistro;
		this.nombre = nombre;
	}
	
	public Tpersona(int idPersona) {
		super();
		this.idPersona = idPersona;
	}

	public int getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getDocumentoIdentidad() {
		return this.documentoIdentidad;
	}

	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}

	public boolean getEstado() {
		return this.estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Tpersona [idPersona=" + idPersona + ", correoElectronico=" + correoElectronico + ", documentoIdentidad="
				+ documentoIdentidad + ", estado=" + estado + ", fechaNacimiento=" + fechaNacimiento
				+ ", fechaRegistro=" + fechaRegistro + ", nombre=" + nombre + "]";
	}

	
}