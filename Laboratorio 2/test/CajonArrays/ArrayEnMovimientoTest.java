package CajonArrays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArrayEnMovimientoTest {
	private ArrayEnMovimiento algo;
	@Test
	void testMoverUno() {
		
		int[] anArray = { 
			    100, 200, 300, 400
			};
		int[] anArray1;
		algo =new ArrayEnMovimiento();
		anArray1 = algo.moverUno(anArray);
		assertTrue(anArray1[0] == -1);
		for (int i = 1; i < anArray.length; i++) {
			assertTrue(anArray[i-1] == anArray1[i]);
		}
	}

}
