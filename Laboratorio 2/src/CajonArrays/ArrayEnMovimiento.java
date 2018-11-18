package CajonArrays;

public class ArrayEnMovimiento {
	
	public int[] moverUno(int[] inArray) {
		int[] aux = new int[inArray.length];
		aux[0] = -1;
		
		for (int i = 1; i < aux.length; i++) {
			aux[i] = inArray[i-1];
		}
		return aux;
	}
}
