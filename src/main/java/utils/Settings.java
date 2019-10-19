package utils;

import models.Board;
import models.Ship;
import models.User;

import java.util.*;

public class Settings {

    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public void init(){
        List<Board> boards = initBoards();
        initUsers(boards);
        placeShips();
    }

    private void placeShips() {
        int[] shipsSize = {5, 4, 3, 3, 2, 2, 1, 1};

        for (User user : users) {
            System.out.printf("User %s settings: ", user.getNick());
            System.out.println();
            Board board = user.getBoard();
            System.out.println(board);
            for (int i : shipsSize) {
                System.out.printf("Enter numbers for %s - most ship, divided commas.", i);
                Scanner scanner = new Scanner(System.in);
                String numbers = scanner.nextLine();
                String[] fields = numbers.split(",");
                ValidateInput.validateInputForShipFields(fields);
                Ship ship = new Ship(fields);
                while (!board.tryPlaceShip(ship)) {
                    numbers = scanner.nextLine();
                    fields = numbers.split(",");
                    ValidateInput.validateInputForShipFields(fields);
                    ship = new Ship(fields);
                }
            }
            System.out.println(board);
        }
    }

    private User getUser(List<Board> boards, String s, boolean b, int i) {
        System.out.println(s);
        String firstUser = new Scanner(System.in).next();
        User userOne = new User(firstUser);
        userOne.setActive(b);
        userOne.setBoard(boards.get(i));
        return userOne;
    }

    private List<Board> initBoards() {
        System.out.println("Enter boards size: ");
        Scanner scanner = new Scanner(System.in);
        String size = scanner.next();
        while (!ValidateInput.validateInputBoardSize(size)) {
            size = scanner.next();
        }
        Board boardOne = Board.createBoard(Integer.parseInt(size));
        Board boardTwo = Board.createBoard(Integer.parseInt(size));
        List<Board> boards = Arrays.asList(boardOne, boardTwo);
        System.out.println("Two board was created.");
        return boards;
    }

    private void initUsers(List<Board> boards){
        User userOne = getUser(boards, "Enter name first user: ", true, 0);
        users.add(userOne);

        User userTwo = getUser(boards, "Enter name second user: ", false, 1);
        users.add(userTwo);
    }
}
