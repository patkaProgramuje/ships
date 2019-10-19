package models;

import enums.Sign;
import utils.Settings;
import utils.ValidateInput;

import java.util.List;
import java.util.Scanner;

public class Game {

    private List<User> users;
    private List<Board> boards;

    private Game() {
    }

    public static void startGame() {
        Game game = new Game();
        game.init();
        game.play(game);
        game.showWinner(game);
    }

    private void play(Game game) {
        while (game.isWinner(game.getOpponentBoard())) {
            String number = game.getFieldNumberFromUser(game);
            if (!game.getOpponentBoard().checkIfNumberIsShip(Integer.parseInt(number))) {
                game.changeActiveUser();
            }
        }
    }

    private String getFieldNumberFromUser(Game game) {
        System.out.printf("%s has movement. Enter number.", game.getActiveUser());
        String number = new Scanner(System.in).next();
        ValidateInput.validateInputForChosenField(number, boards);
        return number;
    }

    private void init() {
        Settings settings = new Settings();
        this.boards = settings.getBoards();
        this.users = settings.getUsers(boards);
        settings.fillBoards(users);
    }

    private User getActiveUser() {
        User active = null;
        for (User user : users) {
            if (user.isActive()) active = user;
        }
        return active;
    }

    private void showWinner(Game game) {
        System.out.printf("The winner is %s.", game.getActiveUser().getNick());
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

    private Board getOpponentBoard() {
        Board board = null;
        for (User user : users) {
            if (!user.isActive()) board = user.getBoard();
        }
        return board;
    }

    private boolean isWinner(Board board) {
        List<Field> fields = board.getFields();
        for (Field field : fields) {
            if (field.getSign().equals(Sign.CROSS))
                return false;
        }
        return true;
    }
}

