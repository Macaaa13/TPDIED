package died.tp.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import died.tp.dao.OrdenDePedidoDao;
import died.tp.dao.PlantaStockDao;
import died.tp.dao.RutaDao;
import died.tp.dominio.Camion;
import died.tp.dominio.Insumo;
import died.tp.dominio.OrdenDePedido;
import died.tp.dominio.Planta;
import died.tp.grafos.GrafoRutas;
import died.tp.jpanel.pedido.PanelInformacionOrden;

public class InformacionOrdenController {

	private List<OrdenDePedido> ordenes;
	private OrdenDePedido orden;
	private PanelInformacionOrden panelInfo;
	private OrdenDePedidoDao ordendao;
	private List<Planta> plantas;
	private List<Insumo> insumosPorOrden;
	private GrafoRutas grafo;
	private RutaDao rd;
	
	
	public InformacionOrdenController(PanelInformacionOrden pio) {
		panelInfo = pio;
		ordenes = new ArrayList<OrdenDePedido>();
		ordendao = new OrdenDePedidoDao();
		plantas = new ArrayList<Planta>();
		grafo = new GrafoRutas();
	}
	
	public List<OrdenDePedido> traerTodasOrdenes() {
		ordenes = ordendao.traerTodas();
		if(ordenes != null) {
			return ordenes;
		}
		panelInfo.informarSituacion("No hay resultados");
		return null;
	}
	
	public OrdenDePedido obtenerOrdenSeleccionada(String id) {
		for(OrdenDePedido opd: ordenes) {
			if(opd.getNroOrden().equals(Integer.valueOf(id))) {
				return opd;
			}
		}
		return null;
	}
	
	/* POR CADA PLANTA HAY QUE VERIFICAR QUE:
	 * EL STOCK CONTENGA A LOS PRODUCTOS
	 * Y QUE LA CANTIDAD DEL STOCK SEA MAYOR AL DEL PEDIDO
	 * 
	 */
	public boolean controlarStock(String idOrden) {
		PlantaStockDao psd = new PlantaStockDao();
		boolean respuesta = false;
		orden = this.obtenerOrdenSeleccionada(idOrden);
		insumosPorOrden = orden.getInsumos().keySet().stream().collect(Collectors.toList());
		List<Planta> plantasBD = psd.traerPlantas();
		for(Planta p: plantasBD) {
			List<Insumo> insporPlanta = ordendao.traerInsumosPorPlanta(p.getId());
			if((convertir(insporPlanta)).containsAll(convertir(insumosPorOrden))) {
				Integer contadorInsumosStock = 0;
				for(Insumo i: insumosPorOrden) {
					if(orden.getInsumos().get(i) < ordendao.obtenerStock(i.getId(),p.getId())) {
						contadorInsumosStock++;
					}
				}
				if(contadorInsumosStock.equals(insumosPorOrden.size())){
					respuesta = true;
					this.plantas.add(p);

				}
			}
		}
		return respuesta;
	}
	
	
	private List<String> convertir(List<Insumo> lista) {
		List<String> array = new ArrayList<>();
		for(Insumo i: lista) {
			array.add(i.getDescripcion());
		}
		return array;
	}
	/* HAY QUE CAMBIAR EL ESTADO
	 * HAY QUE MODIFICAR LA TABLA
	 */
	public void cancelarOrden(Integer nroOrden) {
		ordendao.cambiarEstadoOrden(nroOrden);

	}

	public List<Planta> getPlantas(){
		return this.plantas;
	}

	/* TRAE LOS CAMIONES QUE NO ESTÁN ASIGNADOS A UNA ORDEN 
	 * 
	 */

	public PriorityQueue<Camion> traerCamionesNoAsig(){
		return ordendao.traerCamiones();
	}

	public void traerRutas() {
		grafo.armarGrafo(rd.traerRutas());
	}
	
}
