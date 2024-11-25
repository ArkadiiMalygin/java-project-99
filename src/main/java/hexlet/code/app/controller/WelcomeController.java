package hexlet.code.app.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Welcome", description = "Just a simple method")
public class WelcomeController {
    @GetMapping(path = "/welcome")
    @Operation(summary = "I return only this string: <Welcome to Spring!>")
    public String welcome() {
        return "Welcome to Spring!";
    }
}
