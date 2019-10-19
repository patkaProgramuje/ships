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

    static void validateInputBoardSize(String size) {
        try {
            Integer.parseInt(size);
        } catch (NumberFormatException e) {
            System.out.println("Entered wrong data format.");
        }
    }

    static void validateInputForShipFields(String[] fields) {
        try {
            for(String s: fields) {
                Integer.parseInt(s);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entered wrong data format.");
        }

    }
}
