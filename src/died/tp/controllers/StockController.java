package died.tp.controllers;

import java.util.ArrayList;
import java.util.List;
import died.tp.dao.PlantaStockDao;
import died.tp.dominio.Insumo;
import died.tp.dominio.Planta;
import died.tp.dominio.Stock;
import died.tp.jpanel.stock.PanelStock;

public class StockController {
	private Stock s;
	private Planta p;
	private List<Stock> listaStock;
	private PanelStock ps;
	private PlantaStockDao psd;
	
	
	public StockController(PanelStock ps) {
		this.ps = ps;
		this.listaStock = new ArrayList<Stock>();
		this.psd = new PlantaStockDao();
		this.s = new Stock();
	}
	public boolean controlarInsumoPlanta(String textFieldInsumo) {
		return psd.obtenerInsumo(textFieldInsumo,p.getId());
	}
	
	public void setPlanta(String planta) {
		this.p = psd.getPlanta(planta);
		this.listaStock = this.traerTodos(null);
		p.setStockInsumos(listaStock);
	}

	public List<Stock> traerTodos(List<Integer> s){
		return psd.traerTodos(p.getId(),s);
	}


	public void agregarInsumoPlanta() {
		Insumo i = this.existeInsumo();
		if(i != null) {
			actualizarModelo(i);
			p.getStockInsumos().add(s);
			psd.darAltaStock(p,s);
		}
		else {
			ps.informarError("El insumo no fue agregado.\n Por favor agregarlo desde la pestaña Insumos y volver a intentar.");
		}
	}
	public Insumo existeInsumo() {
		return psd.traerInsumo(ps.getTextFieldInsumo().getText());	
	}
	public boolean validacionVacios() {
		if(ps.getTextFieldCantidad().getText().isEmpty()) {ps.informarError("El campo cantidad se encuentra vacío"); return false;}
		if(ps.getTextFieldInsumo().getText().isEmpty()) {ps.informarError("El campo insumo se encuentra vacío"); return false;}
		if(ps.getTextFieldPuntoPedido().getText().isEmpty()) {ps.informarError("El campo punto pedido se encuentra vacío"); return false;}
		return true;
	}
	public boolean validarCampos() {
		if(!isInt(ps.getTextFieldCantidad().getText())) { ps.informarError("El campo cantidad debe ser un número"); return false;}
		if(!isInt(ps.getTextFieldPuntoPedido().getText())) { ps.informarError("El campo punto pedido debe ser un número"); return false;}
		return true;
	}
	
	public boolean isInt(String numero){
	    try{
	        Integer.parseUnsignedInt(numero);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
	public List<String> traerPlantas() {
		List<Planta> plantas = psd.traerPlantas();
		List<String> resultado = new ArrayList<String>();
		for(Planta p: plantas) {
			resultado.add(p.getNombrePlanta());
		}
		return resultado;
	}
	public void actualizar() {
		actualizarModelo(existeInsumo());
		psd.actualizarStock(p.getId(), s);
		listaStock.clear();
		listaStock.addAll(traerTodos(null));
		
	}
	private void actualizarModelo(Insumo i) {
		s.setCantidad(Integer.valueOf(ps.getTextFieldCantidad().getText()));
		s.setProducto(i);
		s.setPuntoPedido(Integer.valueOf(ps.getTextFieldPuntoPedido().getText()));
		
		
	}
}
