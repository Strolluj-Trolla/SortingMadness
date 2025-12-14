package pl.put.poznan.transformer.logic;

public class Cell {
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
}
