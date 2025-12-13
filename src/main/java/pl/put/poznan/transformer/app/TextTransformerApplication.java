package pl.put.poznan.transformer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.put.poznan.transformer.logic.BubbleSort;
import pl.put.poznan.transformer.logic.Cell;
import pl.put.poznan.transformer.logic.Sorter;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.transformer.rest"})
public class TextTransformerApplication {

    public static void main(String[] args) {
        double[] nums={2, 45,32, 43,1, 2, 4};
        int[] countNums={2, 45,32, 43,1, 2, 4};
        Sorter.quickSort(nums);
        BubbleSort bubbleSort=new BubbleSort();
        Cell[][] cells={{new Cell(2)}, {new Cell(45)}, {new Cell(32)}, {new Cell(43)},{new Cell(1)}, {new Cell(2)}, {new Cell(4)}};
        bubbleSort.sort(cells, 0);
        for (double num:nums) {
            System.out.println(num);
        }
        Sorter.countingSort(countNums);
        for(int num:countNums) {
            System.out.println(num);
        }
        for (Cell[] cell:cells) {
            System.out.println(cell[0].num);
        }
        cells= new Cell[][]{{new Cell("ab")}, {new Cell("ba")}, {new Cell("b")}, {new Cell("3")}};
        bubbleSort.sort(cells, 0);
        for (Cell[] cell:cells) {
            System.out.println(cell[0].str);
        }

        SpringApplication.run(TextTransformerApplication.class, args);
    }
}
