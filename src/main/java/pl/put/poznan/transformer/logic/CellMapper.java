package pl.put.poznan.transformer.logic;

import java.util.List;

/**
 * A unit for converting received data from the format received from the client to the one used internally.
 */
public class CellMapper {

    /**
     * Conversion method for input data.
     *
     * @param input a {@link List} of {@link List} of {@link Object} supplied with the request.
     * @return a 2-D array of type {@link Cell} to be used in further processing.
     */
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
