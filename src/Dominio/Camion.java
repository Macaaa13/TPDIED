package Dominio;

import java.util.Date;

public class Camion {

	//Atributos
	private String patente;
	private Integer modelo;
	private String marca;
	private Integer kmRecorridos;
	private Double costoKM;
	private Date fechaCompra;
	
	//Constructor
	public Camion(String patente, Integer modelo, String marca, Integer kmRecorridos, Double costoKM,Date fechaCompra) {
		this.patente = patente;
		this.modelo = modelo;
		this.marca = marca;
		this.kmRecorridos = kmRecorridos;
		this.costoKM = costoKM;
		this.fechaCompra = fechaCompra;
	}

	
	//Metodos
	
	
	// Getters and setters
	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public Integer getModelo() {
		return modelo;
	}

	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getKmRecorridos() {
		return kmRecorridos;
	}

	public void setKmRecorridos(Integer kmRecorridos) {
		this.kmRecorridos = kmRecorridos;
	}

	public Double getCostoKM() {
		return costoKM;
	}

	public void setCostoKM(Double costoKM) {
		this.costoKM = costoKM;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	
	
}
