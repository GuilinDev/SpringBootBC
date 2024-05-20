package org.guilin.micrometer.controller;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimerMetricsController {

    private final Timer myTimer;

    public TimerMetricsController(MeterRegistry registry) {
        myTimer = Timer.builder("guilin.timer")
                .description("Times something")
                .tags("yang", "us-west")
                .register(registry);
    }

    @GetMapping("/time")
    public String recordTime() {
        myTimer.record(() -> {
            try {
                Thread.sleep((long) (Math.random() * 1000)); // Simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return "Timed a random event";
    }

    // Add an endpoint to record multiple timed events
    @GetMapping("/timeMultiple")
    public String recordMultipleTimes() {
        myTimer.record(() -> {
            try {
                Thread.sleep(500); // Simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        myTimer.record(() -> {
            try {
                Thread.sleep(1000); // Simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        myTimer.record(() -> {
            try {
                Thread.sleep(1500); // Simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return "Timed multiple random events";
    }
}
