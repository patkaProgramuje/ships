package models;

import enums.Sign;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private List<User> users;

    private Game(List<User> users) {
        this.users = users;
    }

    public static void play() {
        //TODO: add exception handle during get data from users (tego jeszcze nie mam)
        List<User> users = new ArrayList<>();
        System.out.println("Enter name first user: ");
        String firstUser = new Scanner(System.in).next();
        User userOne = new User(firstUser);
        userOne.setActive(true);
        users.add(userOne);

        System.out.println("Enter name second user: ");
        String secondUSer = new Scanner(System.in).next();
        User userTwo = new User(secondUSer);
        userTwo.setActive(false);
        users.add(userTwo);

        System.out.println("Enter board size: ");
        String boardSize = new Scanner(System.in).next();
        int board = Integer.parseInt(boardSize);
        //try{}catch{}
        Board boardForFirstUser = Board.createBoard(board);
        userOne.setBoard(boardForFirstUser);
        Board boardForSecondUser = Board.createBoard(board);
        userTwo.setBoard(boardForSecondUser);
        System.out.println("Two board was created.");

        Game game = new Game(users);
        game.setBoard();

        while (game.isWinner(game.getOpponentBoard())) {
            System.out.printf("%s has movement. Enter number.", game.getActiveUser());
            String number = new Scanner(System.in).next();
            if (!game.getOpponentBoard().checkIfNumberIsShip(Integer.parseInt(number))) {
                game.changeActiveUser();
            }
        }

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

    private User getActiveUser() {
        User active = null;
        for (User user : users) {
            if (user.isActive()) active = user;
        }
        return active;
    }

    private Board getOpponentBoard() {
        Board board = null;
        for (User user : users) {
            if (!user.isActive()) board = user.getBoard();
        }
        return board;
    }

    private void setBoard() {
        int[] shipsSize = {5, 4, 3, 3, 2, 2, 1, 1};

        for (User user : users) {
            System.out.printf("User %s settings:", user.getNick());
            for (int i : shipsSize) {
                System.out.printf("Enter numbers for %d - most ship, divided commas.", i);
                String scanner = new Scanner(System.in).next();
                String[] fields = scanner.split(",");
                Ship ship = new Ship(i, fields);
                user.getBoard().fillBoardWithShip(ship);
            }
        }
    }

    private boolean isWinner(Board board) {
        List<Field> fields = board.getFields();
        for (int i = 0; i < fields.size(); i++) {
            if (fields.get(i).getSign().equals(Sign.CROSS))
                return false;
        }
        return true;
    }
}

