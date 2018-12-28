package Bolsa;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.MockitoAnnotations;



class StockBrokerTest {
	private AnalistaMercado analista = mock(AnalistaMercado.class);
	private Portfolio porti = mock(Portfolio.class);
	BigDecimal uno = new BigDecimal("80");
	BigDecimal zero = new BigDecimal("10");
	BigDecimal aux = new BigDecimal("0.10");
	BigDecimal dos = new BigDecimal("2");
	BigDecimal nuevo = new BigDecimal("1");
	StockBroker broker;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);	
	}
	@Test
	void testVender() {
		Stock stock = new Stock("hola", "adios", uno);
		Stock stock2 = new Stock("hola", "adios", aux);
		Portfolio port = new Portfolio();
		for (int i = 0; i < 10; i++) {
			port.comprar(stock);
			port.comprar(stock2);
		}
		when(analista.getCotizacion("hola")).thenReturn(stock);
		broker = new StockBroker(analista);
		broker.perform(port, stock);
		assertEquals(port.getValorActual().divide(port.getAvgPrecio(stock)), zero);
	}
	
	@Test
	void testComprar() {
		Stock stock = new Stock("hola", "adios", uno);
		Portfolio port = new Portfolio();
		when(analista.getCotizacion("hola")).thenReturn(stock);
		broker = new StockBroker(analista);
		port.comprar(stock);
		//stock.updatePrecio(aux);
		broker.perform(port, stock);
		assertEquals(port.getValorActual().divide(port.getAvgPrecio(stock)), dos);
	}
	@Rule
	public ExpectedException thrown =ExpectedException.none(); 
	
	@Test
	void testException() {
		Stock stock = new Stock("hola", "adios", uno);
		doThrow(new IllegalStateException(stock.getSimbolo() + " no incluido en la cartera"))
		.when(porti).vender(any(Stock.class), anyInt());
	}
}
