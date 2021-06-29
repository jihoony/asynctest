package com.example.asynctest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "testServer", url = "https://www.google.com")
public interface HTTPTestClient {

    @GetMapping("/")
    ResponseEntity<String> callUrl();
}
