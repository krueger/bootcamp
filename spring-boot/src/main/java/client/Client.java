package client;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import hello.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class Client {

    private static final Logger log = LoggerFactory.getLogger(Client.class);

    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        Map parameter = ImmutableMap.of("name", "Martin");
        Greeting greeting = restTemplate.postForObject("http://localhost:8080/greeting?name={name}", null, Greeting.class, parameter);
        log.info(greeting.getContent());
    }

}