package ru.oksidisko;

import java.util.ArrayList;
import java.util.List;

import static ru.oksidisko.CellStatus.*;

/**
 * Класс представляющий собой поле 10 на 10 у которого координаты (по оси Х - цифры от 1 до 10, по Y - буквы от A до J)
 */
public class Field {
    public static final int SIZE = 10;
    private final CellStatus[][] field = new CellStatus[SIZE][SIZE];
    private final List<BattleShip> ships = new ArrayList<>();

    public void fillByFogOfWar() {
        for(int x = 0; x < SIZE; x++) {
            for(int y = 0; y < SIZE; y++) {
                field[x][y] = EMPTY;
            }
        }
    }

    public void putShip(BattleShipType type, CoordinatePair coordinates) {
        // создаём и сохраняем корабль
        BattleShip ship = new BattleShip(type, coordinates);
        ships.add(ship);
        // добавляем на поле
        addShipInternal(ship);
    }

    private void addShipInternal(BattleShip ship) {
        InternalXYCoordinatePair coordinates = ship.getCoordinates().convertCoordinatePairToInternal();
        if (ship.isVerticalShip()) { // корабль расположен вертикален
            for (int y = coordinates.start.y; y <= coordinates.end.y; y++) {
                field[coordinates.start.x][y] = SHIP;
            }
        } else { // корабль расположен горизонтально
            for (int x = coordinates.start.x; x <= coordinates.end.x; x++) {
                field[x][coordinates.start.y] = SHIP;
            }
        }
    }



    /**
     * метод вернёт true если можно разместить, false - иначе
     */
    public boolean checkSpaceForShip(CoordinatePair coordinates) {
        InternalXYCoordinatePair internalXYCoordinatePair = coordinates.convertCoordinatePairToInternal();
        InternalXYCoordinate start = internalXYCoordinatePair.start;
        InternalXYCoordinate end = internalXYCoordinatePair.end;

        int x1 = start.x > 0 ? start.x - 1 : start.x;
        int x2 = end.x < 9 ? end.x + 1 : end.x;

        int y1 = start.y > 0 ? start.y - 1 : start.y;
        int y2 = end.y < 9 ? end.y + 1 : end.y;

        return getCountOfCellsOfType(x1, x2, y1, y2, SHIP) == 0;
    }

    public boolean isAllShipsDone() {
        return getCountOfCellsOfType(0, Field.SIZE, 0, Field.SIZE, SHIP) == 0;
    }

    private int getCountOfCellsOfType(int x1, int x2, int y1, int y2, CellStatus cellStatus) {
        int count = 0;
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                if (field[x][y] == cellStatus) {
                    count++;
                }
            }
        }
        return count;
    }

    public boolean makeShotAndGetStatus(Coordinate coordinate) {
        CellStatus status = field[coordinate.getInternalX()][coordinate.getInternalY()];
        CellStatus newStatus;
        BattleShip ship = null;

        if (status == EMPTY) {
            newStatus = MISS;
        } else if (status == SHIP) {
            newStatus = SHOT;
            ship = getShipByCoordinate(coordinate.extractInternalCoordinate());
            if (ship == null)
                throw new Error("No ship in this coordinates");

            ship.markShot(coordinate.extractInternalCoordinate());
        } else {
            newStatus = status;
        }
        field[coordinate.getInternalX()][coordinate.getInternalY()] = newStatus;

        return ship != null && ship.isSunk();
    }

    // если координата не принадлежит какому-либо кораблю будет возвращён null (не обрабатывается выше)
    private BattleShip getShipByCoordinate(InternalXYCoordinate coordinate) {
        for(BattleShip ship : ships) {
            if (ship.containsCoordinate(coordinate)) {
                return ship;
            }
        }

        return null;
    }

    public CellStatus getCellStatus(int x, int y) {
        return field[x][y];
    }
}
