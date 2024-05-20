package org.guilin.micrometer.controller;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class GaugeMetricsController {

    private final AtomicInteger myGauge = new AtomicInteger();

    public GaugeMetricsController(MeterRegistry registry) {
        Gauge.builder("guilin.gauge", myGauge, AtomicInteger::get)
                .description("Gauges something")
                .tags("vickie", "us-east")
                .register(registry);
    }

    @GetMapping("/setGauge")
    public String setGauge(@RequestParam int value) {
        myGauge.set(value);
        return "Gauge set to: " + value;
    }

    // Add an endpoint to set the gauge to a sample value
    @GetMapping("/setGaugeSample")
    public String setGaugeSample() {
        myGauge.set(18);
        return "Gauge set to sample value: 18";
    }
}
