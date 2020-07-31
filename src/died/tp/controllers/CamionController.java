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
	public void guardar() {
		this.actualizarModelo();
		cd.altaActualizacionCamion(c);
		lista.clear();
		lista.addAll(cd.buscarTodos());
	}
	
	public void actualizar(Integer id) {
		this.actualizarModelo();
		c.setId(id);
		cd.altaActualizacionCamion(c);
		lista.clear();
		lista.addAll(cd.buscarTodos());
	}
	
	/** Como para la busqueda de camiones pueden completarse todos, algunos o ningun campo, se valida que estos no estén vacios
	 *  Se asigna a c un nuevo camion porque de lo contrario quedan guardados los datos anteriores
	 */
	public void actualizarModelo() {
		c = new Camion();
		if(!pc.getTextFieldPatente().getText().isEmpty()) c.setPatente(pc.getTextFieldPatente().getText());
		if(!pc.getTextFieldMarca().getText().isEmpty()) c.setMarca(pc.getTextFieldMarca().getText());
		if(!pc.getTextFieldModelo().getText().isEmpty()) c.setModelo(pc.getTextFieldModelo().getText());
		if(!pc.getTextFieldKMRecorridos().getText().isEmpty()) c.setKmRecorridos(Double.valueOf(pc.getTextFieldKMRecorridos().getText()));	
		if(!pc.getTextFieldCostoKM().getText().isEmpty()) c.setCostoKM(Double.valueOf(pc.getTextFieldCostoKM().getText()));	
		if(!pc.getTextFieldCostoHora().getText().isEmpty()) c.setCostoHora(Double.valueOf(pc.getTextFieldCostoHora().getText()));	
		if(pc.getDateChooserFechaCompra().getDate() != null) c.setFechaCompra(pc.getDateChooserFechaCompra().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());		
	}
	
	public List<Camion> traerDatos() {
		return cd.buscarTodos();
	}
	
	public void eliminarCamion(Integer id) {
		cd.eliminarCamion(id);
	}
	
	public boolean validacionVacios() {
		if(pc.getTextFieldPatente().getText().isEmpty()) {pc.informarValidacion("Error, el campo patente está vacio"); return false;}
		if(pc.getTextFieldMarca().getText().isEmpty()) {pc.informarValidacion("Error, el campo marca está vacio"); return false;}
		if(pc.getTextFieldModelo().getText().isEmpty()) {pc.informarValidacion("Error, el campo modelo está vacio"); return false;}
		if(pc.getTextFieldKMRecorridos().getText().isEmpty()) {pc.informarValidacion("Error, el campo kilometros recorridos está vacio"); return false;}
		if(pc.getTextFieldCostoKM().getText().isEmpty()) {pc.informarValidacion("Error, el campo costo por kilometro está vacio"); return false;}
		if(pc.getTextFieldCostoHora().getText().isEmpty()) {pc.informarValidacion("Error, el campo costo por hora está vacio"); return false;}
		if(pc.getDateChooserFechaCompra().getDate() == null) {pc.informarValidacion("Error, no seleccionó la fecha"); return false;}
		
		
		return true;
	}
	public boolean camposCorrectos() {
		if(!this.isDouble(pc.getTextFieldCostoHora().getText())){ pc.informarValidacion("Error, el campo costo Hora no es un número"); return false;}
		if(!this.isDouble(pc.getTextFieldCostoKM().getText())){ pc.informarValidacion("Error, el campo no es un número"); return false;}
		if(!this.isDouble(pc.getTextFieldKMRecorridos().getText())){ pc.informarValidacion("Error, el campo no es un número"); return false;}
		
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
	
	public List<Camion> buscar() {
		if(camposVacios()) {
			return cd.buscarTodos();
		}
		else {
			actualizarModelo();
			return cd.buscarPorCampos(armarString());
		}
	}
	
	/**Si todos los campos son nulos la función retorna verdadero, indicando que no se busca por algún criterio en particular
	 * sino que se deben traer todos los datos existentes
	 * Si algun campo no es nulo, la función retorna falso, indicando que se busca por un criterio en particular
	*/
	public boolean camposVacios() {
		if(pc.getTextFieldPatente().getText().isEmpty() &&
		   pc.getTextFieldMarca().getText().isEmpty() &&
		   pc.getTextFieldModelo().getText().isEmpty() &&
		   pc.getTextFieldKMRecorridos().getText().isEmpty() &&
		   pc.getTextFieldCostoKM().getText().isEmpty() &&
		   pc.getTextFieldCostoHora().getText().isEmpty() &&
		   pc.getDateChooserFechaCompra().getDate()==null) {
			return true;
		}
		return false;
	}
	
	public String armarString() {
		String s = new String();
		if(c.getPatente()!=null) s+= "PATENTE = '" + c.getPatente() + "' AND " ;
		if(c.getModelo()!=null) s+= "MODELO = '"+ c.getModelo() + "' AND ";
		if(c.getMarca()!=null) s+= "MARCA = '" + c.getMarca() + "' AND ";
		if(c.getKmRecorridos()!=null) s+= "KMRECORRIDOS = '" + c.getKmRecorridos().toString() + "' AND ";
		if(c.getCostoKM()!=null) s+= "COSTOKM = '" + c.getCostoKM().toString() + "' AND ";
		if(c.getCostoHora()!=null) s+= "COSTOHORA = '" + c.getCostoHora().toString() + "' AND ";
		if(c.getFechaCompra()!=null) s+= "FECHACOMPRA = '" + c.getFechaCompra().toString() + "' AND ";
		System.out.println(s.substring(0, s.length()-4));
		return s.substring(0, s.length()-4);
	}
	
}
