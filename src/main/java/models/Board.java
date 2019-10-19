package models;

import enums.Sign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board {
    private List<Field> fields;
    private int size;

    private Board(int size) {
        this.size = size;
        this.fields = new ArrayList<>();
    }

    public static Board createBoard(int size) {
        Board board = new Board(size);
        board.initializeBoard();
        return board;
    }

    List<Field> getFields() {
        return fields;
    }

    public void fillBoardWithShip(Ship ship) {
        String[] numbers = ship.getNumbers();
        if (checkAvailabilityFields(numbers) && checkIfNumbersAreNeighbours(numbers)) {
            setSignToField(numbers);
        }
    }

    boolean checkIfNumberIsShip(int number) {
        if (fields.get(number).isChecked()) {
            fields.get(number).setSign(Sign.FOUND);
            return true;
        } else {
            return false;
        }
    }

    private void setSignToField(String[] numbers) {
        for (String str : numbers) {
            int number = Integer.valueOf(str);
            fields.get(number).setSign(Sign.CROSS);
            fields.get(number).setChecked();
        }
    }

    private boolean checkAvailabilityFields(String[] numbers) {
        for (String str : numbers) {
            int number = Integer.valueOf(str);
            if (fields.get(number).isChecked()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfNumbersAreNeighbours(String[] numbers) {
        List<String> strings = Arrays.asList(numbers);
        for (String str : numbers) {
            int number = Integer.valueOf(str);
            List<String> neighbours = fields.get(number).getNeighbours(number, size);
            if (Collections.indexOfSubList(neighbours, strings) == -1) {
                return false;
            }
        }
        return true;
    }

    private void initializeBoard() {
        for (int i = 0; i < size * 2; i++) {
            Field field = new Field();
            field.setNumber(i);
            fields.add(field);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fields.size() * 2; i++) {
            if (i == size) {
                stringBuilder.append("\n");
            }
            if (fields.get(i).getSign().equals(Sign.EMPTY)) {
                if (i < 10) {
                    stringBuilder.append("  ").append(fields.get(i).getNumber()).append(" ");
                } else {
                    stringBuilder.append(" ").append(fields.get(i).getNumber()).append(" ");
                }
            } else {
                stringBuilder.append(" ").append(fields.get(i).getSign()).append(" ");
            }
        }
        return stringBuilder.toString();
    }
}

