package models;

public class Ship {
    private int size;
    private String[] numbers;

    public Ship(int size, String[] numbers) {
        this.size = size;
        this.numbers = numbers;
    }

    String[] getNumbers() {
        return numbers;
    }
}
