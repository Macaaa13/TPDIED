package died.tp.controllers;

import died.tp.dao.CamionDao;
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
	private CamionDao cd;
	
	//----- Constructores -----
	// Constructor para Agregar Camión
	public CamionController(PanelCamiones pc) {
		lista = new ArrayList<Camion>();
		this.pc = pc;
		c = new Camion();
		cd = new CamionDao();
	}
	
	//Métodos
	public Camion guardar() throws DatosObligatoriosException, FormatoNumeroException {
		this.actualizarModelo();
		cd.altaActualizacionCamion(c);
		lista.clear();
		lista.addAll(cd.buscarTodos());
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
			if(pc.getTextFieldModelo()!=null) c.setModelo(pc.getTextFieldModelo().getText());
			if(pc.getTextFieldKMRecorridos()!=null) c.setKmRecorridos(Double.valueOf(pc.getTextFieldKMRecorridos().getText()));
			if(pc.getTextFieldCostoKM()!=null) c.setCostoKM(Double.valueOf(pc.getTextFieldCostoKM().getText()));
			if(pc.getTextFieldCostoHora()!=null) c.setCostoHora(Double.valueOf(pc.getTextFieldCostoHora().getText()));
			if(pc.getDateChooserFechaCompra()!=null) c.setFechaCompra(pc.getDateChooserFechaCompra().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		} catch(NumberFormatException nfe) {
			throw new FormatoNumeroException();
		}
	}
	
}
