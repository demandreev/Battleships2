package ru.oksidisko;

// todo: неудобно пользоваться этим классом (связь наружу с internal координатами)
public class CoordinatePair {

    private final Coordinate start, end;

    public CoordinatePair(Coordinate start, Coordinate end) {
        this.start = start;
        this.end = end;
    }

    public static CoordinatePair parse(String input) {
        String[] points = input.split(" ");
        Coordinate start = Coordinate.parse(points[0]);
        Coordinate end = Coordinate.parse(points[1]);
        return new CoordinatePair(start, end);
    }

    public Coordinate getStart() {
        return start;
    }

    public Coordinate getEnd() {
        return end;
    }

    public int getLength() {
        return (int)Math.sqrt((start.x - end.x) * (start.x - end.x) + (start.y - end.y) * (start.y - end.y)) + 1;
    }

    /**
     * Метод конвертирует координаты вида (A1, A4) в координаты вида (0:0, 0:3)
     */
    public InternalXYCoordinatePair convertCoordinatePairToInternal() {
        return new InternalXYCoordinatePair(start.extractInternalCoordinate(), end.extractInternalCoordinate());
    }
}
