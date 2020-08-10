package died.tp.dominio;

public class Ruta {

	//Atributos
	private Planta origen;
	private Planta destino;
	private Integer distancia;
	private Double duracionEstimada;
	private Integer pesoMax;
	
	
	//Constructores
	public Ruta() {}

	public Ruta(Planta origen, Planta destino, Integer dist, Double durac, Integer pesoM) {
		this.origen = origen;
		this.destino = destino;
		this.distancia = dist;
		this.duracionEstimada = durac;
		this.pesoMax = pesoM;
	}
	
	
	//Getters y Setters
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
