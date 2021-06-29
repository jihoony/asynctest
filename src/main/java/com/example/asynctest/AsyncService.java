package com.example.asynctest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {

    private HTTPTestClient httpTestClient;

    public AsyncService(HTTPTestClient httpTestClient) {
        this.httpTestClient = httpTestClient;
    }

    public void callBack(String args){
        log.debug(args);
    }

    @Async("eventNotificationTaskExecutor")
    public CompletableFuture<String> eventNotificationAsync(){
        CompletableFuture<String> completedFuture = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e){
                log.error(e.getMessage(), e);
            }

            final ResponseEntity<String> stringResponseEntity = httpTestClient.callUrl();

            if(stringResponseEntity.getStatusCode() == HttpStatus.OK) {
                callBack(stringResponseEntity.getBody());
            }

            return "finished";
        });

        return completedFuture;
    }
}
