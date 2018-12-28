package B;


import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;




class EmisorFacturaTest {
	private EmailService email = mock(EmailService.class);
	private PrinterService print = mock(PrinterService.class);
	private Factura fact = mock(Factura.class);
	EmisorFactura emi;
	Cliente cli ,not;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	void testEmitirFactura() {
		emi = new EmisorFactura(print,email);
		cli = new Cliente("hola", true);
		not = new Cliente("adios", false);
		emi.emitirFactura(fact, cli);
		verify(email).sendFactura(fact,cli.getEmail());
		emi.emitirFactura(fact, not);
		verify(print).printFactura(fact);
	}

}
