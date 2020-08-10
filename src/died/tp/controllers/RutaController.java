package died.tp.controllers;

import java.util.*;

import javax.swing.JOptionPane;

import died.tp.dao.RutaDao;
import died.tp.dominio.Planta;
import died.tp.dominio.Ruta;
import died.tp.jpanel.ruta.PanelRutas;

public class RutaController {

	//Atributos
	private RutaDao rd;
	private PanelRutas pr;
	private Ruta r;
	private List<Ruta> listaRutas;
	
	//Constructores
	public RutaController() {
		rd = new RutaDao();
	}
	
	public RutaController(PanelRutas pr) {
		this();
		this.pr = pr; 
		r = new Ruta();
		listaRutas = rd.traerRutas();
	}
	
	//M�todos
	public void actualizarRuta(Planta origen, Planta destino) {
		r.setOrigen(origen);
		r.setDestino(destino);
		r.setDistancia(Integer.valueOf(pr.getTextFieldDistancia().getText()));
		r.setDuracionEstimada(Double.valueOf(pr.getTextFieldDuracionEstimada().getText()));
		r.setPesoMax(Integer.valueOf(pr.getTextFieldPesoMaximo().getText()));
	}
	
	public void agregarRuta(Planta origen, Planta destino) {
		if(!rutaYaCreada(origen,destino)) {
			actualizarRuta(origen, destino);
			rd.agregarRuta(r);
			listaRutas = rd.traerRutas();
			JOptionPane.showMessageDialog(null, "Ruta agregada", "Acci�n exitosa", JOptionPane.PLAIN_MESSAGE);
		} else {
			pr.informarValidacion("La ruta ya fue creada");
		}
	}
	
	public boolean camposVacios() {
		if(pr.getComboBoxOrigen().getSelectedItem()==null) {pr.informarValidacion("Error, debe seleccionar una Planta Origen"); return false;};
		if(pr.getComboBoxDestino().getSelectedItem()==null) {pr.informarValidacion("Error, debe seleccionar una Planta Destino"); return false;};
		if(pr.getTextFieldDistancia()==null) {pr.informarValidacion("Error, el campo Distancia est� vac�o"); return false;};
		if(pr.getTextFieldDuracionEstimada()==null) {pr.informarValidacion("Error, el campo Duraci�n Estimada est� vac�o"); return false;};
		if(pr.getTextFieldPesoMaximo()==null) {pr.informarValidacion("Error, el campo Peso M�ximo est� vac�o"); return false;};
		return true;
	}
	
	public boolean camposCorrectos() {
		if(!isInteger(pr.getTextFieldDistancia().getText())) {pr.informarValidacion("Error, el campo Distancia no es un n�mero"); return false;};
		if(!isDouble(pr.getTextFieldDuracionEstimada().getText())) {pr.informarValidacion("Error, el campo Duraci�n Estimada no es un n�mero"); return false;};
		if(!isInteger(pr.getTextFieldPesoMaximo().getText())) {pr.informarValidacion("Error, el campo Peso M�ximo no es un n�mero"); return false;};
		return true;
	}
	
	public boolean isDouble(String numero){
	    try{
	        Double.parseDouble(numero);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
	
	public boolean isInteger(String numero){
	    try{
	        Integer.parseInt(numero);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
	
	public boolean rutaYaCreada(Planta o, Planta d) {
		boolean b = false;;
		for(Ruta r: listaRutas) {
			if((r.getOrigen().equals(o) && r.getDestino().equals(d)) ||
			   (r.getOrigen().equals(d) && r.getDestino().equals(o))) {
				b = true;
			}
		}
		return b;
	}
	
}
