package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Greeting create(@RequestParam(value="name", defaultValue="World") String name) {
        return greetingService.create(name);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/greeting/{id}")
    public Greeting find(@PathParam("id") Long id) {
        return greetingService.find(id);
    }

}