package ru.oksidisko;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Aircraft Carrier is 5 cells, Battleship is 4 cells, Submarine is 3 cells, Cruiser is also 3 cells, and Destroyer is 2
 */
public enum BattleShipType {
    AIRCRAFT_CARRIER(5, "Aircraft Carrier"),
    BATTLE_SHIP(4, "Battleship"),
    SUBMARINE(3, "Submarine"),
    CRUISER(3, "Cruiser"),
    DESTROYER(2, "Destroyer");

    private final int length;
    private final String name;

    private static final List<BattleShipType> SHIPS = new ArrayList<>();
    static {
//        SHIPS.add(AIRCRAFT_CARRIER);
//        SHIPS.add(BATTLE_SHIP);
//        SHIPS.add(SUBMARINE);
//        SHIPS.add(CRUISER);
        SHIPS.add(DESTROYER);
    }

    public static List<BattleShipType> getBattleShipCollection() {
        return Collections.unmodifiableList(SHIPS);
    }

    BattleShipType(int length, String name) {
        this.length = length;
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public String getName() {
        return name;
    }
}
