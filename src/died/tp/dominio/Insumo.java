package died.tp.dominio;

public abstract class Insumo {

	//Atributos
	private String descripcion;
	private String uMedida;
	private Integer costo;
	public abstract Integer pesoPorUnidad();
	
	//Constructor
	public Insumo(String d, String u, Integer c) {
		this.descripcion = d;
		this.uMedida = u;
		this.costo = c;
	}
	
	
	//Getters and setters
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getuMedida() {
		return uMedida;
	}

	public void setuMedida(String uMedida) {
		this.uMedida = uMedida;
	}

	public Integer getCosto() {
		return costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	} 
	
}
