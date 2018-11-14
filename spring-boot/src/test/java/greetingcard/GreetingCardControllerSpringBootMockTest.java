package greetingcard;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;

/**
 * Starts the whole application
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GreetingCardControllerSpringBootMockTest {

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
