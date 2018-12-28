package Carreras;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.HashSet;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class ResultadosCarrerasTest {
	
	private Cliente cli = mock(Cliente.class);
	private Cliente cli2 = mock(Cliente.class);
	private Cliente cli3 = mock(Cliente.class);
	private Mensaje sms = mock(Mensaje.class);
	private ResultadosCarreras resultado = mock(ResultadosCarreras.class);
	ResultadosCarreras result;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);	
	}
	
	@Test
	void testNoSuscripcion() {
		result = new ResultadosCarreras();
		result.enviar(sms);
		verify(cli, never()).recibe(sms);
	
	}
	@Test
	void testSuscripcion() {
		result = new ResultadosCarreras();
		result.nuevaSuscripcion(cli);
		result.enviar(sms);
		verify(cli, times(1)).recibe(sms);
	}
	
	@Test
	void testVariosClientes() {
		result = new ResultadosCarreras();
		result.nuevaSuscripcion(cli);
		result.nuevaSuscripcion(cli2);
		result.nuevaSuscripcion(cli3);
		result.enviar(sms);
		verify(cli, times(1)).recibe(sms);
		verify(cli2, times(1)).recibe(sms);
		verify(cli3, times(1)).recibe(sms);
		
	}
	
	@Test
	void testVariasSuscripciones() {
		/*Collection<Cliente> clientes = new HashSet<Cliente>();
		clientes.add(cli);
		assertFalse(clientes.add(cli));*/
		resultado.nuevaSuscripcion(cli);
		verify(resultado).nuevaSuscripcion(cli);
		resultado.nuevaSuscripcion(cli);
		verifyNoMoreInteractions(cli);
	}
	

	@Test
	void testBajaSuscripcion() {
		result = new ResultadosCarreras();
		result.nuevaSuscripcion(cli);
		result.bajaSuscripcion(cli);
		result.enviar(sms);
		verify(cli, never()).recibe(sms);
	}

}
