package com.example.demo.Reactive;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class Reactive {

    public void reactiveAction(){
        // Simulate a long-running search operation
        Flux<String> searchResults = Flux.just("apple", "banana", "orange")
                .concatWith(delaySearchResults(50000)); // Simulate delay

        // Subscribe to the stream and print results without blocking
        searchResults.subscribe(result -> System.out.println("Found: " + result));

        // Main thread continues execution without waiting for search to complete
        System.out.println("Search initiated...");
    }
    private static Flux<String> delaySearchResults(long delayMillis) {
        return Flux.interval(Duration.ofDays(delayMillis))
        .take(100000).collectList()
        .flatMapMany(ignored -> Flux.just("mango", "pineapple"));
    }

}
