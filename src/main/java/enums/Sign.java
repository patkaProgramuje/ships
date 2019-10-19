package enums;

public enum Sign {
    CROSS("X"), EMPTY(""),FOUND("#");

    String sign;

    Sign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return sign;
    }
}
