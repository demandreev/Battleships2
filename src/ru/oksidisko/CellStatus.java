package ru.oksidisko;

public enum CellStatus {
    FOG_OF_WAR('~'), // ненужен по идее
    SHIP('O'),
    SHOT('X'),
    MISS('M'),
    EMPTY('.');

    private final char letter;

    CellStatus(char c) {
        letter = c;
    }

    // todo: надо перенести знание о букве поля соответствующей типу куда-то отсюда (обдумать)
    public char getLetter() {
        return letter;
    }
}
