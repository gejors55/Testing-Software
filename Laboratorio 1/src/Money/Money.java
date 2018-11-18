package Money;

public class Money implements Comparable {
	
	private int cantidad;
	private Divisa divisa;

	/**
	 * @param cantidad	
	 * @param divisa	
	 */
	Money (int cantidad, Divisa divisa) {
		this.cantidad = cantidad;
		this.divisa = divisa;
	}
	
	/**
	 * Return Cantidad.
	 * @return Cantidad.
	 */
	public int getCantidad() {
		return this.cantidad;
	}
	
	/**
	 * Returns Divisa.
	 * @return Divisa asociada a esta cantidad
	 */
	public Divisa getDivisa() {
		return this.divisa;
	}
	
	/**
	 * Devuelve un String "cantidad nombre de divisa)", e.g. "10.5 SEK".
	 * Los numeros decimales se representan con enteros, e.g "10.5 SEK" se almacena como 1050
	 * 
	 *  @return String con información de la moneda.
	 */
	public String toString() {
		
		return "cantidad nombre de divisa: " + this.cantidad/100+"."+ this.cantidad%100 + this.divisa.getName();
	}
	
	/**
	 * Valor universal de la cantidad de acuerdo al rate asociado a la divisa
	 * @return Valor universal de la cantidad
	 */
	public int valorUniversal() {
		return this.divisa.valorEnEstaDivisa(this.cantidad/100, this.divisa);
	}
	
	/**
	 * Chequea si el valor universal de otro objeto Money es equivalente al actual
	 * @param Objeto Money a comparar
	 * @return Boolean indicando equivalencia
	 */
	public Boolean equals(Money otra) {
		return otra.valorUniversal() == this.valorUniversal();
	}
	
	/**
	 * Añade un objeto Money al actual
	 * @param other Objeto a ñañadir.
	 * @return Un nuevo objeto Money con la divisa del actual, y la cantidad acumulada con el importe del objeto Money recibido.
	 **/
	public Money add(Money otra) {
		Money mo = new Money(this.divisa.valorEnEstaDivisa(otra.cantidad, otra.divisa)+this.cantidad,this.divisa);
		return mo;
	}

		
	/**
	 * 
	 * @return True si cantidad es cero, false e.o.c.
	 */
	public Boolean isZero() {
		return this.cantidad==0;

	}
	/**
	 * Convierte la cantidad del objeto actual a negativo
	 * @return Un nuevo objeto Money con la cantidad en negativo
	 */
	public Money negate() {
		Money mo = new Money(-this.cantidad, this.divisa);
		return mo;
	}
	
	/**
	 * Compara dos objetos Money
	 * @return 0  si sus valores son iguales
	 * Un entero negativo si el objeto actual tiene menos valor que el recibido
	 * Un entero positivo si el objeto actual tiene más valor que el recibido
	 */
	public int compareTo(Object otra) {
		Money mo = (Money)otra;
		int valor_otra = mo.divisa.valorEnEstaDivisa(mo.cantidad, mo.divisa);
		int valor_esta = (int) (this.cantidad *this.divisa.getRate());
		if(valor_otra > valor_esta) {
			return -1;
		}
		else if(valor_otra < valor_esta) {
			return +1;
		}
		else {
			return 0;
		}
		
	}
}