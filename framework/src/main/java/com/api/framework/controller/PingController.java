package com.api.framework.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "Ping Service")
@RestController
public class PingController {

    @ApiOperation(value = "Ping")
    @GetMapping("/ping")
    public ResponseEntity<String> ping() { return ResponseEntity.ok("Hello, World!"); }
}
