package com.fongfox.ticketmanagementsystem.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 * <p>
 * Hello Controller to test web
 * <p>
 * Version 1.0
 * Date: 7/3/2025
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE         AUTHOR       DESCRIPTION
 * -------------------------------------
 * 7/3/2025      FongFox      Create
 */
@RestController
@RequestMapping("api/")
public class HelloController {
    @GetMapping("Hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello World From Spring boot! (Exclude security)");
    }
}
