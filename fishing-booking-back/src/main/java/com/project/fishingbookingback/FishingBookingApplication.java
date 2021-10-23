package com.project.fishingbookingback;

import com.project.fishingbookingback.model.ProviderRegistration;
import com.project.fishingbookingback.service.ProviderRegistrationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class FishingBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(FishingBookingApplication.class, args);
    }

    
    @RestController
    @RequestMapping(value = "/api")
    public class TestController {
        private ProviderRegistrationService providerRegistrationService;

        public TestController(ProviderRegistrationService providerRegistrationService) {
            this.providerRegistrationService = providerRegistrationService;
        }

        @PreAuthorize("permitAll()")
        @GetMapping(value = "/")
        public ResponseEntity<List<ProviderRegistration>> test() {
            return ResponseEntity.ok(providerRegistrationService.findAll());
        }
    }

}
