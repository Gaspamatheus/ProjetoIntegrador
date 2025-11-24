package com.projeto.integrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/clientes")
    public String clientes() {
        return "clientes";
    }

    @GetMapping("/garagens")
    public String garagens() {
        return "garagens";
    }
}
