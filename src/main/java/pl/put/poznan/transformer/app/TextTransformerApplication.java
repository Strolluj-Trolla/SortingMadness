package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.logic.Sorter;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    public static void main(String[] args) {
        double[] nums={2, 45,32, 43,1, 2, 4};
        Sorter.insertSort(nums);
        for (double num : nums) {
            System.out.println(num);
        }
        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
