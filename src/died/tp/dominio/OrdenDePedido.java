package died.tp.dominio;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrdenDePedido {

	//Atributos
	private Integer nroOrden;
	private Planta origen;
	private Planta destino;
	public enum estado {CREADA,PROCESADA,ENTREGADA,CANCELADO};
	private Map<Insumo,Integer> insumos;
	private Camion camionAsignado;
	private Integer costoEnvio;
	private LocalDate fechaEntrega;
	private estado est;
	
	//Constructor
	public OrdenDePedido(Integer nroOrden, Planta origen, Planta destino, Map<Insumo,Integer> insumos) {
		this.nroOrden = nroOrden;
		this.origen = origen;
		this.destino = destino;
		this.insumos = insumos;
		
	}

	
	public OrdenDePedido() {
		// TODO Auto-generated constructor stub
	}


	//Getters y Setters
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

	public Map<Insumo,Integer> getInsumos() {
		return insumos;
	}

	public void setInsumos(Map<Insumo,Integer> insumos) {
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


	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}


	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}


	public estado getEst() {
		return est;
	}


	public void setEst(estado est) {
		this.est = est;
	}

}