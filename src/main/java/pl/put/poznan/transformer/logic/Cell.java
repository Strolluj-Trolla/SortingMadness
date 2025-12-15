package pl.put.poznan.transformer.logic;

import org.springframework.lang.NonNull;

/**
 * Helper class to store a single unit of data. Used to create arrays of data to process.
 * Holds either a {@link Double} or a {@link String}, never both.
 * Also implements the {@link Comparable} interface to allow comparing to a  different {@link Cell}.
 */
public class Cell implements Comparable<Cell> {
    /**
     * Numerical value of the {@link Cell}. Mutually exclusive with {@link #str}.
     */
    public final Double num;
    /**
     * Text value of the {@link Cell}. Mutually exclusive with {@link #num}.
     */
    public final String str;

    /**
     * Constructor for making a {@link Cell} holding numerical data.
     *
     * @param num a {@link Double} to be stored in the {@link Cell}
     */
    public Cell(Double num) {
        this.num = num;
        this.str = null;
    }

    /**
     * Alternative constructor for storing integer data
     *
     * @param num an {@link Integer} to be converted and stored in the {@link Cell}
     */
    public Cell(Integer num) {
        this.num = num.doubleValue();
        this.str = null;
    }

    /**
     * A constructor for making a {@link Cell} storing text data.
     *
     * @param str a {@link String} to be stored in the Cell
     */
    public Cell(String str) {
        this.str = str;
        this.num = null;
    }

    public Double getNum() {
        return num;
    }
    public String getStr() {
        return str;
    }

    /**
     * An override method to compare this {@link Cell} to another.
     *
     * @param c the object to be compared.
     * @return a positive value if this {@link Cell} is greater than supplied, negative one if lesser and {@code 0} otherwise.
     */
    @Override
    public int compareTo(@NonNull Cell c){
        if(this.str==null){
            if(c.str==null){
                return this.num.compareTo(c.num);
            }
            return -10;
        }
        if(c.str==null) {
            return 10;
        }
        return this.str.compareTo(c.str);
    }
}
