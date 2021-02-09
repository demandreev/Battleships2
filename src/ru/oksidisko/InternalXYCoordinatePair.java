package ru.oksidisko;

/**
 * пара координат - начало и конец корабля
 */
class InternalXYCoordinatePair {
    InternalXYCoordinate start, end;

    public InternalXYCoordinatePair(InternalXYCoordinate start, InternalXYCoordinate end) {
        this.start = start;
        this.end = end;
    }
}
