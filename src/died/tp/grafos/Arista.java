package died.tp.grafos;

import java.util.Random;

public class Arista<T> {
	
	//Atributos
	private Vertice<T> inicio;
	private Vertice<T> fin;
	private Integer distancia;
	private Double duracionEstimada;
	private Integer pesoMax;
	
	
	//Constructores
	public Arista(){
		
	} 
	
	public Arista(Vertice<T> ini, Vertice<T> fin, Integer dist, Double durac, Integer pesoM){
		this.inicio = ini;
		this.fin = fin;
		distancia = dist;
		duracionEstimada = durac;
		pesoMax = pesoM;
	}
	
	
	//Getters y Setters
	public Vertice<T> getInicio() {
		return inicio;
	}
	
	public void setInicio(Vertice<T> inicio) {
		this.inicio = inicio;
	}
	
	public Vertice<T> getFin() {
		return fin;
	}
	
	public void setFin(Vertice<T> fin) {
		this.fin = fin;
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

	
	//Métodos
	@Override
	public String toString() {
		return "( "+this.inicio.getValor()+" --> "+this.fin.getValor()+" )";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Arista<?>) && 
			   ((Arista<?>)obj).getDistancia().equals(this.getDistancia()) &&
			   ((Arista<?>)obj).getDuracionEstimada().equals(this.getDuracionEstimada()) &&
			   ((Arista<?>)obj).getPesoMax().equals(this.getPesoMax());
	}
}
