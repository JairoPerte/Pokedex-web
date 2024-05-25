package com.jairopertegal.pokemonweb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pokemon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long numpokedex;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "tipo1")
	private String tipo1;
	@Column(name = "tipo2", nullable = true)
	private String tipo2;
	@Column(name = "habilidad")
	private String habilidad;

	/**
	 * 
	 */
	public Pokemon() {
		super();
	}

	/**
	 * @param numPokedex
	 * @param nombre
	 * @param descripcion
	 * @param tipo1
	 * @param tipo2
	 * @param habilidad
	 */
	public Pokemon(long numPokedex, String nombre, String descripcion, String tipo1, String tipo2, String habilidad) {
		super();
		this.numpokedex = numPokedex;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
		this.habilidad = habilidad;
	}

	/**
	 * @return the numPokedex
	 */
	public long getNumPokedex() {
		return numpokedex;
	}

	/**
	 * @param numPokedex the numPokedex to set
	 */
	public void setNumPokedex(long numPokedex) {
		this.numpokedex = numPokedex;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the tipo1
	 */
	public String getTipo1() {
		return tipo1;
	}

	/**
	 * @param tipo1 the tipo1 to set
	 */
	public void setTipo1(String tipo1) {
		this.tipo1 = tipo1;
	}

	/**
	 * @return the tipo2
	 */
	public String getTipo2() {
		return tipo2;
	}

	/**
	 * @param tipo2 the tipo2 to set
	 */
	public void setTipo2(String tipo2) {
		this.tipo2 = tipo2;
	}

	/**
	 * @return the habilidad
	 */
	public String getHabilidad() {
		return habilidad;
	}

	/**
	 * @param habilidad the habilidad to set
	 */
	public void setHabilidad(String habilidad) {
		this.habilidad = habilidad;
	}
}
