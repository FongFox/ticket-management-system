package com.fongfox.ticketmanagementsystem.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController
 * <p>
 * Provides business logic for managing employment details.
 * <p>
 * Version 1.0
 * Date: 7/12/2025
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE         AUTHOR       DESCRIPTION
 * -------------------------------------
 * 7/12/2025      User      Create
 */
@RestController
@RequestMapping("auth/ver-0.1")
public class AuthController {
    @PostMapping("login")
    public ResponseEntity<Void> login() {
        //todo here
        return ResponseEntity.ok(null);
    }
}
