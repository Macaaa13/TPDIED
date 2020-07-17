package died.tp.dominio;

import java.time.*;

public class Camion {

	//Atributos
	private Integer id;
	private String patente;
	private Integer modelo;
	private String marca;
	private Integer kmRecorridos;
	private Double costoKM;
	private Double costoHora;
	private LocalDate fechaCompra;
	
	
	// Getters y Setters
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
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
	
	public Double getCostoHora() {
		return costoHora;
	}
	
	public void setCostoHora(Double costoHora) {
		this.costoHora = costoHora;
	}
	
	public LocalDate getFechaCompra() {
		return fechaCompra;
	}
	
	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	
}
