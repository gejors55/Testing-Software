package Money;

import java.util.Hashtable;

public class Banco {
	
	private Hashtable<String, Cuenta> listacuentas;
	private String nombre;
	private Divisa divisa;
	
	/**
	 * Nuevo Banco
	 * @param nombre 
	 * @param divisa base del banco
	 */
	Banco(String nombre, Divisa divisa) {
		listacuentas = new Hashtable<String, Cuenta>();
		this.nombre = nombre;
		this.divisa = divisa;
	}
	
	/**
	 * @return  nombre del banco
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @return divisa del banco
	 */
	public Divisa getDivisa() {
		return divisa;
	}
	
	/**
	 * @return cuentas del banco
	 */
	public Hashtable<String, Cuenta> getCuentas() {
		return listacuentas;
	}
	
	/**
	 * Saldo de una cuenta
	 * @param cuentaid cuenta del saldo solicitado
	 * @return saldo de la cuenta
	 * @throws CuentaNoExisteException si la cuenta ya existe
	 */
	public Integer getSaldo(String cuentaid) throws CuentaNoExisteException {
		if (!listacuentas.containsKey(cuentaid)) {
			throw new CuentaNoExisteException("Cuenta no existente");
		}
		else {
			return listacuentas.get(cuentaid).getSaldo().getCantidad();
		}
	}
	
	/**
	 * Abre una cuenta en el banco
	 * @param cuentaid 
	 * @throws CuentaExisteException si la cuenta ya existe
	 */
	public void abrirCuenta(String cuentaid) throws CuentaExisteException { 
		if (listacuentas.containsKey(cuentaid)) {
			throw new CuentaExisteException("Cuenta ya existe");
		}
		else {																	//Hemos cambiado todo del else
			Cuenta cuenta = new Cuenta(cuentaid, this.divisa);
			listacuentas.put(cuentaid, cuenta);
		}
	}
	
	/**
	 * Deposito dinero en una cuenta
	 * @param cuentaid cuenta destino del depósito
	 * @param money deposito
	 * @throws CuentaNoExisteException si la cuenta ya existe
	 */
	public void deposito(String cuentaid, Money money) throws CuentaNoExisteException, SaldoInsuficienteException {
		if (!listacuentas.containsKey(cuentaid)) {
			throw new CuentaNoExisteException("Cuenta no existente");
		}
		else {
			listacuentas.get(cuentaid).deposito(money);							//Error cambiamos reintegro por deposito
		}
	}
	
	/**
	 * Reintegro de una cuenta
	 * @param cuentaid cuenta del reintegro
	 * @param money reintegro
	 * @throws CuentaNoExisteException si la cuenta no existe
	 */
	public void reintegro(String cuentaid, Money money) throws SaldoInsuficienteException, CuentaNoExisteException{
		if (!listacuentas.containsKey(cuentaid)) {							//Error, hemos añadido el if de la excepcion
			throw new CuentaNoExisteException("Cuenta no existente");
		}
		else {
		listacuentas.get(cuentaid).reintegro(money);
		}
	}
	
	

	/**
	 * Transferir dinero entre cuentas
	 * @param decuenta cuenta de la que se reintegra el dinero
	 * @param tobanco banco de la cuenta destino
	 * @param tocuenta cuenta en la que se deposita el dinero
	 * @param cantidad importe a transferir
	 * @throws CuentaNoExisteException si la cuenta ya existe
	 * @throws SaldoInsuficiente si el importe supera al saldo en cuenta
	 */
	public void transfer(String decuenta, Banco tobanco, String tocuenta, Money cantidad) throws CuentaNoExisteException, SaldoInsuficienteException {
		if (!listacuentas.containsKey(decuenta) && listacuentas.containsKey(tocuenta) ) { 		//hemos añadido && y la segunda condicion
			throw new CuentaNoExisteException("Cuenta no existente");
		}
		
		else {
			this.reintegro(decuenta,cantidad);
			tobanco.deposito(tocuenta,cantidad);
		}
	}

	/**
	 * Transferir dinero entre cuentas del mismo banco
	 * @param decuenta cuenta de la que se reintegra el dinero
	 * @param tocuenta cuenta en la que se deposita el dinero
	 * @param cantidad importe a transferir
	 * @throws CuentaNoExisteException si la cuenta ya existe
	 */
	public void transfer(String decuenta, String tocuenta, Money cantidad) throws CuentaNoExisteException, SaldoInsuficienteException {
		if (!listacuentas.containsKey(decuenta)&& listacuentas.containsKey(tocuenta)) {					// hemos cambiado ||  por &&
			throw new CuentaNoExisteException("Cuenta no existente");
		}
		
		else {
			transfer(decuenta, this, tocuenta, cantidad); 					//hemos corregido tocuenta como tercer parametro
		}		

	}

}
