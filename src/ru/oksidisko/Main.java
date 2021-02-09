package ru.oksidisko;

public class Main {
    private static final Console console = new Console();

    public static void main(String[] args) {
	    Field firstField;
        Field secondField;
        boolean isFirstPlayerWin = false;
        boolean isSecondPlayerWin = false;

        System.out.println("First player, fill your field");
        firstField = createAndFulfillField();
        System.out.println("Second player, fill your field");
        secondField = createAndFulfillField();
        System.out.println("We ready to start!");

        while (!isFirstPlayerWin && !isSecondPlayerWin) {
            System.out.println("First player, make your move:");
	        isFirstPlayerWin = makeShotAndCheckGameEnds(secondField);
            printCurrentField(secondField);
	        if (!isFirstPlayerWin) {
                System.out.println("Second player, make your move:");
                isSecondPlayerWin = makeShotAndCheckGameEnds(firstField);
                printCurrentField(firstField);
            }
        }
	    showResult(isFirstPlayerWin, isSecondPlayerWin);
    }

    private static Field createAndFulfillField() {
        // создать новое поле
        Field field = new Field();
        // заполнить поле "туманом войны"
        coverFieldByFogOfWar(field);
        // пройти по всем вариантам кораблей
        for (BattleShipType ship : getBattleShipCollection()) {
            boolean isInputCorrect = false;
            do {
                // запросить координаты корабля (CoordinatePair)
                CoordinatePair coordinatePair = getShipCoordinatePair(ship);
                // проверить координаты для заданного корабля
                if (isShipCoordinateCorrect(ship, coordinatePair, field)) {
                    // меняем флаг
                    isInputCorrect = true;
                    // записать в поле
                    putShipToField(ship, coordinatePair, field);
                    // распечатать поле
                    printCurrentFieldUncovered(field);
                } else {
                    System.out.println("Wrong input, repeat!");
                }
            } while (!isInputCorrect);
        }
        // после окончания заполнения распечатываем поле
        printCurrentFieldUncovered(field);
        return field;
    }

    private static void printCurrentFieldUncovered(Field field) {
        console.printField(field, PrintMode.ALL);
    }

    private static void printCurrentField(Field field) {
        console.printField(field, PrintMode.ONLY_KNOWN);
    }

    private static void putShipToField(BattleShipType ship, CoordinatePair coordinatePair, Field field) {
        field.putShip(ship, coordinatePair);
    }

    private static boolean isShipCoordinateCorrect(BattleShipType ship, CoordinatePair coordinatePair, Field field) {
        return ship.getLength() == coordinatePair.getLength() && field.checkSpaceForShip(coordinatePair);
    }

    private static CoordinatePair getShipCoordinatePair(BattleShipType ship) {
        return console.getShipCoordinates(
                String.format("Please input coordinate for %s (size %d)", ship.getName(), ship.getLength()));
    }


    private static Iterable<BattleShipType> getBattleShipCollection() {
        return BattleShipType.getBattleShipCollection();
    }

    private static void coverFieldByFogOfWar(Field field) {
        field.fillByFogOfWar();
    }

    /**
     * Возвращает true если игра продолжается, false если игра закончена победой
     */
    private static boolean makeShotAndCheckGameEnds(Field field) {
        // запросить координату
        Coordinate coordinate = console.getShotCoordinate("Please input coordinate for your shot");
        // проверить статус выстрела (мимо, ранил, убил),
        boolean isShipSunk = field.makeShotAndGetStatus(coordinate);
        // если этот ход потопил корабль - написать об этом
        if (isShipSunk)
            System.out.println("You just sunk enemy ship! Congrats!");
        // проверить был ли потоплен последний корабль
        return field.isAllShipsDone();
    }

    private static void showResult(boolean isFirstPlayerWin, boolean isSecondPlayerWin) {
        // просто печатаем текущее положение вещей
        console.printGameResult(isFirstPlayerWin, isSecondPlayerWin);
    }
}
