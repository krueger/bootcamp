package hello;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingControllerSpringBootMockTest {

    @Autowired
    private GreetingController greetingController;

    @MockBean
    private GreetingService greetingService;

    @Test
    public void whenApplicationIsStarted_thenGreetingControllerInAvailableInTheSpringContext() {
        Assertions.assertThat(greetingController).isNotNull();
    }

    @Test
    public void whenCreateIsCalled_thenCallIsForwardedToGreetingService() {
        greetingController.create("name");

        verify(greetingService).create("name");
    }

    @Test
    public void whenFindIsCalled_thenCallIsForwardedToGreetingService() {
        long id = 33L;

        greetingController.find(id);

        verify(greetingService).find(id);
    }
}
