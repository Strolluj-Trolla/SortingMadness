package pl.put.poznan.transformer.logic;

public class Cell{
    public final String str;
    public final Double num;
    public Cell(String str){
        this.str=str;
        this.num=null;
    }
    public Cell(double num){
        this.num=num;
        this.str=null;
    }
}
