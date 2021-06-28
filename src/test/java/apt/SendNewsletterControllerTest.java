package apt;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

class SendNewsletterControllerTest {

    @Test
    public void sendNewsletter_shouldSendEmails() throws SQLException, ClassNotFoundException {
        ISmtpService smtpService = spy(ISmtpService.class);
        TestController controller = new TestController(smtpService);
        when(controller.customerService.getCustomersToReceiveMailout()).thenReturn(new ArrayList<Customer>() {{
            add(new Customer("John", "john@doe.com", true, 0, 0, 0));
            add(new Customer("Joe", "joe@bloggs.com", true, 0, 0, 0));
            add(new Customer("Jane", "jane@bloggs.com", true, 0, 0, 0));
        }});
        when(controller.contentService.getMailout("testMailoutName")).thenReturn(
                new Mailout(null, "testSubject", "Hello {firstName}"));

        controller.sendNewsletter("testMailoutName");

        verify(smtpService).sendMail("john@doe.com", "testSubject", "Hello John");
        verify(smtpService).sendMail("joe@bloggs.com", "testSubject", "Hello Joe");
        verify(smtpService).sendMail("jane@bloggs.com", "testSubject", "Hello Jane");

        verifyNoMoreInteractions(smtpService);
    }

    private static class TestController extends SendNewsletterController {
        public IMailoutContentService contentService;
        public ICustomerService customerService;

        public TestController(ISmtpService smtpService) {
            super(smtpService);
            contentService = mock(IMailoutContentService.class);
            customerService = mock(ICustomerService.class);
        }

        @Override
        protected IMailoutContentService getContentService() {
            return contentService;
        }

        @Override
        protected ICustomerService getCustomerService() {
            return customerService;
        }
    }
}