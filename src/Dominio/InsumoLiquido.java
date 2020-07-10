package Dominio;

public class InsumoLiquido extends Insumo{

	private Integer densidad;

	public InsumoLiquido(String d, String u, Integer c, Integer den) {
		super(d, u, c);
		this.densidad = den;
	}
	
	@Override
	public Integer pesoPorUnidad() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	//Gettters and setters
	public Integer getDensidad() {
		return densidad;
	}

	public void setDensidad(Integer densidad) {
		this.densidad = densidad;
	}
	
	
	
}
