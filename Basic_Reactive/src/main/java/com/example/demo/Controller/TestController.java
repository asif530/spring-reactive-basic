package com.example.demo.Controller;

import com.example.demo.Reactive.Reactive;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @GetMapping("/hello2")
    public String index2() {
        Reactive reactive = new Reactive();
        reactive.reactiveAction();
        return "hello";
    }
}
