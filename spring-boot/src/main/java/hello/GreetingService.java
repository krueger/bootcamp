package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    private final GreetingRepository greetingRepository;

    @Autowired
    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting create(String name) {
        Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
        greetingRepository.save(greeting);
        return greeting;
    }

    public Greeting find(long id) {
        Optional<Greeting> greetingOptional = greetingRepository.findById(id);
        return greetingOptional.orElseThrow(RuntimeException::new);
    }

}
