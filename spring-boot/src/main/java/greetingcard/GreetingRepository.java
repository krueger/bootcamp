package greetingcard;

import org.springframework.data.repository.CrudRepository;

public interface GreetingRepository extends CrudRepository<GreetingCard, Long> {
}
