import org.junit.Test;
import org.kohsuke.stapler.Stapler;
import org.kohsuke.stapler.StaplerRequest;
import org.mockito.Mockito;
import javax.servlet.http.HttpSession;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class OpenIdSessionTest {

    @Test
    public void testSessionInvalidation() throws Exception {
        // Mock the StaplerRequest and HttpSession
        StaplerRequest mockRequest = mock(StaplerRequest.class);
        HttpSession mockSession = mock(HttpSession.class);

        // Set up the Stapler to return the mock request
        Stapler.setCurrentRequest(mockRequest);
        when(mockRequest.getSession()).thenReturn(mockSession);

        // Create an instance of OpenIdSession
        OpenIdSession session = new OpenIdSession(null, null, "finishUrl") {
            @Override
            protected HttpResponse onSuccess(Identity identity) {
                return null;
            }
        };

        // Call the method to test
        session.doCommenceLogin();

        // Verify that the session was invalidated
        verify(mockSession).invalidate();
    }
}
