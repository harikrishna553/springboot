package com.sample.app.controller;

import java.net.URI;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RedirectController {

    @GetMapping("/redirect")
    public ResponseEntity<Void> redirect() {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("https://self-learning-java-tutorial.blogspot.com/"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}