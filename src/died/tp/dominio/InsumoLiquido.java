package died.tp.dominio;

public class InsumoLiquido extends Insumo{

	//Atributos
	private Integer densidad;

	
	//Constructor
	public InsumoLiquido(String d, String u, Integer c, Integer den) {
		super(d, u, c);
		this.densidad = den;
	}
	

	//Gettters y Setters
	public Integer getDensidad() {
		return densidad;
	}

	public void setDensidad(Integer densidad) {
		this.densidad = densidad;
	}
	
	
	//Métodos
	@Override
	public Integer pesoPorUnidad() {
		return null;
	}
	
}
