package pl.put.poznan.transformer.logic;

import org.springframework.lang.NonNull;

public class Cell implements Comparable<Cell> {
    public Double num;
    public String str;

    public Cell(Double num) {
        this.num = num;
        this.str = null;
    }

    public Cell(Integer num) {
        this.num = num.doubleValue();
        this.str = null;
    }

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
