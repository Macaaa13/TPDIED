package died.tp.grafos;

public class Vertice<T> {
	
	//Atributos
	private T valor;
	
	
	//Constructores
	public Vertice(){	}
	 
	public Vertice(T v){
		this.valor = v;
	}
	
	
	//Getters y Setters
	public void setValor(T v){
		this.valor = v;
	}
	
	public T getValor(){
		return this.valor;
	}
	
	
	//Métodos
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Vertice other = (Vertice) obj;
		if (valor == null) {
			if (other.valor != null) return false;
		} else if (!valor.equals(other.valor)) return false;
		return true;
	}

	@Override
	public String toString() {
		return valor.toString();
	}
	
}
