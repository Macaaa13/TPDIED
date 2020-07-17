package died.tp.dominio;

public class Planta {

	//Atributos
	private Integer id;
	private String nombrePlanta;
	
	
	//Constructor
	public Planta (String np) {
		this.nombrePlanta = np;
	}

	
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
	
	
}
