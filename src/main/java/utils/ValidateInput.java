package utils;

import models.Board;

public class ValidateInput {

    private static final String ENTERED_WRONG_DATA_FORMAT = "Entered wrong data format.";
    private static final String ENTERED_TOO_BIG_NUMBER = "Entered too big number.";
    private static final String ENTERED_SIZE_IS_TOO_SMALL_ENTERED_VALUE_MUST_BE_GREATER_OR_EQUAL_TO_10 = "Entered size is too small. Entered value must be greater or equal to 10.";

    public static void validateInputForChosenField(String number, Board board) {
        try {
            int fieldNumber = Integer.parseInt(number);
            int size = board.getSize();
            if (fieldNumber > size * size) {
                System.out.println(ENTERED_TOO_BIG_NUMBER);
            }
        } catch (NumberFormatException e) {
            System.out.println(ENTERED_WRONG_DATA_FORMAT);
        }
    }

    static boolean validateInputBoardSize(String size) {
        try {
            int boardSize = Integer.parseInt(size);
            if (boardSize < 10) {
                System.out.println(ENTERED_SIZE_IS_TOO_SMALL_ENTERED_VALUE_MUST_BE_GREATER_OR_EQUAL_TO_10);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            System.out.println(ENTERED_WRONG_DATA_FORMAT);
            return false;
        }
    }

    static void validateInputForShipFields(String[] fields) {
        try {
            for (String s : fields) {
                Integer.parseInt(s);
            }
        } catch (NumberFormatException e) {
            System.out.println(ENTERED_WRONG_DATA_FORMAT);
        }

    }
}
