package pl.put.poznan.transformer.logic;

public interface SortAlgorithm {
    enum Order{RISING, FALLING}
    boolean sort(Cell[][] tab, int column, int maxIter, Order order);
}
