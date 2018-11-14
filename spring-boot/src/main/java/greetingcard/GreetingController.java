package greetingcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/greetingCard")
public class GreetingController {

    private GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @PostMapping
    public GreetingCard create(@RequestParam(value="name", defaultValue="World") String name) {
        return greetingService.create(name);
    }

    @GetMapping("/{id}")
    public GreetingCard find(@PathParam("id") Long id) {
        return greetingService.find(id);
    }

}