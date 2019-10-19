package models;

import enums.Sign;
import utils.Settings;
import utils.ValidateInput;

import java.util.List;
import java.util.Scanner;

public class Game {

    private List<User> users;
    private List<Board> boards;

    public void startGame() {
        init();
        play();
        showWinner();
    }

    private void play() {
        while (!isWinner(getActiveUser().getOpponentBoard())) {
            String number = getFieldNumberFromUser();
            if (!getActiveUser().getOpponentBoard().checkIfNumberIsShip(Integer.parseInt(number))) {
                changeActiveUser();
            }
        }
    }

    private void init() {
        Settings settings = new Settings();
        this.boards = settings.getBoards();
        this.users = settings.getUsers(boards);
        settings.fillBoards(users, boards);
    }

    private void showWinner() {
        System.out.printf("The winner is %s.", getActiveUser().getNick());
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

    private boolean isWinner(Board board) {
        List<Field> fields = board.getFields();
        for (Field field : fields) {
            if (field.getSign().equals(Sign.CROSS))
                return false;
        }
        return true;
    }

    private String getFieldNumberFromUser() {
        System.out.printf("%s has movement. Enter number.", getActiveUser());
        String number = new Scanner(System.in).next();
        ValidateInput.validateInputForChosenField(number, boards);
        return number;
    }
}

