package pl.put.poznan.transformer.rest.mapper;

import pl.put.poznan.transformer.logic.Cell;

import java.util.List;

public class CellMapper {

    public static Cell[][] toCells(List<List<Object>> input) {

        if (input == null || input.isEmpty()) {
            return new Cell[0][0];
        }

        Cell[][] cells = new Cell[input.size()][];

        for (int i = 0; i < input.size(); i++) {
            List<Object> row = input.get(i);
            cells[i] = new Cell[row.size()];

            for (int j = 0; j < row.size(); j++) {
                Object val = row.get(j);

                if (val instanceof Number) {
                    cells[i][j] = new Cell(((Number) val).doubleValue());
                } else {
                    cells[i][j] = new Cell(val.toString());
                }
            }
        }
        return cells;
    }
}
