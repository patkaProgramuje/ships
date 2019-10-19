package models;

import enums.Sign;

import java.util.ArrayList;
import java.util.Arrays;
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

    public boolean tryPlaceShip(Ship ship) {
        String[] numbers = ship.getNumbers();
        if (checkAvailabilityFields(numbers) && checkIfNumbersAreNeighbours(numbers)) {
            setSignToField(numbers);
            return true;
        } else {
            return false;
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
                System.out.println("This place is already busy.");
                return false;
            }
        }
        return true;
    }

    private boolean checkIfNumbersAreNeighbours(String[] numbers) {
        for (String str : numbers) {
            int number = Integer.valueOf(str);
            List<String> neighbours = fields.get(number).getNeighbours(number, size);
            if (!hasAtLeastOneNeighbour(numbers, neighbours) && numbers.length > 1) {
                System.out.println("Entered wrong fields.");
                return false;
            }
        }
        return true;
    }

    private boolean hasAtLeastOneNeighbour(String[] number, List<String> neighbours) {
        List<String> numbers = Arrays.asList(number);
        return neighbours.stream()
                .anyMatch(numbers::contains);
    }

    private void initializeBoard() {
        for (int i = 0; i < size * size; i++) {
            Field field = new Field();
            field.setNumber(i);
            fields.add(field);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < fields.size(); i++) {
            if (i % size == 0) {
                stringBuilder.append("\n");
            }
            if (fields.get(i).getSign().equals(Sign.EMPTY)) {
                if (i < fields.size()) {
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

    public int getSize() {
        return size;
    }
}

