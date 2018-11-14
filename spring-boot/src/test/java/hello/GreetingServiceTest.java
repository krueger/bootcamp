package hello;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = GreetingServiceTest.Initializer.class)
public class GreetingServiceTest {

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

        Greeting greeting = greetingService.create(name);

        assertThat(greeting.getContent()).isEqualTo("Hello, Martin!");
    }

    @Test
    public void whenCreatingGreeting_thenIdIs1() {
        String name = "Martin";

        Greeting greeting = greetingService.create(name);

        assertThat(greeting.getId()).isEqualTo(1L);
    }

    @Test
    public void whenGreetingIsCreated_thenGreetingCanBeFoundByItsId() {
        String name = "Martin";
        Greeting greeting = greetingService.create(name);

        Greeting findGreeting = greetingService.find(greeting.getId());

        assertThat(findGreeting.getId()).isEqualTo(greeting.getId());
        assertThat(findGreeting.getContent()).isEqualTo(greeting.getContent());
    }
}