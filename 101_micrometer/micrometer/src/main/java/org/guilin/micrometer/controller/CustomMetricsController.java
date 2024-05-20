package org.guilin.micrometer.controller;

import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomMetricsController {

    private final DistributionSummary summary;

    public CustomMetricsController(MeterRegistry registry) {
        this.summary = DistributionSummary.builder("guilin.custom.summary")
                .description("A custom distribution summary")
                .tags("ju", "ap-southeast-2")
                .register(registry);
    }

    @GetMapping("/recordSummary")
    public String recordSummary(@RequestParam double amount) {
        summary.record(amount);
        return "Recorded summary value: " + amount;
    }

    // Add an endpoint to record multiple summary values
    @GetMapping("/recordMultipleSummary")
    public String recordMultipleSummary() {
        summary.record(1.0);
        summary.record(2.0);
        summary.record(3.0);
        summary.record(4.0);
        summary.record(5.0);
        return "Recorded multiple summary values: 1, 2, 3, 4, 5";
    }
}
