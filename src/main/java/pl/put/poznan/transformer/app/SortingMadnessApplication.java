package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the SpringBoot application.
 * Takes REST controllers from the <a href="#{@link}">@link pl.put.poznan.transformer.rest</a> package.
 */

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class SortingMadnessApplication {

    /**
     * Main runner for the application.
     * @param args Arguments to be passed o the apllication.
     */
    public static void main(String[] args) {
        SpringApplication.run(SortingMadnessApplication.class, args);
    }
}
