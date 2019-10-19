package models;

public class Ship {
    private int size;
    private String[] numbers;

    public Ship(int size, String[] numbers) {
        this.size = size;
        this.numbers = numbers;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String[] getNumbers() {
        return numbers;
    }

    public void setNumbers(String[] numbers) {
        this.numbers = numbers;
    }
}
