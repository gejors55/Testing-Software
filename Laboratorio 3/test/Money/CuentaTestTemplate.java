package Money;

import org.junit.Before;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.Hashtable;

import org.junit.rules.*;


public class CuentaTestTemplate {
	
	Divisa SEK, DKK;
	Cuenta testAccount, testAccount2;
	Money importe, importe2;
	Hashtable<String, PagoPeriodico> pagosperiodicos;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Divisa("SEK", 0.15);
		DKK = new Divisa("DKK", 0.20);
		importe = new Money(10000,SEK);
		importe2 = new Money(20000,SEK);
		testAccount = new Cuenta("Jorge", SEK);
		testAccount2= new Cuenta("Gonzalo", DKK);
		pagosperiodicos = new Hashtable<String, PagoPeriodico>();
	}
	
	//Comprobar constructor	Saldo	
	@Test
	public void testGetSaldo() {
		assertEquals(0, testAccount.getSaldo().getCantidad());
		assertEquals(0, testAccount2.getSaldo().getCantidad());
		assertEquals(SEK.getRate(), testAccount.getSaldo().getDivisa().getRate());
		assertEquals(DKK.getRate(), testAccount2.getSaldo().getDivisa().getRate());
		assertEquals("SEK", testAccount.getSaldo().getDivisa().getName());
		assertEquals("DKK", testAccount2.getSaldo().getDivisa().getName());
	}
	//Comprobar constructor pagos	
	@Test
	public void testGetPagos() {
		assertEquals(testAccount.getPagos(), pagosperiodicos);
		assertEquals(testAccount2.getPagos(), pagosperiodicos);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 
	
	//Añadir un pago no existente
	@Test
	public void testAñadirPagoPeriodico() throws PagoExistenteException  {
		testAccount.pagoPeriodico("hola", importe, "Gonzalo");
		assertTrue(testAccount.pagoPeriodicoExiste("hola"));
	}
	//Añadir un pago existente
	@Test
	public void testAñadirPagoPeriodicoException() throws PagoExistenteException{
		thrown.expect(PagoExistenteException.class);
		thrown.expectMessage("Pago ya registrado");
		testAccount.pagoPeriodico("hola", importe, "Gonzalo");
		testAccount.pagoPeriodico("hola", importe, "Gonzalo");
	}
	
	//Borrar un pago existente
	@Test
	public void testBorrarPagoPeriodico() throws PagoExistenteException, PagoNoExistenteException{
		testAccount.pagoPeriodico("hola", importe, "Gonzalo");
		testAccount.cancelarPagoPeriodico("hola");
		assertFalse(testAccount.pagoPeriodicoExiste("hola"));
	}
	//Borrar un pago no existente
	@Test
	public void testBorrarPagoPeriodicoException() throws PagoNoExistenteException  {
		thrown.expect(PagoNoExistenteException.class);
		thrown.expectMessage("Pago no registrado");
		testAccount.cancelarPagoPeriodico("Gonzalo");
	}
	
	// Deposito en cuenta
	@Test
	public void testdeposito() throws SaldoInsuficienteException {
		testAccount.deposito(importe);
		assertEquals(importe.getCantidad(), testAccount.getSaldo().getCantidad());
	}
	// Reintegro con Saldo suficiente en cuenta
	@Test
	public void testReintegro() throws SaldoInsuficienteException{
		testAccount.deposito(importe2);
		testAccount.reintegro(importe);
		assertEquals(importe.getCantidad(), testAccount.getSaldo().getCantidad());	
	}
	// Reintegro con Saldo Insuficiente en cuenta
	@Test
	public void testReintegroSaldoInsuficienteException() throws SaldoInsuficienteException {
		thrown.expect(SaldoInsuficienteException.class);
		thrown.expectMessage("Saldo insuficiente");
		testAccount.reintegro(importe);
	}
}
