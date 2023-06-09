package com.example.carandownerservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    @GetMapping
    String getAuthors() {
        return "Filip Wiśniewski & Szymon Bąk";
    }
}
