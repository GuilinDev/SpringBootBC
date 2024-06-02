package org.guilin.micrometer.command;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class MetricsDataGenerator implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        generateMetricsData();
    }

    private void generateMetricsData() {
        String[] commands = {
                "curl http://localhost:8089/increment",
                "curl http://localhost:8089/incrementMultiple",
                "curl http://localhost:8089/recordSummary?amount=5",
                "curl http://localhost:8089/recordSummary?amount=3",
                "curl http://localhost:8089/recordMultipleSummary",
                "curl http://localhost:8089/setGauge?value=10",
                "curl http://localhost:8089/setGaugeSample",
                "curl http://localhost:8089/time",
                "curl http://localhost:8089/timeMultiple"
        };

        for (String command : commands) {
            executeCommand(command);
        }
    }

    private void executeCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
