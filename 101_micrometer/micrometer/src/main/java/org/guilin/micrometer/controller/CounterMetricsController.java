package org.guilin.micrometer.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterMetricsController {

    private final Counter myCounter;

    public CounterMetricsController(MeterRegistry registry) {
        myCounter = Counter.builder("guilin.counter")
                .description("Counts something")
                .tags("wei", "us-east")
                .register(registry);
    }

    @GetMapping("/increment")
    public String incrementCounter() {
        myCounter.increment();
        return "Counter incremented";
    }

    // Add an endpoint to increment the counter multiple times
    @GetMapping("/incrementMultiple")
    public String incrementCounterMultiple() {
        for (int i = 0; i < 10; i++) {
            myCounter.increment();
        }
        return "Counter incremented 10 times";
    }
}
