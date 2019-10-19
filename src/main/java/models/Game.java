package models;

import enums.Sign;
import utils.Settings;

import java.util.List;
import java.util.Scanner;

public class Game {

    private List<User> users;

    private Game() {
    }

    public static void play() {
        //TODO: add exception handle during get data from users (tego jeszcze nie mam)
        Game game = new Game();
        game.init();

        while (game.isWinner(game.getOpponentBoard())) {
            System.out.printf("%s has movement. Enter number.", game.getActiveUser());
            String number = new Scanner(System.in).next();
            if (!game.getOpponentBoard().checkIfNumberIsShip(Integer.parseInt(number))) {
                game.changeActiveUser();
            }
        }
        game.showWinner(game);
    }

    private void init() {
        Settings settings = new Settings();
        List<Board> boards = settings.getBoards();
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

