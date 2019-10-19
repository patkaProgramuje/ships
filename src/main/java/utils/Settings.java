package utils;

import models.Board;
import models.Ship;
import models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Settings {

    public List<User> getUsers(List<Board> boards) {
        List<User> users = new ArrayList<>();
        System.out.println("Enter name first user: ");
        String firstUser = new Scanner(System.in).next();
        User userOne = new User(firstUser);
        userOne.setActive(true);
        userOne.setBoard(boards.get(0));
        userOne.setOpponentBoard(boards.get(1));
        users.add(userOne);

        System.out.println("Enter name second user: ");
        String secondUSer = new Scanner(System.in).next();
        User userTwo = new User(secondUSer);
        userTwo.setActive(false);
        userTwo.setBoard(boards.get(1));
        userTwo.setOpponentBoard(boards.get(0));
        users.add(userTwo);
        return users;
    }

    public List<Board> getBoards() {
        System.out.println("Enter boards size: ");
        Scanner scanner = new Scanner(System.in);
        String size = scanner.next();
        while (!ValidateInput.validateInputBoardSize(size)) {
            size = scanner.next();
        }
        Board board = Board.createBoard(Integer.parseInt(size));
        List<Board> boards = Arrays.asList(board, board);
        System.out.println("Two board was created.");
        return boards;
    }

    public void fillBoards(List<User> users, List<Board> boards) {
        int[] shipsSize = {5, 4, 3, 3, 2, 2, 1, 1};

        for (User user : users) {
            System.out.printf("User %s settings: ", user.getNick());
            System.out.println(boards);
            for (int i : shipsSize) {
                System.out.printf("Enter numbers for %s - most ship, divided commas.", i);
                String scanner = new Scanner(System.in).nextLine();
                String[] fields = scanner.split(",");
                ValidateInput.validateInputForShipFields(fields);
                Ship ship = new Ship(i, fields);
                user.getBoard().fillBoardWithShip(ship);
            }
        }
    }
}
