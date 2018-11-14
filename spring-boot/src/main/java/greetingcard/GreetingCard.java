package greetingcard;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash
public class GreetingCard {

    @Id
    private long id;

    private String content;

    public GreetingCard() {
    }

    public GreetingCard(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}