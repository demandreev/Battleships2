package ru.oksidisko;

import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);

    public CoordinatePair getShipCoordinates(String text) {
        System.out.println(text);
        // прочесть строку с двумя координатами
        String input = scanner.nextLine();
        return CoordinatePair.parse(input);
    }

    public Coordinate getShotCoordinate(String text) {
        System.out.println(text);
        // прочесть строку с координатой одной клетки
        String input = scanner.next();
        return Coordinate.parse(input);
    }

    public void printGameResult(boolean isFirstPlayerWin, boolean isSecondPlayerWin) {
        String winner = isFirstPlayerWin ? "First" : "Second";
        System.out.println(winner + " player wins!");
    }

    public void printField(Field field, PrintMode isOnlyKnown) {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char stringId = 'A';

        for (int y = 0; y < Field.SIZE; y++) {
            System.out.print("" + stringId++ + ' ');
            printString(y, field, isOnlyKnown);
            System.out.println();
        }
    }

    private void printString(int strNumber, Field field, PrintMode printMode) {
        for(int x = 0; x < Field.SIZE; x++) {
            CellStatus status = field.getCellStatus(x, strNumber);
            if (printMode == PrintMode.ONLY_KNOWN) {
                if (status != CellStatus.SHOT && status != CellStatus.MISS) {
                    status = CellStatus.FOG_OF_WAR;
                }
            }
            System.out.print("" + status.getLetter() + " ");
        }
    }
}
