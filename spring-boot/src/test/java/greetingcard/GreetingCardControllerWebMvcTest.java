package greetingcard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Starts only the web layer. Services has to be mocked.
 */
@RunWith(SpringRunner.class)
@WebMvcTest
public class GreetingCardControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingService greetingService;

    @Test
    public void test() throws Exception {
        GreetingCard greetingCard = new GreetingCard(1, "Hello, World!");
        Mockito.when(greetingService.create(anyString())).thenReturn(greetingCard);
        mockMvc.perform(post("/greetingCard"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"content\":\"Hello, World!\"}"));
    }

}
