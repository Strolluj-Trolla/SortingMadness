package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the SpringBoot application.
 * Takes REST controllers from the {@link pl.put.poznan.transformer.rest} package.
 */

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class SortingMadnessApplication {

    /**
     * Main runner for the application.
     *
     * @param args arguments to be passed o the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(SortingMadnessApplication.class, args);
    }
}
