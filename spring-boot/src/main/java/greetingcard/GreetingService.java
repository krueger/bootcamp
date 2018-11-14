package greetingcard;

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

    public GreetingCard create(String name) {
        GreetingCard greetingCard = new GreetingCard(counter.incrementAndGet(), String.format(template, name));
        greetingRepository.save(greetingCard);
        return greetingCard;
    }

    public GreetingCard find(long id) {
        Optional<GreetingCard> greetingOptional = greetingRepository.findById(id);
        return greetingOptional.orElseThrow(RuntimeException::new);
    }

}
