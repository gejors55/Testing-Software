package CajonArrays;

import java.util.HashMap;

public class Departamentos {

	private static Departamentos dept;
	
	private HashMap<String, String> lista = new HashMap<String, String>();

	public Departamentos() {
		
		lista.put("quim", "Qu�mica");
		lista.put("geo", "Geograf�a");
		lista.put("inf", "Inform�tica");
		lista.put("leng", "Lengua");
		lista.put("mate", "Matem�ticas");
		lista.put("fisic", "F�sica");
	}
	
	public static Departamentos getInstance() {
		if (dept == null){ dept = new Departamentos(); }
        return dept;
	}
	
	public String getDept(String dom) {
		return lista.get(dom);
	}
	
}
