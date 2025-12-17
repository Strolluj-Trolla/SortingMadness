package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MeasurerTest {

    private Measurer measurer;

    @BeforeEach
    void setUp() {
        measurer = new Measurer();
    }

    @Test
    void shouldReturnErrorWhenInputDataIsEmpty() {
        // given
        Cell[][] data = new Cell[0][0];

        // when
        List<Result> results = measurer.measure(
                data, 0, List.of("bubble"), -1, SortAlgorithm.Order.RISING);

        // then
        assertTrue(results.get(0).isError());
    }

    @Test
    void shouldReturnErrorForUnknownAlgorithm() {
        // given
        Cell[][] data = {
                { new Cell(1) }, { new Cell(2) }
        };

        // when
        List<Result> results = measurer.measure(
                data, 0, List.of("unknown"), -1, SortAlgorithm.Order.RISING);

        // then
        assertTrue(results.get(0).isError());
        assertEquals("Algorithm not recognized", results.get(0).getErrMessage());
    }

    @Test
    void shouldReturnSortedDataForValidAlgorithm() {
        // given
        Cell[][] data = {
                { new Cell(3) }, { new Cell(1) }, { new Cell(2) }
        };

        // when
        List<Result> results = measurer.measure(
                data, 0, List.of("merge"), -1, SortAlgorithm.Order.RISING);

        // then
        assertEquals(1, results.size());

        Result result = results.get(0);
        assertFalse(result.isError());
        assertTrue(result.isComplete());

        Cell[][] sorted = result.getData();
        assertEquals(1, sorted[0][0].getNum());
        assertEquals(2, sorted[1][0].getNum());
        assertEquals(3, sorted[2][0].getNum());
    }
}
