package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsertSortTest {
    private SortAlgorithm sort;
    Cell[][] cells;

    @BeforeEach
    void setUp() {
        this.sort = new InsertSort();
        this.cells= new Cell[][]{{new Cell(2), new Cell("jajko")},
                {new Cell(1), new Cell("jabłko")},
                {new Cell(5), new Cell("banan")},
                {new Cell(8), new Cell("gruszka")},
                {new Cell(-3), new Cell("śliwka")},
                {new Cell(0), new Cell("truskawka")}};
    }

    @Test
    void sortRisingNums(){
        Cell[][] cmp={{new Cell(-3), new Cell("śliwka")},
                {new Cell(0), new Cell("truskawka")},
                {new Cell(1), new Cell("jabłko")},
                {new Cell(2), new Cell("jajko")},
                {new Cell(5), new Cell("banan")},
                {new Cell(8), new Cell("gruszka")}};
        this.sort.sort(this.cells, 0, -1, SortAlgorithm.Order.RISING);
        assertArrayEquals(cmp, this.cells);
    }

    @Test
    void sortRisingNumsIncomplete(){
        boolean complete=this.sort.sort(this.cells, 0, 1, SortAlgorithm.Order.RISING);
        assertFalse(complete);
    }

    @Test
    void sortRisingText(){
        Cell[][] cmp={{new Cell(5), new Cell("banan")},
                {new Cell(8), new Cell("gruszka")},
                {new Cell(1), new Cell("jabłko")},
                {new Cell(2), new Cell("jajko")},
                {new Cell(0), new Cell("truskawka")},
                {new Cell(-3), new Cell("śliwka")}};
        this.sort.sort(this.cells, 1, -1, SortAlgorithm.Order.RISING);
        assertArrayEquals(cmp, this.cells);
    }

    @Test
    void sortRisingTextIncomplete(){
        boolean complete=this.sort.sort(this.cells, 1, 1, SortAlgorithm.Order.RISING);
        assertFalse(complete);
    }

    @Test
    void sortFallingNums(){
        Cell[][] cmp={{new Cell(8), new Cell("gruszka")},
                {new Cell(5), new Cell("banan")},
                {new Cell(2), new Cell("jajko")},
                {new Cell(1), new Cell("jabłko")},
                {new Cell(0), new Cell("truskawka")},
                {new Cell(-3), new Cell("śliwka")}};
        this.sort.sort(this.cells, 0, -1, SortAlgorithm.Order.FALLING);
        assertArrayEquals(cmp, this.cells);
    }

    @Test
    void sortFallingNumsIncomplete(){
        boolean complete=this.sort.sort(this.cells, 0, 1, SortAlgorithm.Order.FALLING);
        assertFalse(complete);
    }

    @Test
    void sortFallingText(){
        Cell[][] cmp={{new Cell(-3), new Cell("śliwka")},
                {new Cell(0), new Cell("truskawka")},
                {new Cell(2), new Cell("jajko")},
                {new Cell(1), new Cell("jabłko")},
                {new Cell(8), new Cell("gruszka")},
                {new Cell(5), new Cell("banan")}};
        this.sort.sort(this.cells, 1, -1, SortAlgorithm.Order.FALLING);
        assertArrayEquals(cmp, this.cells);
    }

    @Test
    void sortFallingTextIncomplete(){
        boolean complete=this.sort.sort(this.cells, 1, 1, SortAlgorithm.Order.FALLING);
        assertFalse(complete);
    }

    @Test
    void sortThrowsIndexOutOfRangeRising(){
        assertThrows(IndexOutOfBoundsException.class, ()->this.sort.sort(this.cells, 2, -1, SortAlgorithm.Order.RISING));
    }

    @Test
    void sortThrowsIndexOutOfRangeFalling(){
        assertThrows(IndexOutOfBoundsException.class, ()->this.sort.sort(this.cells, 2, -1, SortAlgorithm.Order.FALLING));
    }
}