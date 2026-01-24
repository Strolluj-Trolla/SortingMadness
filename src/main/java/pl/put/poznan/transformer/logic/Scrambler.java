package pl.put.poznan.transformer.logic;

import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A simple class for scrambling input data intended for testing performance under different circumstances.
 */
public class Scrambler{
    /**
     * Enumeration type used for denoting the fashion in which data should be scrambled.
     */
    public enum ScrambleType {RANDOM, RISING, FALLING, NONE}
    /**
     * Randomizer used for RANDOM scrambling
     */
    private final Random r;
    /**
     * Logger instance used for logging execution details and diagnostics
     * of the sorting measurement process.
     */
    private static final Logger logger = LoggerFactory.getLogger(Scrambler.class);


    /**
     * A simple constructor which initializes the randomizer
     */
    public Scrambler(){
        r = new Random(System.currentTimeMillis());
    }

    /**
     * Lightweight scrambling method. Used to prepare data before measuring sorting performance.
     * @param data a 2-D array of type {@link Cell} to scramble
     * @param column the index of the column used for scrambling in case of RISING or FALLING scrambleType.
     * @param scrambleType an enum value of type {@link ScrambleType} denoting how to scramble data.
     */
    public void scramble(Cell[][] data, int column, ScrambleType scrambleType) {
        if((column<0)||(column>=data[0].length)) {
            logger.error("Invalid column index for given data, can't scramble");
            return;
        }
        if (scrambleType == ScrambleType.NONE) return;
        if (scrambleType == ScrambleType.RANDOM) {
            int index;
            Cell[] temp;
            for (int i = data.length - 1; i > 0; i--) {
                index = r.nextInt(i + 1);
                temp = data[index];
                data[index] = data[i];
                data[i] = temp;
            }
        } else {
            SortAlgorithm.Order temp;
            if (scrambleType == ScrambleType.FALLING)temp=SortAlgorithm.Order.FALLING;
            else temp=SortAlgorithm.Order.RISING;
            Sorter sorter = new Sorter();
            sorter.sort(data, column, -1, temp);
        }
    }
}
