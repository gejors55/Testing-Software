package Agenda;

import static org.junit.Assert.*; //estas librerias no fallan
import org.junit.*;
import org.junit.rules.*;

public class SemanaBreakTest {
	Dia dia, dia2;
	Semana semana, semana2;
	Cita cita8, cita9,cita;
	@Before
	public void setUp() throws Exception{
		dia = new Dia(258);
		dia2 = new Dia(134);
		cita = new Cita("test1", 1);
		cita8 = new Cita("test8", 8);
		cita9 = new Cita("test9", 9);
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Test
	public void testSemanaBreak() throws DatoException {
		Semana semana3, semana4;
		semana3 = new Semana(13);
		assertTrue(semana3.getNumeroSemana() == 13);
		semana4 = new Semana(52);
		assertTrue(semana4.getNumeroSemana() == 52);
	}
	@Test
	public void testSemanaBreakException() throws DatoException {
		thrown.expect(DatoException.class);
		thrown.expectMessage("La semana debe tomar valor entre 1 y 52");
		semana = new Semana(0);
	}
	@Test
	public void testSemanaBreakException2() throws DatoException {
		thrown.expect(DatoException.class);
		thrown.expectMessage("La semana debe tomar valor entre 1 y 52");
		semana = new Semana(53);
	}

	/*@Test
	public void testMostrarCita() {
		
	}*/

	@Test
	public void testGetDia() throws DatoException {
		semana = new Semana(14);
		assertTrue(semana.getDia(6)== null);
		assertTrue(semana.getDia(7)== null);
		assertTrue(semana.getDia(25)== null);
		assertTrue(semana.getDia(1).getDiaNumero()==99);
	}


	@Test
	public void testPrimerHueco() throws DatoException {
		semana = new Semana(14);
		assertTrue(semana.primerHueco(1).equals("Lunes 9:00"));
		semana.getDia(1).asignarCita(9, cita8);
		assertTrue(semana.primerHueco(2).equals("Martes 9:00"));
		assertTrue(semana.primerHueco(1).equals("Lunes 17:00"));
		semana.getDia(1).asignarCita(17, cita);
		semana.getDia(2).asignarCita(9, cita9);
		semana.getDia(3).asignarCita(9, cita9);
		semana.getDia(4).asignarCita(9, cita9);
		semana.getDia(5).asignarCita(9, cita8);
		assertTrue(semana.primerHueco(2).equals("No hay disponibilidad"));
		assertTrue(semana.primerHueco(1).equals("Viernes 17:00"));
		semana.getDia(5).asignarCita(17, cita);
		assertTrue(semana.primerHueco(1).equals("No hay disponibilidad"));	
	}

	@Test
	public void testDiaSemana() throws DatoException {
		semana = new Semana (14);
		assertTrue(semana.diaSemana(1).equals("Lunes"));
		assertTrue(semana.diaSemana(3).equals("Miercoles"));
		assertTrue(semana.diaSemana(5).equals("Viernes"));
		assertTrue(semana.diaSemana(0).equals("No citable"));
		assertTrue(semana.diaSemana(45).equals("No citable"));
	}
}
