package died.tp.dominio;

public class Stock {

	private Insumo producto;
	private int cantidad;

	public Stock(Insumo insumo, int cant) {
		this.producto = insumo;
		this.cantidad = cant;
	}

	public Insumo getProducto() {
		return producto;
	}

	public void setProducto(Insumo producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
