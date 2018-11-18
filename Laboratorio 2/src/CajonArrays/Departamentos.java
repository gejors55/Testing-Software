package CajonArrays;

import java.util.HashMap;

public class Departamentos {

	private static Departamentos dept;
	
	private HashMap<String, String> lista = new HashMap<String, String>();

	public Departamentos() {
		
		lista.put("quim", "Química");
		lista.put("geo", "Geografía");
		lista.put("inf", "Informática");
		lista.put("leng", "Lengua");
		lista.put("mate", "Matemáticas");
		lista.put("fisic", "Física");
	}
	
	public static Departamentos getInstance() {
		if (dept == null){ dept = new Departamentos(); }
        return dept;
	}
	
	public String getDept(String dom) {
		return lista.get(dom);
	}
	
}
