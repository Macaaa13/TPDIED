package died.tp.controllers;

import died.tp.servicios.*;
import died.tp.dominio.*;
import died.tp.excepciones.*;
import died.tp.jpanel.camion.*;

import java.time.ZoneId;
import java.util.*;

public class CamionController {

	//Atributos
	private Camion c;
	private List<Camion> lista;
	private PanelCamiones pc;
	private ServicioCamion sc;
	
	//----- Constructores -----
	// Constructor para Agregar Camión
	public CamionController(PanelCamiones pc) {
		lista = new ArrayList<Camion>();
		this.pc = pc;
		c = new Camion();
		sc = new ServicioCamion();
	}
	
	//Métodos
	public Camion guardar() throws DatosObligatoriosException, FormatoNumeroException {
		this.actualizarModelo();
		sc.crearCamion(c);
		lista.clear();
		lista.addAll(sc.listar());
		return null;
	}
	
	public void actualizarModelo() throws DatosObligatoriosException, FormatoNumeroException {
		try {
			if(pc.getTextFieldPatente()!=null) {
				c.setPatente(pc.getTextFieldPatente().getText());
			}
			else {
				throw new DatosObligatoriosException("La patente es obligatoria.");
			}
			if(pc.getTextFieldMarca()!=null) c.setMarca(pc.getTextFieldMarca().getText());
			if(pc.getTextFieldModelo()!=null) c.setModelo(Integer.valueOf(pc.getTextFieldModelo().getText()));
			if(pc.getTextFieldKMRecorridos()!=null) c.setKmRecorridos(Integer.valueOf(pc.getTextFieldKMRecorridos().getText()));
			if(pc.getTextFieldCostoKM()!=null) c.setCostoKM(Double.valueOf(pc.getTextFieldCostoKM().getText()));
			if(pc.getTextFieldCostoHora()!=null) c.setCostoHora(Double.valueOf(pc.getTextFieldCostoHora().getText()));
			if(pc.getDateChooserFechaCompra()!=null) c.setFechaCompra(pc.getDateChooserFechaCompra().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		} catch(NumberFormatException nfe) {
			throw new FormatoNumeroException();
		}
	}
	
}
