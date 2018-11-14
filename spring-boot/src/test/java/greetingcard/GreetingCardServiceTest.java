package greetingcard;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.GenericContainer;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration(initializers = GreetingCardServiceTest.Initializer.class)
public class GreetingCardServiceTest {

    @ClassRule
    public static GenericContainer redis = new GenericContainer<>("redis:latest").withExposedPorts(6379);

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "spring.redis.host=" + redis.getContainerIpAddress(),
                    "spring.redis.port=" + redis.getMappedPort(6379)
            );
            values.applyTo(configurableApplicationContext);
        }
    }

    @Autowired
    private GreetingService greetingService;

    @Test
    public void whenCreatingGreeting_thenContentContainsHelloMartin() {
        String name = "Martin";

        GreetingCard greetingCard = greetingService.create(name);

        assertThat(greetingCard.getContent()).isEqualTo("Hello, Martin!");
    }

    @Test
    public void whenCreatingGreeting_thenIdIs1() {
        String name = "Martin";

        GreetingCard greetingCard = greetingService.create(name);

        assertThat(greetingCard.getId()).isEqualTo(1L);
    }

    @Test
    public void whenGreetingIsCreated_thenGreetingCanBeFoundByItsId() {
        String name = "Martin";
        GreetingCard greetingCard = greetingService.create(name);

        GreetingCard findGreetingCard = greetingService.find(greetingCard.getId());

        assertThat(findGreetingCard.getId()).isEqualTo(greetingCard.getId());
        assertThat(findGreetingCard.getContent()).isEqualTo(greetingCard.getContent());
    }
}