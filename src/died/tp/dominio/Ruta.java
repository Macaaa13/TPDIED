package died.tp.dominio;

import java.text.DecimalFormat;
import java.util.*;

public class Ruta {

	//Atributos
	private Integer id;
	private Planta origen;
	private Planta destino;
	private Integer distancia;
	private Double duracionEstimada;
	private Integer pesoMax;
	
	//Constructores
	public Ruta() {}

	public Ruta(Planta origen, Planta destino) {
		this.origen = origen;
		this.destino = destino;
		Random r = new Random();
		this.distancia = r.nextInt(300)+15;
		this.duracionEstimada = distancia/60.0;
		this.pesoMax = r.nextInt(500)+50;
	}
	
	//Getters y Setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	public Double getDuracionEstimada() {
		return duracionEstimada;
	}

	public void setDuracionEstimada(Double duracionEstimada) {
		this.duracionEstimada = duracionEstimada;
	}

	public Integer getPesoMax() {
		return pesoMax;
	}

	public void setPesoMax(Integer pesoMax) {
		this.pesoMax = pesoMax;
	}
	
}
