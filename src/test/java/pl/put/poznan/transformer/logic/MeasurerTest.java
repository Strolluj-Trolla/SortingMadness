package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MeasurerTest {

    private Measurer measurer;
    private Cell[][] cells;

    @Test
    void shouldReturnErrorWhenInputDataIsEmpty() {
        //given
        measurer = new Measurer();
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
        measurer = new Measurer();
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
        this.cells=new Cell[][]{
                { new Cell(3) }, { new Cell(1) }, { new Cell(2) }
        };
        Cell[][] cmp={
                { new Cell(1) }, { new Cell(2) }, { new Cell(3) }
        };
        Sorter mock=mock(Sorter.class);
        when(mock.sort(any(Cell[][].class), anyInt(), anyInt(), any(SortAlgorithm.Order.class))).thenAnswer(
                (Answer<Boolean>) invocation->{
                    Cell[][] data = (Cell[][]) invocation.getArguments()[0];
                    for (int i = 0; i < cmp.length; i++) {
                        data[i] = cmp[i].clone();
                    }
                    return true;
                });
        measurer = new Measurer(mock);

        // when
        List<Result> results = measurer.measure(
                this.cells, 0, List.of("merge"), -1, SortAlgorithm.Order.RISING);

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

    @Test
    void shouldReturnResultForEachAlgorithm() {
        // given
        this.cells=new Cell[][]{
                { new Cell(3) }, { new Cell(1) }, { new Cell(2) }
        };
        Sorter mock=mock(Sorter.class);
        when(mock.sort(any(Cell[][].class), anyInt(), anyInt(), any(SortAlgorithm.Order.class))).thenReturn(true);
        measurer = new Measurer(mock);

        // when
        List<Result> results = measurer.measure(
                this.cells, 0, List.of("merge", "quick", "selection"), -1, SortAlgorithm.Order.RISING);

        // then
        assertEquals(3, results.size());
    }

    @Test
    void shouldReturnIncompleteResult() {
        // given
        this.cells=new Cell[][]{
                { new Cell(3) }, { new Cell(1) }, { new Cell(2) }
        };
        Sorter mock=mock(Sorter.class);
        when(mock.sort(any(Cell[][].class), anyInt(), anyInt(), any(SortAlgorithm.Order.class))).thenReturn(false);
        measurer = new Measurer(mock);

        // when
        List<Result> results = measurer.measure(
                this.cells, 0, List.of("merge"), -2, SortAlgorithm.Order.RISING);

        // then
        assertEquals(1, results.size());

        Result result = results.get(0);
        assertFalse(result.isError());
        assertFalse(result.isComplete());
    }

    @Test
    void shouldReturnTimeInResult() {
        // given
        this.cells=new Cell[][]{
                { new Cell(3) }, { new Cell(1) }, { new Cell(2) }
        };
        Cell[][] cmp = {
                { new Cell(1) }, { new Cell(2) }, { new Cell(3) }
        };
        Sorter mock=mock(Sorter.class);
        when(mock.sort(any(Cell[][].class), anyInt(), anyInt(), any(SortAlgorithm.Order.class))).thenAnswer(
                (Answer<Boolean>) invocation->{
                    try{
                        Thread.sleep(1000);
                    }
                    catch(InterruptedException ex){
                        Thread.currentThread().interrupt();
                    }
                    this.cells=cmp;
                    return true;
                });
        measurer = new Measurer(mock);

        // when
        List<Result> results = measurer.measure(
                this.cells, 0, List.of("merge"), -1, SortAlgorithm.Order.RISING);

        // then
        assertEquals(1, results.size());

        Result result=results.get(0);
        double time=result.getTime();
        assertTrue(time>0);
    }

    @Test
    void shouldCatchExceptionForResult() {
        // given
        this.cells=new Cell[][]{
                { new Cell(3) }, { new Cell(1) }, { new Cell(2) }
        };
        Sorter mock=mock(Sorter.class);
        when(mock.sort(any(Cell[][].class), anyInt(), anyInt(), any(SortAlgorithm.Order.class))).thenThrow(
                IndexOutOfBoundsException.class);
        measurer = new Measurer(mock);

        // when
        List<Result> results = measurer.measure(
                this.cells, 0, List.of("merge"), -2, SortAlgorithm.Order.RISING);

        // then
        assertEquals(1, results.size());

        Result result = results.get(0);
        assertTrue(result.isError());
        assertEquals("Error while sorting: null", result.getErrMessage());
    }
}
