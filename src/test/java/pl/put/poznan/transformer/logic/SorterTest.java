package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class SorterTest {
    private Sorter sorter;
    Cell[][] cells;

    @BeforeEach
    void setUp() {
        sorter = new Sorter();
        this.cells= new Cell[][]{{new Cell(2), new Cell("jajko")},
                {new Cell(1), new Cell("jabłko")},
                {new Cell(5), new Cell("banan")},
                {new Cell(8), new Cell("gruszka")},
                {new Cell(-3), new Cell("śliwka")},
                {new Cell(0), new Cell("truskawka")}};
    }

    @Test
    void sortRisingNums() {
        Cell[][] cmp={{new Cell(-3), new Cell("śliwka")},
                {new Cell(0), new Cell("truskawka")},
                {new Cell(1), new Cell("jabłko")},
                {new Cell(2), new Cell("jajko")},
                {new Cell(5), new Cell("banan")},
                {new Cell(8), new Cell("gruszka")}};
        SortAlgorithm mock=mock(SortAlgorithm.class);
        when(mock.sort(this.cells, 0, -1, SortAlgorithm.Order.RISING)).thenAnswer(
                (Answer<Boolean>)invocation->{
                    this.cells=cmp;
                    return true;
                });
        sorter.setStrategy(mock);

        boolean complete = sorter.sort(this.cells, 0, -1, SortAlgorithm.Order.RISING);

        assertTrue(complete);
        assertArrayEquals(cmp, this.cells);
    }

    @Test
    void sortIncomplete() {
        SortAlgorithm mock=mock(SortAlgorithm.class);
        when(mock.sort(any(Cell[][].class), anyInt(), eq(-2), any(SortAlgorithm.Order.class))).thenReturn(false);
        sorter.setStrategy(mock);

        boolean complete = sorter.sort(this.cells, 0, -2, SortAlgorithm.Order.RISING);

        assertFalse(complete);
    }

    @Test
    void sortThrowsIndexOutOfRange() {
        SortAlgorithm mock=mock(SortAlgorithm.class);
        when(mock.sort(any(Cell[][].class), eq(-1), anyInt(), any(SortAlgorithm.Order.class))).thenThrow(IndexOutOfBoundsException.class);
        sorter.setStrategy(mock);
        assertThrows(IndexOutOfBoundsException.class, ()->sorter.sort(this.cells, -1, -1, SortAlgorithm.Order.RISING));
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
        Cell[][] cmp={{new Cell(-3), new Cell("śliwka")},
                {new Cell(0), new Cell("truskawka")},
                {new Cell(1), new Cell("jabłko")},
                {new Cell(2), new Cell("jajko")},
                {new Cell(5), new Cell("banan")},
                {new Cell(8), new Cell("gruszka")}};
        MergeSort mockMerge=mock(MergeSort.class);
        when(mockMerge.sort(this.cells, 0, -1, SortAlgorithm.Order.RISING)).thenAnswer(
                (Answer<Boolean>)invocation->{
                    this.cells=cmp;
                    return true;
                });
        Cell[][]orig=new Cell[this.cells.length][];
        for (int i = 0; i < this.cells.length; i++) {
            orig[i] = new Cell[this.cells[i].length];
            System.arraycopy(this.cells[i], 0, orig[i], 0, this.cells[i].length);
        }
        Cell[][] cells2;
        BubbleSort mockBubble=mock(BubbleSort.class);
        when(mockBubble.sort(any(Cell[][].class), anyInt(), anyInt(), any(SortAlgorithm.Order.class))).thenAnswer(
                (Answer<Boolean>)invocation->{
                    this.cells=cmp;
                    return true;
                });

        sorter.setStrategy(mockMerge);
        boolean complete1 = sorter.sort(this.cells, 0, -1, SortAlgorithm.Order.RISING);
        cells2=new Cell[this.cells.length][];
        for (int i = 0; i < this.cells.length; i++) {
            cells2[i] = new Cell[this.cells[i].length];
            System.arraycopy(this.cells[i], 0, cells2[i], 0, this.cells[i].length);
        }
        this.cells=orig;
        sorter.setStrategy(mockBubble);
        boolean complete2 = sorter.sort(this.cells, 0, -1, SortAlgorithm.Order.RISING);

        // then
        assertTrue(complete1);
        assertArrayEquals(cmp, cells2);
        assertTrue(complete2);
        assertArrayEquals(cmp, this.cells);
    }

}
