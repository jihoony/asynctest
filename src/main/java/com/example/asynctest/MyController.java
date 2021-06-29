package com.example.asynctest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MyController {

    private AsyncService asyncService;

    public MyController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/")
    public ResponseEntity<String> callAsync(){
        asyncService.eventNotificationAsync();

        return ResponseEntity.ok("success");
    }
}
