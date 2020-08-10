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

	
	//M�todos
	/**  El m�todo cumple la funci�n de agregar un v�rtice y crear una arista si es posible
	 * 	 Se agrega la �ltima planta de la lista, es decir, la �ltima planta creada
	 *   Si hay m�s de una planta, comienzan a crearse las rutas:
	 *   - Como planta origen se usa la �ltima planta creada
	 *   - Como planta destino se elige una al azar entre las plantas restantes
	 *   Una vez agregada la ruta al grafo, se busca la �ltima arista agregada que contiene todos los datos necesarios
	 *   para crear la ruta, y el controller se ocupa de indicarle al dao que debe guardar los datos en la base de datos
	 */
	public void agregarRuta() {
		
	}
	
	public List<Planta> getRutaCorta(Planta origen, Planta destino){
		List<Planta> rutaInicial = new LinkedList<Planta>();
		rutaInicial.add(origen);
		return getRutaCorta(origen, destino, rutaInicial);
	}
	
	public List<Planta> getRutaCorta(Planta origen, Planta destino, List<Planta> listaPlantas){
		List<Planta> adyacentes = this.getAdyacentes(origen);
		for(Planta p: adyacentes) {
			if(p.equals(destino)) {
				listaPlantas.add(p);
				return listaPlantas;
			} else {
				
			}
		}
		return null;
	}
	
}