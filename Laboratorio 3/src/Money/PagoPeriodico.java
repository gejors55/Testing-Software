package Money;

public class PagoPeriodico {
	
	private Cuenta decuenta;
	private Money cantidad;
	private String tocuenta;
	
	PagoPeriodico(Money cantidad, Cuenta decuenta, String tocuenta) {
		
		this.cantidad = cantidad;
		this.decuenta = decuenta;
		this.tocuenta = tocuenta;
	}
	
}