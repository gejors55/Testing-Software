package Money;

import static org.junit.Assert.*;

import java.util.Hashtable;

import static org.hamcrest.CoreMatchers.*;

import org.junit.*;
import org.junit.rules.*;

public class BancoTestTemplate {
	Divisa SEK, DKK;
    Banco Nordea, DanskeBanco;
	Money importe;
	Hashtable<String, Cuenta> listacuentas;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Divisa("SEK", 0.15);
		DKK = new Divisa("DKK", 0.20);
		importe = new Money(10000,SEK);
		Nordea =  new Banco("Nordea", SEK);
		DanskeBanco= new Banco("DanskeBanco", DKK);
		listacuentas = new Hashtable<String, Cuenta>();
	}

	@Test
	public void testGetNombre() {
		assertTrue(Nordea.getNombre().equals("Nordea"));
		assertTrue(DanskeBanco.getNombre().equals("DanskeBanco"));
	}

	@Test
	public void testGetDivisa() {
		assertTrue(Nordea.getDivisa().equals(SEK));
		assertTrue(DanskeBanco.getDivisa().equals(DKK));
	}
	
	@Test
	public void testGetCuentas() {
		assertTrue(Nordea.getCuentas().equals(listacuentas));
		assertTrue(DanskeBanco.getCuentas().equals(listacuentas));
		assertTrue(Nordea.getCuentas().equals(DanskeBanco.getCuentas()));
	}
	
	
	//Abrir cuenta Inexistente
	@Test
	public void testAbrirNuevaCuenta() throws CuentaExisteException  {
		Nordea.abrirCuenta("Cuenta1");
		assertTrue(Nordea.getCuentas().containsKey("Cuenta1"));
	}
	//Abrir cuenta Existente
	@Test
	public void testAbrirCuentaExistenteException() throws CuentaExisteException {
		thrown.expect(CuentaExisteException.class);
		thrown.expectMessage("Cuenta ya existe");
		Nordea.abrirCuenta("Cuenta1");
		Nordea.abrirCuenta("Cuenta1");
	}
	
	
	//Recuperar Saldo cuenta Existente
	@Test
	public void testGetSaldoCuentaExistente() throws CuentaExisteException, CuentaNoExisteException {
		Nordea.abrirCuenta("cuenta2");
		assertTrue(Nordea.getSaldo("cuenta2")==0);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();  
	
	//Recuperar Saldo cuenta Inexistente
	@Test
	public void testGetSaldoCuentaNoExistente() throws CuentaNoExisteException  {
		thrown.expect(CuentaNoExisteException.class);
		thrown.expectMessage("Cuenta no existente");
		Nordea.getSaldo("Gon");
	}
	
	//Deposito cuenta Existente
	@Test
	public void testDeposito() throws CuentaExisteException, CuentaNoExisteException, SaldoInsuficienteException {
		Nordea.abrirCuenta("cuenta3");
		Nordea.deposito("cuenta3", importe);
		assertTrue(importe.getCantidad()== Nordea.getSaldo("cuenta3"));
	}
	//Deposito cuenta Inexistente
	@Test
	public void testDepositoCuentaNoExisteException() throws CuentaNoExisteException, SaldoInsuficienteException {
		thrown.expect(CuentaNoExisteException.class);
      	thrown.expectMessage("Cuenta no existente");
      	Nordea.deposito("Mercedes", importe);
	}

	//Reintegro cuenta Existente con saldo suficiente
	@Test
	public void testReintegro() throws CuentaExisteException, CuentaNoExisteException, SaldoInsuficienteException {
		Nordea.abrirCuenta("cuenta3");
		Nordea.deposito("cuenta3", importe);
		Nordea.reintegro("cuenta3", importe);
		assertTrue(Nordea.getSaldo("cuenta3")==0);
	}
	
	//Reintegro cuenta Inexistente
	@Test
	public void testReintegroCuentaNoExisteException() throws SaldoInsuficienteException, CuentaNoExisteException {
		thrown.expect(CuentaNoExisteException.class);
      	thrown.expectMessage("Cuenta no existente");
      	Nordea.reintegro("cuenta3", importe);
	}
	
	//Reintegro cuenta Existente con saldo Insuficiente
	@Test
	public void testReintegroSaldoInsuficienteException() throws CuentaExisteException, SaldoInsuficienteException, CuentaNoExisteException  {
		thrown.expect(SaldoInsuficienteException.class);
      	thrown.expectMessage("Saldo insuficiente");
      	Nordea.abrirCuenta("cuenta3");
    	Nordea.reintegro("cuenta3", importe);
	}
	
	//Transfer a otro Banco a una cuenta existente con Saldo Suficiente
	@Test
	public void testTransferOtroBanco() throws CuentaExisteException, CuentaNoExisteException, SaldoInsuficienteException {
		Nordea.abrirCuenta("cuenta3");
		DanskeBanco.abrirCuenta("cuenta1");
		Nordea.deposito("cuenta3", importe);
		Nordea.transfer("cuenta3", DanskeBanco, "cuenta1", importe);
		assertTrue(DanskeBanco.getSaldo("cuenta1")== DanskeBanco.getDivisa().valorEnEstaDivisa(importe.getCantidad(), importe.getDivisa()));
	}
	//Transfer a otro Banco a cuenta inexistente 
	@Test
	public void testTransferOtroBancoCuentaNoExisteException() throws CuentaExisteException, CuentaNoExisteException, SaldoInsuficienteException {
		thrown.expect(CuentaNoExisteException.class);
      	thrown.expectMessage("Cuenta no existente");
		Nordea.abrirCuenta("cuenta3");
		Nordea.deposito("cuenta3", importe);
		Nordea.transfer("cuenta3", DanskeBanco, "cuenta1", importe);
	}
	
	//Transfer a otro Banco a una cuenta existente con Saldo Insuficiente
	@Test
	public void testTransferOtroBancoSaldoInsuficienteException() throws CuentaExisteException, CuentaNoExisteException, SaldoInsuficienteException {
		thrown.expect(SaldoInsuficienteException.class);
      	thrown.expectMessage("Saldo insuficiente");
      	Nordea.abrirCuenta("cuenta3");
		DanskeBanco.abrirCuenta("cuenta1");
		Nordea.transfer("cuenta3", DanskeBanco, "cuenta1", importe);
	}
	//Transfer a mismo Banco a una cuenta existente con Saldo Suficiente
	@Test
	public void testTransferMismoBanco() throws CuentaExisteException, CuentaNoExisteException, SaldoInsuficienteException {
		Nordea.abrirCuenta("cuenta3");
		Nordea.abrirCuenta("cuenta1");
		Nordea.deposito("cuenta3", importe);
		Nordea.transfer("cuenta3", "cuenta1", importe);
		assertTrue(Nordea.getSaldo("cuenta1") == importe.getCantidad());
	}
	//Transfer mismo Banco a cuenta inexistente	
	@Test
	public void testTransferMismoBancoCuentaNoExisteException() throws CuentaExisteException, CuentaNoExisteException, SaldoInsuficienteException  {
		thrown.expect(CuentaNoExisteException.class);
      	thrown.expectMessage("Cuenta no existente");
		Nordea.abrirCuenta("cuenta3");
		Nordea.deposito("cuenta3", importe);
		Nordea.transfer("cuenta3", "cuenta1", importe);
	}
	//Transfer a mismo banco a una cuenta existente con Saldo Insuficiente
	@Test
	public void testTransferMismoBancoSaldoInsuficienteException() throws CuentaExisteException, CuentaNoExisteException, SaldoInsuficienteException  {
		thrown.expect(SaldoInsuficienteException.class);
      	thrown.expectMessage("Saldo insuficiente");
      	Nordea.abrirCuenta("cuenta3");
		DanskeBanco.abrirCuenta("cuenta1");
		Nordea.transfer("cuenta3", "cuenta1", importe);
	}
	
}
