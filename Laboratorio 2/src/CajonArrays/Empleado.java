package CajonArrays;


public class Empleado {

	private String DNI;
	private String nombre;
	private String email;
	private int edad;
	private String sexo;

	public Empleado(String mat, String nombre, String email, int edad, String sexo) {
		
		this.DNI=mat;
		this.nombre=nombre;
		this.email=email;
		this.edad=edad;
		this.sexo=sexo;
	}
	
	public String getDept() {
		
		String[] mailDom = email.split("@");
		String dom = mailDom[1];
		Departamentos tmp = Departamentos.getInstance( );
		return tmp.getDept(dom);
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	}
