package died.tp.grafos;

public class Vertice<T>implements Comparable<T> {
	
	//Atributos
	private T valor;
	private boolean visitado;
	private Integer dist;
	
	
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
	
	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public Integer getDist() {
		return dist;
	}

	public void setDist(Integer dist) {
		this.dist = dist;
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

	@Override
	public int compareTo(Object o) {
		Vertice v = (Vertice) o;
		return Double.compare(this.dist, v.getDist());
	}
	
}
