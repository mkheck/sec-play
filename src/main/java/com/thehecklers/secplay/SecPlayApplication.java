package com.thehecklers.secplay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class SecPlayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecPlayApplication.class, args);
    }
}

@Controller
@RequestMapping("/")
class PlayController {
    @GetMapping
    private String getRoot() {
        return "index.html";
    }

    @GetMapping("/app")
    private String getAll() {
        return "app.html";
    }
}
