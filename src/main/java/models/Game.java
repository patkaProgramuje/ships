package models;

import enums.Sign;
import utils.Settings;
import utils.ValidateInput;

import java.util.List;
import java.util.Scanner;

public class Game {

    private List<User> users;

    public void startGame() {
        init();
        play();
        showWinner();
    }

    private void play() {
        while (!isWinner(getOpponentBoard())) {
            String number = getFieldNumberFromUser();
            if (!getOpponentBoard().checkIfNumberIsShip(Integer.parseInt(number))) {
                changeActiveUser();
            }
        }
    }

    private Board getOpponentBoard() {
        Board board = null;
        for (User user : users) {
            if (!user.isActive()) {
                board = user.getBoard();
            }
        }
        return board;
    }

    private void init() {
        Settings settings = new Settings();
        settings.init();
        this.users = settings.getUsers();
    }

    private void showWinner() {
        System.out.printf("The winner is %s.", getActiveUser().getNick());
    }

    private boolean isWinner(Board board) {
        List<Field> fields = board.getFields();
        for (Field field : fields) {
            if (field.getSign().equals(Sign.CROSS))
                return false;
        }
        return true;
    }

    private User getActiveUser() {
        User active = null;
        for (User user : users) {
            if (user.isActive()) active = user;
        }
        return active;
    }

    private void changeActiveUser() {
        for (User user : users) {
            if (user.isActive()) {
                user.setActive(false);
            } else {
                user.setActive(true);
            }
        }
    }

    private String getFieldNumberFromUser() {
        System.out.printf("%s has movement. Enter number.", getActiveUser().getNick());
        String number = new Scanner(System.in).next();
        ValidateInput.validateInputForChosenField(number, getOpponentBoard());
        return number;
    }
}

