package died.tp.grafos;

import died.tp.controllers.RutaController;
import died.tp.dominio.Planta;
import died.tp.dominio.Ruta;

import java.util.*;

public class GrafoRutas extends Grafo<Planta> {

	//Atributos
	private RutaController rc;
	
	
	//Constructor
	public GrafoRutas() { 
		rc = new RutaController();
	}

	
	//M�todos
	/**  El m�todo cumple la funci�n de agregar un v�rtice y crear una arista si es posible
	 * 	 Se agrega la �ltima planta de la lista, es decir, la �ltima planta creada
	 *   Si hay m�s de una planta, comienzan a crearse las rutas:
	 *   - Como planta origen se usa la �ltima planta creada
	 *   - Como planta destino se elige una al azar entre las plantas restantes
	 *   Una vez agregada la ruta al grafo, se busca la �ltima arista agregada que contiene todos los datos necesarios
	 *   para crear la ruta, y el controller se ocupa de indicarle al dao que debe guardar los datos en la base de datos
	 */
	public void armarGrafo(List<Ruta> lista) {
		for(Ruta r: lista) {
			if(!this.getVertices().contains(this.getNodo(r.getOrigen()))) { this.addNodo(r.getOrigen()); } ;
			if(!this.getVertices().contains(this.getNodo(r.getDestino()))) { this.addNodo(r.getDestino()); } ;
			this.conectar(r.getOrigen(), r.getDestino(), r.getDistancia(), r.getDuracionEstimada(), r.getPesoMax());
		}
	}
	
	public List<Planta> getRutaCorta(Planta destino){
		List<Planta> camino = new ArrayList<Planta>();
		
		
		
		Collections.reverse(camino);
		return camino;
	}
	
	
	
}
