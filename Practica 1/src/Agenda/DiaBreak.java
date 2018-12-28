package Agenda;

public class DiaBreak {

	    public static final int PRIMERA_CITA = 9;
	    public static final int ULTIMA_CITA = 17;
	    public static final int MAX_CITAS_POR_DIA =	ULTIMA_CITA - PRIMERA_CITA +1;//a�adimos +1 test asignarcita
	    

	    private int diaNumero;
	    private Cita[] citas;

	    /**
	     * @param diaNumero numero del dia del a�o (1-365), si es correcto
	     */
	    public DiaBreak(int diaNumero) throws DatoException{
	    
	    	if (diaNumero <= 0 || diaNumero > 365){//(diaNumero < 0 || diaNumero >= 365)testDiaBreak, and exceptions
	    		
	    		throw new DatoException("La semana debe tomar valor entre 1 y 365");
	    	}
	        this.diaNumero = diaNumero;
	        citas = new Cita[MAX_CITAS_POR_DIA];
	    }

	    /**
	     * Busca hueco para una cita
	     * @param cita 
	     * @return Hora m�s temprana para adjudicar la cita
	     *         Devuelve -1 si no es posible encontrar hueco
	     */ 
	    public int buscaSlot(int duracion)
	    
	    {   int hora;    
	    	int slot = 0, sigSlot;
	    	while (slot < MAX_CITAS_POR_DIA){ 
	    		if(citas[slot] == null) {
	            	hora = slot + PRIMERA_CITA;// a�adido + PRIMERA_CITA test buscaslot
	                if(duracion == 1) {
	                      return hora;
	                }
	                else {
	                   
	                	int numSlots = duracion;
	                	sigSlot= slot + 1;
	                	while (numSlots > 0 && sigSlot < MAX_CITAS_POR_DIA && citas[sigSlot] == null )
	                     { numSlots--; }

	                    if(numSlots == 0) {
	                        return hora;
	                    }
	                    else {
	                    	slot= sigSlot;
	                    }
	                }
	             }
	    	 slot++;
	        }
	    	
	    	return -1;
	    }
	        
	        

	    /**
	     * asignarCita.
	     * @param Hora Inicio
	     * @param cita
	     * @return true si se asigno la cita,
	     *         false en otro caso.
	     */
	    public boolean asignarCita(int hora, Cita cita)
	    {
	        if(validaHora(hora) && huecoLibre(hora, cita.getDuracion())) {
	            int horaIni = hora - PRIMERA_CITA;
	            if(citas[horaIni] == null) {//citas[this.diaNumero] == null test asirganr cita and debug daba excepcion de salirse del array
	                int duracion = cita.getDuracion();
	                for(int i = 0; i < duracion; i++) {
	                    citas[horaIni + i] = cita;
	                }
	                return true;
	            }
	            else {
	                return false;
	            }
	        }
	        else {
	            return false;
	        }
	    }
	    
	    /**
	     * @param hora de inicio de cita
	     * @return La cita asignada a esa hora. Devuelve null si la hora no 
	     *         es valida o no existe cita a la hora indicada
	     */
	    public Cita getCita(int hora)
	    {
	        if(validaHora(hora)) {
	            return citas[hora - PRIMERA_CITA];
	        }
	        else {
	            return null;
	        }
	    }

	    /**
	     * String que devuelve la descripci�n de la cita solicitada .
	     */
	    
	    public String muestraCita(int hora){      
	    	
	    	if (validaHora(hora)) { 
	    	    int horaIni = hora - PRIMERA_CITA; 
	            if(citas[horaIni] != null) {
	                return hora + ":00 " + citas[horaIni].getDescripcion();
	            }
	    	
	            else {
	                return "No existe";
	            }
	        }
	    	else {
                return "Hora no valida";// Test muestra cita; a�adido no
            }
	    }

	    /**
	     * @return Numero de dia dentro del a�o (1-366).
	     */
	    public int getDiaNumero()
	    {
	        return diaNumero;
	    }
	    
	    /**
	     * @return true si la hora esta en el intervalo valido
	     *         false en otro caso.
	     */
	    public boolean validaHora(int hora)
	    {
	        return hora >= PRIMERA_CITA && hora <= ULTIMA_CITA;
	    }
	    
	    public boolean huecoLibre(int hora, int duracion) {
	    	
	    	int horaIni = hora - PRIMERA_CITA;
	    	if( hora + duracion > 18){//a�adido todo el if
        		return false;
        	}
            if(citas[horaIni] == null) {
            	duracion--;
	            for(int slot=horaIni+1; duracion > 0 && slot < MAX_CITAS_POR_DIA ; slot++) {
	          	  if (citas[slot] == null) {duracion--;
	          	  slot++;//a�adido slot++
	          	  }
	          	  else {//hemos a�adido todo el else, testhuecolibre
	          		  return false;
	          	  }
	          	  	}
   
              return true;
            }
            else {return false;}
	    }
	    
	    
}
