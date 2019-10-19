package utils;

import models.Board;
import models.Ship;
import models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Settings {

    public List<User> getUsers(List<Board> boards){
        List<User> users = new ArrayList<>();
        System.out.println("Enter name first user: ");
        String firstUser = new Scanner(System.in).next();
        User userOne = new User(firstUser);
        userOne.setActive(true);
        userOne.setBoard(boards.get(0));
        users.add(userOne);

        System.out.println("Enter name second user: ");
        String secondUSer = new Scanner(System.in).next();
        User userTwo = new User(secondUSer);
        userTwo.setActive(false);
        userTwo.setBoard(boards.get(1));
        users.add(userTwo);
        return users;
    }

    public List<Board> getBoards(){
        System.out.println("Enter boards size: ");
        String boardSize = new Scanner(System.in).next();
        int size = Integer.parseInt(boardSize);
        //try{}catch{}
        Board board = Board.createBoard(size);
        List<Board> boards = Arrays.asList(board, board);
        System.out.println("Two board was created.");
        return boards;
    }

    public void fillBoards(List<User> users) {
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
}
