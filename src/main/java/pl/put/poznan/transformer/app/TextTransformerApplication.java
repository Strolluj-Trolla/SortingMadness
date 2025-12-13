package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.logic.*;

import java.util.List;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    public static void main(String[] args) {
        Cell[][] nums={{new Cell(2)}, {new Cell(45)}, {new Cell(32)}, {new Cell(43)},{new Cell(1)}, {new Cell(2)}, {new Cell(4)}};
        Cell[][] strings= new Cell[][]{{new Cell("ab")}, {new Cell("ba")}, {new Cell("b")}, {new Cell("3")}};
        Measurer measurer=new Measurer();
        List<Result> numRes=measurer.measure(nums, 0, List.of("bubble", "insertion", "binaryInsertion", "selection", "merge", "quick", "counting"));
        List<Result> strRes=measurer.measure(strings, 0, List.of("bubble", "insertion", "binaryInsertion", "selection", "merge", "quick", "counting"));

        Result.printResults(numRes);
        System.out.println();
        Result.printResults(strRes);

        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
