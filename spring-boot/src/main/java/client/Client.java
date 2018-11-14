package client;

import com.google.common.collect.ImmutableMap;
import greetingcard.GreetingCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class Client {

    private static final Logger log = LoggerFactory.getLogger(Client.class);

    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        Map parameter = ImmutableMap.of("name", "Martin");
        GreetingCard greetingCard = restTemplate.postForObject("http://localhost:8080/greetingCard?name={name}", null, GreetingCard.class, parameter);
        log.info(greetingCard.getContent());
    }

}