package models;

public class User {
    private String nick;
    private Board board;
    private Board opponentBoard;
    private boolean isActive;

    public User(String nick) {
        this.nick = nick;
    }

    public String getNick() {
        return nick;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Board getOpponentBoard() {
        return opponentBoard;
    }

    public void setOpponentBoard(Board opponentBoard) {
        this.opponentBoard = opponentBoard;
    }
}
