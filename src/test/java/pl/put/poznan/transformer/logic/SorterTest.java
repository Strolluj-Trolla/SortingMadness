package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {

    private Sorter sorter;

    @BeforeEach
    void setUp() {
        sorter = new Sorter();
    }

    @Test
    void shouldSortInRisingOrderUsingBubbleSort() {
        // given
        Cell[][] data = cells(3, 1, 2);
        sorter.setStrategy(new BubbleSort());

        // when
        boolean complete = sorter.sort(data, 0, -1, SortAlgorithm.Order.RISING);

        // then
        assertTrue(complete);
        assertArrayEquals(new int[]{1, 2, 3}, extract(data));
    }

    @Test
    void shouldReturnIncompleteWhenIterationsLimitReached() {
        // given
        Cell[][] data = cells(3, 2, 1);
        sorter.setStrategy(new QuickSort());

        // when
        boolean complete = sorter.sort(data, 0, 0, SortAlgorithm.Order.RISING);

        // then
        assertFalse(complete);
    }

    @Test
    void countingSortShouldSortDescending() {
        // given
        int[] convData = {1, 3, 2};

        // when
        boolean complete = Sorter.countingSort(convData, SortAlgorithm.Order.FALLING);

        // then
        assertTrue(complete);
        assertArrayEquals(new int[]{3, 2, 1}, convData);
    }

    @Test
    void countingSortShouldThrowExceptionForNegativeValues() {
        // given
        int[] data = {3, -1, 2};

        // when + then
        assertThrows(IllegalArgumentException.class,
                () -> Sorter.countingSort(data, SortAlgorithm.Order.RISING)
        );
    }

    @Test
    void shouldAllowChangingSortingStrategy() {
        // given
        Cell[][] data = cells(2, 1);

        sorter.setStrategy(new SelectionSort());

        // when
        boolean complete = sorter.sort(data, 0, -1, SortAlgorithm.Order.RISING);

        // then
        assertTrue(complete);
        assertArrayEquals(new int[]{1, 2}, extract(data));
    }

    // ===== helpers =====

    private Cell[][] cells(int... values) {
        Cell[][] data = new Cell[values.length][1];
        for (int i = 0; i < values.length; i++) {
            data[i][0] = new Cell(values[i]);
        }
        return data;
    }

    private int[] extract(Cell[][] data) {
        int[] result = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = data[i][0].getNum().intValue();
        }
        return result;
    }
}
