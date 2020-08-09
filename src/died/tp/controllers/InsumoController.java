package died.tp.controllers;

import java.util.*;
import died.tp.dao.InsumoDao;
import died.tp.dominio.Insumo;
import died.tp.excepciones.DatosObligatoriosException;
import died.tp.excepciones.FormatoNumeroException;
import died.tp.dominio.*;
import died.tp.jpanel.insumo.PanelInsumos;

public class InsumoController {

	//Atributos
	private Insumo i;
	private List<Insumo> listaInsumos;
	private PanelInsumos pi;
	private InsumoDao id;
	
	
	//Constructor
	public InsumoController(PanelInsumos pi) {
		this.pi = pi;
		listaInsumos = new ArrayList<Insumo>();
		id = new InsumoDao();
		i = new InsumoGeneral();
	}
	
	
	//Métodos	
	public void cambiarInsumo(String opcion) {
		switch(opcion) {
			case "general":{
				if(!i.esGeneral()) {
					this.i = new InsumoGeneral();
				}
			}
			break;
			case "liquido":{
				if(!i.esLiquido()) {
					this.i = new InsumoLiquido();
				}
			}
		}
	}
	
	public boolean validacionVacios() {
		if(pi.getTextFieldUnidadMedida().getText().isEmpty()) {pi.informarValidacion("El campo Unidad de medida se encuentra vacio"); return false; }
		if(pi.getTextFieldCosto().getText().isEmpty()) {pi.informarValidacion("El campo Costo se encuentra vacio"); return false; }
		if(pi.getTextFieldDescripcion().getText().isEmpty()) {pi.informarValidacion("El campo Descripción está vacío (colocar '-' si no contiene la misma)"); return false;}
		if(pi.getComboBoxTipo().getSelectedItem().toString().equals("General")) {
			if(pi.getTextFieldPeso().getText().isEmpty()) {pi.informarValidacion("El campo Peso se encuentra vacio"); return false; }
		}
		else {
			if(pi.getTextFieldDensidad().getText().isEmpty()) {pi.informarValidacion("El campo Densidad se encuentra vacio"); return false; }
		}
		return true;
	}
	
	public boolean camposCorrectos() {
		if(this.isDouble(pi.getTextFieldUnidadMedida().getText())) { pi.informarValidacion("Error, el campo Unidad de medida no debe contener números"); return false;}
		if(!this.isDouble(pi.getTextFieldCosto().getText())) { pi.informarValidacion("Error, el campo Costo no es un número"); return false;}
		if(pi.getComboBoxTipo().getSelectedItem().toString().equals("General")) {
			if(!this.isDouble(pi.getTextFieldPeso().getText())) { pi.informarValidacion("Error, el campo Peso no es un número"); return false;}
		}
		else {
			if(!this.isDouble(pi.getTextFieldDensidad().getText())) { pi.informarValidacion("Error, el campo Densidad no es un número"); return false;}
		}
		return true;
	}
	
	public void actualizarModelo() {
		if(!(pi.getTextFieldDensidad().getText().isEmpty())) {
			this.cambiarInsumo("liquido");
			i.setPesoDensidad(Double.valueOf(pi.getTextFieldDensidad().getText()));
		}
		else {
			i.setPesoDensidad(Double.valueOf(pi.getTextFieldPeso().getText()));
			this.cambiarInsumo("general");
		}
		i.setCosto(Integer.valueOf(pi.getTextFieldCosto().getText()));
		i.setDescripcion(pi.getTextFieldDescripcion().getText());
		i.setuMedida(pi.getTextFieldUnidadMedida().getText());
	}
	
	public void guardar(List<Integer> s) throws DatosObligatoriosException, FormatoNumeroException {
		this.actualizarModelo();
		id.altaActualizacionInsumo(i);
		listaInsumos.clear();
		this.listaInsumos.addAll(id.buscarTodos(s));
	}
	
	public void eliminarInsumo(int id) {
		this.id.eliminarI(id);

	}
	
	public List<Insumo> traerDatos(List<Integer> s) {
		return id.buscarTodos(s);
	}
	
	public boolean isDouble(String numero){
	    try{
	        Double.parseDouble(numero);
	        return true;
	    }catch(NumberFormatException e){
	        return false;
	    }
	}
	
	public void actualizar(Integer id,List<Integer> s) {
		this.actualizarModelo();
		i.setId(id);
		this.id.altaActualizacionInsumo(i);
		listaInsumos.clear();
		listaInsumos.addAll(this.id.buscarTodos(s));
	}
	
	public Integer completarCampos(String[] resultados) {
		Integer aux = 0;
		System.out.println(pi.getTextFieldDescripcion().getText());
		System.out.println(pi.getTextFieldUnidadMedida().getText());
		if(!(pi.getTextFieldUnidadMedida().getText().isEmpty())) {resultados[aux] = "unidadMedida"; resultados[aux+1] = pi.getTextFieldUnidadMedida().getText(); aux +=2;}
		if(!(pi.getTextFieldCosto().getText().isEmpty())) {resultados[aux] = "costoUnidad"; resultados[aux+1] = pi.getTextFieldCosto().getText();aux +=2;}
		if(!(pi.getTextFieldPeso().getText().isEmpty())) {resultados[aux] = "peso"; resultados[aux+1] = pi.getTextFieldPeso().getText();aux +=2;}
		if(!(pi.getTextFieldDensidad().getText().isEmpty())) {resultados[aux] = "densidad"; resultados[aux+1] = pi.getTextFieldDensidad().getText();aux +=2;}
		if(!(pi.getTextFieldDescripcion().getText().isEmpty())) {resultados[aux] = "descripcion"; resultados[aux+1] = pi.getTextFieldDescripcion().getText();aux +=2;}
		return aux;
	}	

	public List<Insumo> buscarPorCampos(String[] campos, Integer cant, List<Integer> s){
		return id.buscarPorCampos(campos, cant,s);
	}
	
}
