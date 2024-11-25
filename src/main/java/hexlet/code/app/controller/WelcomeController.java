package hexlet.code.app.controller;


import hexlet.code.app.exception.EntityIsConnectedToOthers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping(path = "/welcome")
    public String welcome() {
        throw new EntityIsConnectedToOthers("tuut");
        //return "Welcome to Spring!";
    }
}
