package died.tp.servicios;

import java.util.List;

import died.tp.dao.CamionDao;
import died.tp.dominio.Camion;

public class ServicioCamion {

	//Atributos
	private CamionDao cd = new CamionDao();
	
	
	//Métodos
	public Camion crearCamion(Camion c) {
		return cd.insertOrUpdate(c);
	}
	
	public List<Camion> listar(){
		return cd.listarTodos();
	}
}
