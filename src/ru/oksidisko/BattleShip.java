package ru.oksidisko;

public class BattleShip {
    private final BattleShipType type;
    private final CoordinatePair coordinates;
    private int shotsCount = 0;

    public BattleShip(BattleShipType type, CoordinatePair coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public CoordinatePair getCoordinates() {
        return coordinates;
    }

    public boolean isVerticalShip() {
        return coordinates.getStart().x == coordinates.getEnd().x;
    }

    public void markShot(InternalXYCoordinate extractInternalCoordinate) { // todo: координата не используется, упрощённая реализация.
        shotsCount++;
    }

    public boolean isSunk() {
        return shotsCount == type.getLength();
    }

    public boolean containsCoordinate(InternalXYCoordinate coordinateToCheck) {
        int x1 = coordinates.getStart().getInternalX();
        int x2 = coordinates.getEnd().getInternalX();
        int y1 = coordinates.getStart().getInternalY();
        int y2 = coordinates.getEnd().getInternalY();

        int xStart = Math.min(x1, x2);
        int xEnd = Math.max(x1, x2);
        int yStart = Math.min(y1, y2);
        int yEnd = Math.max(y1, y2);

        boolean result = false;
        for(int x = xStart; x <= xEnd; x++) {
            for(int y = yStart; y <= yEnd; y++) {
                if (x == coordinateToCheck.x && y == coordinateToCheck.y) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}
