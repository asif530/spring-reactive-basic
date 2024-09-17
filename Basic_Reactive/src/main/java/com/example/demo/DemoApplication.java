package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.time.Duration;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DemoApplication.class, args);
		// Simulate a long-running search operation
		Flux<String> searchResults = Flux.just("apple", "banana", "orange","apple1", "banana1", "orange1")
				.concatWith(delaySearchResults()); // Simulate delay

		// Subscribe to the stream and print results without blocking
		searchResults.flatMap(result -> System.out.println("Found: " + result));
		// Main thread continues execution without waiting for search to complete
		System.out.println("Search initiated...");
		doSomeWork(500); // Simulate work for 500 milliseconds

		System.out.println("Doing some other work...");
	}

	private static void doSomeWork(long millis) throws InterruptedException {
		Thread.sleep(millis);
	}

	private static Flux<String> delaySearchResults() {
		try{
			Thread.sleep(5000);
		}catch (Exception e){}

		return Flux.interval(Duration.ofDays(5)).
				collectList()
				.flatMapMany(ignored -> Flux.just("mango", "pineapple"));
	}

}
