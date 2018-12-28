package Agenda;

import static org.junit.Assert.*; //estas librerias no fallan
import org.junit.*;
import org.junit.rules.*;

public class DiaBreakTest {

	Cita cita, cita2, cita3, cita8;
	DiaBreak dia, dia2;

	@Before
	public void setUp() throws Exception{
	cita = new Cita("test1", 1);
	cita2 = new Cita("test2", 2);
	cita3 = new Cita("test3", 3);
	cita8 = new Cita("test8", 8);
	dia = new DiaBreak(258);
	dia2 = new DiaBreak(134);
	}
	
	
	@Test
	public void testDiaBreak() throws DatoException {
		DiaBreak diaaux, diaaux2, diaaux3;
		diaaux = new DiaBreak(365);
		assertTrue(diaaux.getDiaNumero() == 365);
		diaaux2 = new DiaBreak(1);
		assertTrue(diaaux2.getDiaNumero() == 1);
		diaaux3 = new DiaBreak(257);
		assertTrue(diaaux3.getDiaNumero() == 257);
	}
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Test
	public void testDiaBreakException() throws DatoException {
		thrown.expect(DatoException.class);
		thrown.expectMessage("La semana debe tomar valor entre 1 y 365");
		dia = new DiaBreak(0);
	}
	@Test
	public void testDiaBreakException2() throws DatoException {
		thrown.expect(DatoException.class);
		thrown.expectMessage("La semana debe tomar valor entre 1 y 365");
		dia = new DiaBreak(366);
	}

	@Test
	public void testBuscaSlot() {
		assertTrue(dia.buscaSlot(1) == 9);
		dia.asignarCita(9, cita);
		assertTrue(dia.buscaSlot(1) == 10);
		dia.asignarCita(10, cita2);
		assertTrue(dia.buscaSlot(2) == 12);
		dia.asignarCita(14, cita);
		assertTrue(dia.buscaSlot(2) == 12);
		assertTrue(dia.buscaSlot(2) == 12);
		dia2.asignarCita(9, cita8);
		assertTrue(dia2.buscaSlot(1) == 17);
		assertTrue(dia2.buscaSlot(2) == -1);
		assertTrue(dia2.buscaSlot(3) == -1);
		assertFalse(dia2.buscaSlot(2) == 17);
		
		
	}

	@Test
	public void testAsignarCita() {
		assertTrue(dia.asignarCita(9, cita));
		assertTrue(dia.asignarCita(17, cita));
		assertTrue(dia.asignarCita(13, cita2));
		assertFalse(dia.asignarCita(9, cita));
		assertFalse(dia.asignarCita(8, cita));
		assertFalse(dia.asignarCita(14, cita3));
		assertFalse(dia.asignarCita(12, cita2));
		
	}

	@Test
	public void testGetCita() {
		dia.asignarCita(9, cita);
		assertTrue(cita.getDuracion() == dia.getCita(9).getDuracion());
		assertTrue(cita.getDescripcion() == dia.getCita(9).getDescripcion());
		assertTrue(null == dia.getCita(10));
		assertTrue(null == dia.getCita(5));
		
	}

	@Test
	public void testMuestraCita() {
		dia.asignarCita(9, cita);
		assertTrue(dia.muestraCita(9).equals("9:00 " + cita.getDescripcion()));
		assertTrue("No existe" == dia.muestraCita(11));
		assertTrue("Hora no valida" == dia.muestraCita(8));
		
	}

	@Test
	public void testValidaHora() {
		assertFalse(dia.validaHora(8));
		assertFalse(dia.validaHora(18));
		assertTrue(dia.validaHora(9));
		assertTrue(dia.validaHora(17));
		assertTrue(dia.validaHora(15));
	}

	@Test
	public void testHuecoLibre() {
		assertTrue(dia.huecoLibre(9, 1));
		dia.asignarCita(9, cita);
		assertFalse(dia.huecoLibre(9, 1));
		assertFalse(dia.huecoLibre(9, 2));
		dia.asignarCita(11, cita2);
		assertTrue(dia.huecoLibre(10, 1));
		//assertTrue(dia.huecoLibre(13, 3));
		assertFalse(dia.huecoLibre(10, 2));
		dia.asignarCita(15, cita2);
		assertFalse(dia.huecoLibre(13, 3));
		assertFalse(dia.huecoLibre(17, 2));
	}

}