package died.tp.grafos;

import died.tp.controllers.RutaController;
import died.tp.dominio.Planta;
import died.tp.dominio.Ruta;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

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
			if(!this.getVertices().isEmpty()) {
				if(!this.getVertices().contains(this.getNodo(r.getOrigen()))) { this.addNodo(r.getOrigen()); } ;
				if(!this.getVertices().contains(this.getNodo(r.getDestino()))) { this.addNodo(r.getDestino()); } ;
			} else {
				this.addNodo(r.getOrigen());
				this.addNodo(r.getDestino());
			}
			this.conectar(r.getOrigen(), r.getDestino(), r.getDistancia(), r.getDuracionEstimada(), r.getPesoMax());
		}
	}
	
	public List<List<Vertice<Planta>>> getRutaCorta(Planta p1, Planta p2, String tipo){ 
		List<List<Vertice<Planta>>> listaCaminos = caminos(getNodo(p1),getNodo(p2));
		Map<List<Vertice<Planta>>, Double> map = new HashMap<List<Vertice<Planta>>, Double>();
		for(List<Vertice<Planta>> listaVert: listaCaminos) {
			map.put(listaVert, calcularKmHs(listaVert, tipo));
		}
		return calcularListaFinal(map);
	}
	
	public List<List<Vertice<Planta>>> calcularListaFinal(Map<List<Vertice<Planta>>, Double> map){
		List<List<Vertice<Planta>>> listaFinal = new ArrayList<List<Vertice<Planta>>>();		
		Double min = Collections.max(map.values());
		for(Entry<List<Vertice<Planta>>, Double> entry: map.entrySet()) {
			if(entry.getValue().equals(min)) {
				listaFinal.add(entry.getKey());
			}
		}
		return listaFinal;
	}
	
	public Double calcularKmHs(List<Vertice<Planta>> lv, String tipo) {
		Double dist = 0.0;
		if(tipo.equals("mas corto")) {
			for(int i=0; i<lv.size()-1; i++) {
				dist += arista(lv.get(i), lv.get(i+1)).getDistancia();
			}
		} else {
			for(int i=0; i<lv.size()-1; i++) {
				dist += arista(lv.get(i), lv.get(i+1)).getDuracionEstimada();
			}
		}
		return dist;
	}
	
	public List<List<Vertice<Planta>>> caminos(Planta p1, Planta p2){
		return caminos(new Vertice<Planta>(p1), new Vertice<Planta>(p2));
	}
	
	public List<List<Vertice<Planta>>> caminos(Vertice<Planta> p1, Vertice<Planta> p2){
		List<List<Vertice<Planta>>> salida = new ArrayList<List<Vertice<Planta>>>();
		List<Vertice<Planta>> marcados = new ArrayList<Vertice<Planta>>();
		marcados.add(p1);
		buscarCaminosAux(p1,p2,marcados,salida);
		return salida;
	}
	
	private void buscarCaminosAux(Vertice<Planta> p1, Vertice<Planta> p2, List<Vertice<Planta>> marcados, List<List<Vertice<Planta>>> salida) {
		List<Vertice<Planta>> adyacentes = this.getAdyacentes(p1);
		List<Vertice<Planta>> copiaMarcados = null;
		for(Vertice<Planta> a: adyacentes) {
			copiaMarcados = marcados.stream().collect(Collectors.toList());
			if(a.equals(p2)) {
				copiaMarcados.add(p2);
				salida.add(new ArrayList<Vertice<Planta>>(copiaMarcados));
				
			} else {
				if(!copiaMarcados.contains(a)) {
					copiaMarcados.add(a);
					this.buscarCaminosAux(a, p2, copiaMarcados, salida);
				}
			}
		}
	}
	
	
	
}
