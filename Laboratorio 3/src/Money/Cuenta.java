package Money;

import java.util.Hashtable;

public class Cuenta {
	
	private Money balance;
	private Hashtable<String, PagoPeriodico> pagosperiodicos;

	Cuenta(String nombre, Divisa divisa) {
		this.balance = new Money(0, divisa);
		pagosperiodicos = new Hashtable<String, PagoPeriodico>();
	}
	
	/**
	 * Balance de cuenta
	 * @return Balance de la cuenta
	 */
	public Money getSaldo() {
		return balance;
	}
	
	/**
	 * Pagos periodicos
	 * @return Pagos asociados a cuenta
	 */
	public Hashtable<String, PagoPeriodico> getPagos() {
		return pagosperiodicos;
	}

	/**
	 * Registro de un pago periodico a otra cuenta
	 * @param id identificador del pago
	 * @param cantidad importe del pago
	 * @param tocuenta cuenta receptora del pago
	 */
	public void pagoPeriodico(String id, Money cantidad, String tocuenta) throws PagoExistenteException{
		if (pagoPeriodicoExiste(id))  {					//hemos quitado un !
			throw new PagoExistenteException("Pago ya registrado");
		}
		PagoPeriodico tp = new PagoPeriodico(cantidad, this, tocuenta);
		pagosperiodicos.put(id, tp);			//hemos cambiado tocuenta por id
	}
	
	/**
	 * Cancelación de pago periodico
	 * @param id identificador del pago
	 */
	public void cancelarPagoPeriodico(String id) throws PagoNoExistenteException {
		if (!pagoPeriodicoExiste(id))  {							//error, hemos puesto !
			throw new PagoNoExistenteException("Pago no registrado");
		}
		pagosperiodicos.remove(id);
	}
	
	/**
	 * Comprobar que un pago existe
	  * @param id identificador del pago
	 */
	
	public boolean pagoPeriodicoExiste(String id) {
		return pagosperiodicos.containsKey(id);
	}

			
	/**
	 * Deposito en cuenta
	 * @param money deposito.
	 */
	public void deposito(Money money) {
		balance = balance.add(money); 		//Error hemos cambiado add por sub
	}
	
	/**
	 * Reintegro de cuenta
	 * @param reintegro.
	 * @throws SaldoInsuficienteException si no hay saldo suficiente
	 */
	public void reintegro(Money money) throws SaldoInsuficienteException {
		if (this.balance.getCantidad() < money.getCantidad())  {			//error, hemos añadido el if
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}		
		balance = balance.sub(money);//pone la misma divisa											
	}
	
	

}
