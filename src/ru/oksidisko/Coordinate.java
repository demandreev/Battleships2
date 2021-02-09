package ru.oksidisko;

// todo: неудобно пользоваться этим классом (связь наружу с internal координатами)
public class Coordinate {
    byte x;
    char y;

    public Coordinate(byte x, char y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate parse(String input) {
        char y = input.toCharArray()[0];
        byte x = Byte.parseByte(input.substring(1));

        return new Coordinate(x, y);
    }

    public InternalXYCoordinate extractInternalCoordinate() {
        byte startX = (byte)(x -1);
        byte startY = (byte)(y - 'A');
        return new InternalXYCoordinate(startX, startY);
    }

    public int getInternalX() {
        return extractInternalCoordinate().x;
    }

    public int getInternalY() {
        return extractInternalCoordinate().y;
    }
}
