package died.tp.excepciones;

public class DatosObligatoriosException extends Exception {

	public DatosObligatoriosException(String s) {
		super("Error: " + s);
	}
	
}
