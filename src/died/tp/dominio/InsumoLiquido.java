package died.tp.dominio;

public class InsumoLiquido extends Insumo{

	//Atributos
	private Double densidad;

	
	//Constructor
	public InsumoLiquido(String d, String u, Integer c, Double den) {
		super(d, u, c);
		this.densidad = den;
	}
	
	public InsumoLiquido() {};
	

	//Gettters y Setters
	public Double getDensidad() {
		return densidad;
	}

	public void setDensidad(Double densidad) {
		this.densidad = densidad;
	}
	
	
	//Métodos
	@Override
	public Integer pesoPorUnidad() {
		return null;
	}


	@Override
	public boolean esLiquido() {
		return true;
	}


	@Override
	public boolean esGeneral() {
		return false;
	}
	
	@Override
	public void setPesoDensidad(Double d) {
		this.densidad = d;

	}

	@Override
	public Double getPesoDensidad() {
		return this.densidad;
	}
	
}
