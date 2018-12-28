package A;


import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class MensajeTest {

	private TemplateEngine miengine  = mock(TemplateEngine.class);
	private MailServer miserver = mock(MailServer.class);
	private Cliente micliente = mock(Cliente.class);
	private Template templatee = mock(Template.class);
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);	
	}
	
	@Test
	void testSendMensaje() {
		Mensaje sms = new Mensaje(miserver, miengine);
		when(micliente.getEmail()).thenReturn("algo");
		when(miengine.preparaMensaje(templatee, micliente)).thenReturn("Hola señor Gonzalo");
		sms.sendMensaje(micliente, templatee);
		verify(miserver).send("algo","Hola señor Gonzalo");
	}

}
