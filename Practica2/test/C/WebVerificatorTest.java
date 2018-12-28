package C;

import static org.mockito.Mockito.*;

import org.junit.*;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;


class WebVerificatorTest {
	private Logger log = mock(Logger.class);
	private Server server = mock(Server.class);
	private Web web = mock(Web.class);
	private Result re = mock(Result.class);
	WebVerificator ver;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);	
	}
	@Test
	void testCheckWeb() {
		ver = new WebVerificator(log);
		when(re.isOk()).thenReturn(true);
		when(server.connect(web)).thenReturn(re);
		ver.checkWeb(server, web);
		verify(log).registerWebisOk(web);
		when(re.isOk()).thenReturn(false);
		when(server.connect(web)).thenReturn(re);
		ver.checkWeb(server, web);
		verify(log).registerWebReturnedError(web, server.connect(web));
	}

}
