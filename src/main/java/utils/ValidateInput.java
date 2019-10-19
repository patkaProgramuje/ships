package utils;

import models.Board;

import java.util.List;

public class ValidateInput {

    public static void validateInputForChosenField(String number, List<Board> boards) {
        try {
            int fieldNumber = Integer.parseInt(number);
            if (fieldNumber > boards.size() * 2) {
                System.out.println("Entered wrong number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entered wrong data format.");
        }
    }

    static boolean validateInputBoardSize(String size) {
        try {
            int boardSize = Integer.parseInt(size);
            if (boardSize < 10) {
                System.out.println("Entered size is too small. Enter value minimum 10.");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Entered wrong data format.");
            return false;
        }
    }

    static void validateInputForShipFields(String[] fields) {
        try {
            for (String s : fields) {
                Integer.parseInt(s);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entered wrong data format.");
        }

    }
}
