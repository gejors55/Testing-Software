package Cajon;

public class Boa {
	
	private String nombre;
	private int longitud; // en pies
	private String comidaFavorita;
	
	public Boa (String nombre, int longitud, String comidaFavorita){
		this.nombre = nombre;
		this.longitud = longitud;
		this.comidaFavorita = comidaFavorita;
	}
	
	public boolean esSana(){
		return this.comidaFavorita.equals("granola");
	}
	
}