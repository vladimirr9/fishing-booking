package com.project.fishingbookingback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class FishingBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FishingBookingApplication.class, args);
    }


    @RestController
    @RequestMapping(value = "/api")
    public class TestController {
        @PreAuthorize("hasRole('CLIENT')")
        @GetMapping(value = "/")
        public ResponseEntity<String> test() {
            return ResponseEntity.ok("Hello World");
        }
    }

}
