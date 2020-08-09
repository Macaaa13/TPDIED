package died.tp.grafos;

import died.tp.dominio.Planta;

public class GrafoPlantas extends Grafo<Planta> {

	//Atributos
	private GrafoPlantas gr;
	
	//Métodos
	public void agregarNodo(Planta p) {
		gr.addNodo(p);
	}
	
}
