package Dominio;

public class InsumoGeneral extends Insumo{

	private Integer peso;
	
	public InsumoGeneral(String d, String u, Integer c, Integer p) {
		super(d, u, c);
		this.peso = p;
	}
	
	
	@Override
	public Integer pesoPorUnidad() {
		
		return null;
	}


	
	
	//Getters and setters
	public Integer getPeso() {
		return peso;
	}


	public void setPeso(Integer peso) {
		this.peso = peso;
	}

}
