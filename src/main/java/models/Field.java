package models;

import enums.Sign;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private Sign sign;
    private boolean isChecked;
    private int number;

    public Field() {
        this.sign = Sign.EMPTY;
        this.isChecked = false;
    }

    public Sign getSign() {
        return sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getNeighbours(int number, int boardSize) {
        List<String> neighbours = new ArrayList<>();
        getLeftNeighbour(number, neighbours);
        getRightNeighbour(number, boardSize, neighbours);
        getDownNeighbour(number, boardSize, neighbours);
        getUpNeighbour(number, boardSize, neighbours);
        return neighbours;
    }

    private void getUpNeighbour(int number, int boardSize, List<String> neighbours) {
        if ((number - boardSize) > 0) {
            neighbours.add(String.valueOf(number - boardSize));
        }
    }

    private void getDownNeighbour(int number, int boardSize, List<String> neighbours) {
        if ((number + boardSize) < boardSize * 2) {
            neighbours.add(String.valueOf(number + boardSize));
        }
    }

    private void getRightNeighbour(int number, int boardSize, List<String> neighbours) {
        if ((number + 1) % boardSize != 0) {
            neighbours.add(String.valueOf(number + 1));
        }
    }

    private void getLeftNeighbour(int number, List<String> neighbours) {
        if ((number - 1) < 0) {
            neighbours.add(String.valueOf(number - 1));
        }
    }
}
