package com.shalev.phoenix.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Ping {
    @GetMapping("/**")
    public String fallback() {
        return "Phoneix API App is Running!";
    }
}
