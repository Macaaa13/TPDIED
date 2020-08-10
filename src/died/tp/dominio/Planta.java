package died.tp.dominio;

import java.util.*;

public class Planta {

	//Atributos
	private Integer id;
	private String nombrePlanta;
	private List<Stock> stockInsumos;
	
	
	//Constructor
	public Planta (String np) {
		this.nombrePlanta = np;
		stockInsumos = new ArrayList<Stock>();
	}
	
	public Planta() { }

	
	//Getters y Setters
	public String getNombrePlanta() {
		return nombrePlanta;
	}

	public void setNombrePlanta(String nombrePlanta) {
		this.nombrePlanta = nombrePlanta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Stock> getStockInsumos() {
		return stockInsumos;
	}

	public void setStockInsumos(List<Stock> stockInsumos) {
		this.stockInsumos = stockInsumos;
	}
	
	
	//Métodos
	@Override
	public boolean equals(Object o) {
		if(o instanceof Planta) {
			Planta p = (Planta) o;
			if(this.getId().equals(p.getId())) return true;
		}
		return false;
	}
}
