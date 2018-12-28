package Agenda;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class SemanaBreakTest {

	private Semana sem;
	private Cita cita1, cita2, cita3, cita4, cita5;

	
	@Before
	public void setUp() throws Exception {
		sem = new Semana(1);
	}
	
	private static final Object[] genSemanasOk() {
		return new Object[] {
		new Object[] {1},
		new Object[] {25},
		new Object[] {52}
		};
	}
		
	@Test
	@Parameters(method = "genSemanasOk")
	public void constructorSemanasCorrectas(int numsem) throws DatoException{
		Semana semX = new Semana(numsem);
		assertEquals(numsem, semX.getNumeroSemana());		
	}
	
	private static final Object[] genSemanasError() {
		return new Object[] {
		new Object[] {0},
		new Object[] {53}
		};
	}
	
	@Test(expected = DatoException.class)
	@Parameters(method = "genSemanasError")
	public void constructorSemanasError(int numsem) throws DatoException{
		Semana semX = new Semana(numsem);
		semX.getNumeroSemana();
	}
	
	private static final Object[] genDiasCorrectos() {
		return new Object[] {
		new Object[] {1},
		new Object[] {2},
		new Object[] {3},
		new Object[] {4},
		new Object[] {5}
		};
	}
	@Test
	@Parameters(method = "genDiasCorrectos")
	public void getDiaCorrecto(int dia) {
		assertEquals(sem.getDia(dia).getDiaNumero(), dia);
	}
	
	@Test
	public void getDiaError(){
		assertNull(sem.getDia(0));
		assertNull(sem.getDia(6));
	}
	@Test
	public void mostrarCitaexistente() {
		cita1 = new Cita("Cita 1", 1);
		sem.getDia(1).asignarCita(9, cita1);
		assertEquals("9:00 Cita 1", sem.mostrarCita(9,1));
	}
	
	@Test
	public void mostrarCitaInexistente() {
	
	assertEquals("No existe", sem.mostrarCita(9,1));
	
	}
	
	
	@Test
	public void mostrarCitaHoraErronea()  {

	assertEquals("Hora no valida", sem.mostrarCita(7,1));
	
	}

	
	@Test
	public void DisponibleHuecoSemanaVacia() {
		
		assertEquals("Lunes 9:00",sem.primerHueco(3));
		
	}
	
	@Test
	public void DisponibleHuecoPrimerDiaConCitasRegistradas() {
		cita1 = new Cita("Cita 1", 4);
		sem.getDia(1).asignarCita(12, cita1);
		assertEquals("Lunes 9:00",sem.primerHueco(3));
		
	}

	@Test
	public void NoHayHuecosPrimerDiaDeDuracionSolicitada() {
		cita1 = new Cita("Cita 1", 6);
		sem.getDia(1).asignarCita(10, cita1);
		assertEquals("Martes 9:00",sem.primerHueco(3));
		
	}
	
	@Test
	public void PrimerDiaCompleto() {
		cita1 = new Cita("Cita 1", 9);
		sem.getDia(1).asignarCita(9, cita1);
		assertEquals("Martes 9:00",sem.primerHueco(3));
		
	}
	
	@Test
	public void primerHuecoUltimoDia() {
		cita1 = new Cita("Cita 1", 9);
		cita2 = new Cita("Cita 2", 9);
		cita3 = new Cita("Cita 3", 9);
		cita4 = new Cita("Cita 4", 9);
		sem.getDia(1).asignarCita(9, cita1);
		sem.getDia(2).asignarCita(9, cita2);
		sem.getDia(3).asignarCita(9, cita3);
		sem.getDia(4).asignarCita(9, cita4);
		assertEquals("Viernes 9:00",sem.primerHueco(3));
		
	}
	
	@Test
	public void NoHayHuecoLibre() {
		cita1 = new Cita("Cita 1", 7);
		cita2 = new Cita("Cita 2", 7);
		cita3 = new Cita("Cita 3", 7);
		cita4 = new Cita("Cita 4", 7);
		cita5 = new Cita("Cita 5", 7);
		sem.getDia(1).asignarCita(10, cita1);
		sem.getDia(2).asignarCita(10, cita2);
		sem.getDia(3).asignarCita(10, cita3);
		sem.getDia(4).asignarCita(10, cita4);
		sem.getDia(5).asignarCita(10, cita5);
		assertEquals("No hay disponibilidad",sem.primerHueco(3));
		
	}
	
	@Test
	public void nombresDiasSemana() {
		assertEquals("Lunes",sem.diaSemana(1));
		assertEquals("Martes",sem.diaSemana(2));
		assertEquals("Miercoles",sem.diaSemana(3));
		assertEquals("Jueves",sem.diaSemana(4));
		assertEquals("Viernes",sem.diaSemana(5));
		assertEquals("No citable",sem.diaSemana(6));
	}
	
	
}
