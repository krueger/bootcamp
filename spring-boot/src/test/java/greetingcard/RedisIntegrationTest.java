package greetingcard;

import org.junit.ClassRule;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;

@ContextConfiguration(initializers = RedisIntegrationTest.Initializer.class)
public class RedisIntegrationTest {

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

}
