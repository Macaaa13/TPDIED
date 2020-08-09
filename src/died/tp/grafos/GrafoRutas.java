package died.tp.grafos;

import died.tp.controllers.RutaController;
import died.tp.dominio.Planta;
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
	public void agregar(List<Planta> listaPlantas) {
		Planta origen = listaPlantas.get(listaPlantas.size()-1);
		System.out.println("\nOrigen: " + origen.getNombrePlanta());
		addNodo(origen);
		if(listaPlantas.size()>1) {
			Planta destino = listaPlantas.get(new Random().nextInt(listaPlantas.size()-1));
			System.out.println("Destino: " + destino.getNombrePlanta());
			conectar(origen, destino);
			rc.agregarRuta(getAristas().get(getAristas().size()-1));
		}	
	}
	
}
