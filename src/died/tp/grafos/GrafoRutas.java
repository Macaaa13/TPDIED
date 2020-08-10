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

	
	//Métodos
	/**  El método cumple la función de agregar un vértice y crear una arista si es posible
	 * 	 Se agrega la última planta de la lista, es decir, la última planta creada
	 *   Si hay más de una planta, comienzan a crearse las rutas:
	 *   - Como planta origen se usa la última planta creada
	 *   - Como planta destino se elige una al azar entre las plantas restantes
	 *   Una vez agregada la ruta al grafo, se busca la última arista agregada que contiene todos los datos necesarios
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
