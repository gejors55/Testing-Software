package Agenda;


import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;

import junitparams.Parameters;

import org.junit.Test;

@RunWith(JUnitParamsRunner.class)
public class DiaBreakTest {
	
	private Dia dia;
	private Cita cita1, cita2;
	private int hora;
	
	@Before
	public void setUp() throws Exception {
		dia = new Dia(100);
	}
		
/*
 * Tests constructores
 */
	
	private static final Object[] genDiasOk() {
		return new Object[] {
		new Object[] {1},
		new Object[] {183},
		new Object[] {365}
		};
		}
	
		@Test
		@Parameters(method = "genDiasOk")
		public void constructorDiasCorrectos(int numdia) throws DatoException{
		Dia diaX = new Dia(numdia);
		assertEquals(numdia, diaX.getDiaNumero());		
		}

		
		private static final Object[] genDiasError() {
			return new Object[] {
			new Object[] {0},
			new Object[] {366}
			};
			}
		
		@Test(expected = DatoException.class)
		@Parameters(method = "genDiasError")
		public void constructorDiasError(int numdia) throws DatoException{
		Dia diaX = new Dia(numdia);
		diaX.getDiaNumero();
		}
		
/*
 * Tests Horas correctas
 */
		private static final Object[] genHorasOk() {
			return new Object[] {
			new Object[] {9},
			new Object[] {10},
			new Object[] {11},
			new Object[] {12},
			new Object[] {13},
			new Object[] {14},
			new Object[] {15},
			new Object[] {16},
			new Object[] {17}
			};
			}
		
		@Test
		@Parameters(method = "genHorasOk")
		public void horaValida(int hora) {
		
		assertTrue(dia.validaHora(hora));
		
		}
/*
 * Tests Horas incorrectas
 */		
		@Test
		public void horaNoValida() {

		assertFalse(dia.validaHora(8));
		assertFalse(dia.validaHora(18));
		
		}

/*
 * Tests descripción citas
 */
		@Test
		public void descCitaExistente() {
		
		cita1 = new Cita("Cita 1", 1);
		dia.asignarCita(9, cita1);
		assertEquals("9:00 Cita 1", dia.muestraCita(9));
		
		}
		
		@Test
		public void descCitaInexistente() {
		
		assertEquals("No existe", dia.muestraCita(9));
		
		}
		
		@Test
		public void descCitaHoraErronea()  {

		assertEquals("Hora no valida", dia.muestraCita(7));
		
		}
		
		@Test
		public void diaSinCitasAsignaLas9() {
			
		hora = dia.buscaSlot(1);
		assertEquals(9, hora);
		
		}
		
		@Test
		public void asignacionSlotHoraErronea(){
		
		cita1 = new Cita("Cita1", 1);
		assertFalse(dia.asignarCita(8, cita1));
		assertFalse(dia.asignarCita(18, cita1));
		
		}
		
		@Test
		public void asignacionSlotHoraYaAsignada() {
		
		cita1 = new Cita("Cita1", 1);
		cita2 = new Cita("Cita2", 1);
		assertTrue(dia.asignarCita(9, cita1));
		assertFalse(dia.asignarCita(9, cita2));
		
		}
		
		@Test
		public void asignacionSlotHorasSolapadas() {
		
		cita1 = new Cita("Cita1", 2);
		cita2 = new Cita("Cita2", 2);
		assertTrue(dia.asignarCita(10, cita1));
		assertFalse(dia.asignarCita(9, cita2));
		
		}
		
		@Test
		public void citaDeUnDiaCompletoLlenaTodasLasHoras(){
			
			assertEquals(9, dia.buscaSlot(9));
		
		}
		
		@Test
		public void citaCabeEntreCitasExistentes() {
			
		cita1 = new Cita("Cita1", 4);
		cita2 = new Cita("Cita2", 2);
		dia.asignarCita(10, cita1);
		dia.asignarCita(16, cita2);
		assertEquals(14, dia.buscaSlot(2));
		}
		
		@Test
		public void citaNoCabeEntreCitasExistentes() {
			
		cita1 = new Cita("Cita1", 4);
		cita2 = new Cita("Cita2", 2);
		dia.asignarCita(10, cita1);
		dia.asignarCita(16, cita2);
		assertEquals(-1, dia.buscaSlot(3));
		
		
		}
		
		@Test
		public void diaCompletoNoPermiteMasCitas() {
			
		cita1 = new Cita("Cita1", 9);
		dia.asignarCita(dia.buscaSlot(9), cita1);
		assertEquals(-1, dia.buscaSlot(1));
		
		
		}

		@Test
		public void citaDeDuracionMayorQueLosHuecosDisponiblesInicioyFin() {
			
		cita1 = new Cita("Cita1", 7);
		dia.asignarCita(10, cita1);
		assertEquals(-1, dia.buscaSlot(2));
		
		}
}