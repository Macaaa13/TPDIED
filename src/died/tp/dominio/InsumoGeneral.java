package died.tp.dominio;

public class InsumoGeneral extends Insumo{

	//Atributos
	private Integer peso;
	
	
	//Constructos
	public InsumoGeneral(String d, String u, Integer c, Integer p) {
		super(d, u, c);
		this.peso = p;
	}


	//Getters y Setters
	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
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

}
