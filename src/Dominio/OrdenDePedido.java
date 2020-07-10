package Dominio;

import java.util.List;

public class OrdenDePedido {

	private Integer nroOrden;
	private Planta origen;
	private Planta destino;
	private enum estado {CREADA,PROCESADA,ENTREGADA,CANCELADO};
	private List<Insumo> insumos;
	private Camion camionAsignado;
	private Integer costoEnvio;
	
	public OrdenDePedido(Integer nroOrden, Planta origen, Planta destino, List<Insumo> insumos) {
		this.nroOrden = nroOrden;
		this.origen = origen;
		this.destino = destino;
		this.insumos = insumos;
		
	}

	
	
	
	//Getters and setters
	public Integer getNroOrden() {
		return nroOrden;
	}

	public void setNroOrden(Integer nroOrden) {
		this.nroOrden = nroOrden;
	}

	public Planta getOrigen() {
		return origen;
	}

	public void setOrigen(Planta origen) {
		this.origen = origen;
	}
	
	public Planta getDestino() {
		return destino;
	}

	public void setDestino(Planta destino) {
		this.destino = destino;
	}

	public List<Insumo> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<Insumo> insumos) {
		this.insumos = insumos;
	}

	public Camion getCamionAsignado() {
		return camionAsignado;
	}

	public void setCamionAsignado(Camion camionAsignado) {
		this.camionAsignado = camionAsignado;
	}

	public Integer getCostoEnvio() {
		return costoEnvio;
	}

	public void setCostoEnvio(Integer costoEnvio) {
		this.costoEnvio = costoEnvio;
	}
	
	
	
}
