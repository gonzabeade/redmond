package ar.edu.itba.bd2.redmond;

import ar.edu.itba.bd2.redmond.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/")
public class HelloController {

    private final UserService userService;

    @Autowired
    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String helloWorld() {
        return userService.registerUser("HelloWorldTX", "123456789", "tierra.gaia.lemma").getRedmondId();
    }
}