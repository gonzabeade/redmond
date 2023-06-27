package ar.edu.itba.bd2.redmond;

import ar.edu.itba.bd2.redmond.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/")
public class HelloController {

    private final SampleService sampleService;

    @Autowired
    public HelloController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping
    public String helloWorld() {
        sampleService.sampleServiceCall(9);
        return "Hello, World!";
    }
}