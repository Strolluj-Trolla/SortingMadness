package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScramblerTest {
    Scrambler scrambler;
    Cell[][] cells;

    @BeforeEach
    void setUp() {
        this.scrambler = new Scrambler();
        this.cells= new Cell[][]{{new Cell(2), new Cell("jajko")},
                {new Cell(1), new Cell("jabłko")},
                {new Cell(5), new Cell("banan")},
                {new Cell(8), new Cell("gruszka")},
                {new Cell(-3), new Cell("śliwka")},
                {new Cell(0), new Cell("truskawka")}};
    }

    @Test
    void scrambleTestRising(){
        Cell[][] cmp={{new Cell(-3), new Cell("śliwka")},
                {new Cell(0), new Cell("truskawka")},
                {new Cell(1), new Cell("jabłko")},
                {new Cell(2), new Cell("jajko")},
                {new Cell(5), new Cell("banan")},
                {new Cell(8), new Cell("gruszka")}};
        this.scrambler.scramble(this.cells, 0, Scrambler.ScrambleType.RISING);
        assertArrayEquals(cmp, this.cells);
    }

    void scrambleTestFalling(){
        Cell[][] cmp={{new Cell(8), new Cell("gruszka")},
                {new Cell(5), new Cell("banan")},
                {new Cell(2), new Cell("jajko")},
                {new Cell(1), new Cell("jabłko")},
                {new Cell(0), new Cell("truskawka")},
                {new Cell(-3), new Cell("śliwka")}};
        this.scrambler.scramble(this.cells, 0, Scrambler.ScrambleType.FALLING);
        assertArrayEquals(cmp, this.cells);
    }

    void scrambleTestNone(){
        Cell[][] cmp={{new Cell(2), new Cell("jajko")},
                {new Cell(1), new Cell("jabłko")},
                {new Cell(5), new Cell("banan")},
                {new Cell(8), new Cell("gruszka")},
                {new Cell(-3), new Cell("śliwka")},
                {new Cell(0), new Cell("truskawka")}};
        this.scrambler.scramble(this.cells, 0, Scrambler.ScrambleType.NONE);
        assertArrayEquals(cmp, this.cells);
    }

    void scrambleTestRandom(){
        int length=this.cells.length;
        assertDoesNotThrow(()-> this.scrambler.scramble(this.cells, 0, Scrambler.ScrambleType.RANDOM));
        assertEquals(length, this.cells.length);

    }
}