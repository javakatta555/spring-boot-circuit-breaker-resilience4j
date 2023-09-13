package com.example.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class ProductController {

    @GetMapping("/product")
    @CircuitBreaker(name="product",fallbackMethod = "fallback")
    public String getProduct(){
        log.info("inside get product");
        return new RestTemplate().getForObject("http://localhost:8080/testing", String.class);
    }

    private String fallback(Throwable e) {

        System.out.println("Exception happened : " + e.getMessage());
        return "Handled the exception through fallback method";
    }
}
