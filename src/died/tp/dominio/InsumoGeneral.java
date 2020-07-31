package died.tp.dominio;

public class InsumoGeneral extends Insumo{

	//Atributos
	private Double peso;
	
	
	//Constructos
	public InsumoGeneral(String d, String u, Integer c, Double p) {
		super(d, u, c);
		this.peso = p;
	}
	
	public InsumoGeneral() {};


	//Getters y Setters
	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	
	//Métodos
	@Override
	public Integer pesoPorUnidad() {
		return null;
	}


	@Override
	public boolean esLiquido() {
		return false;
	}


	@Override
	public boolean esGeneral() {
		return true;
	}

	@Override
	public void setPesoDensidad(Double peso) {
		this.peso = peso;

	}

	@Override
	public Double getPesoDensidad() {
		return this.peso;
	}
	
}
