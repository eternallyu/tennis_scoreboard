package enums;

import lombok.Getter;

@Getter
public enum PointsForDisplay {
    ZERO("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), ADVANTAGE("AD");

    private final String displayValue;

    PointsForDisplay(String displayValue) {
        this.displayValue = displayValue;
    }

    public static PointsForDisplay fromPointsToDisplayPoints(int points) {
        return switch (points) {
            case 0 -> ZERO;
            case 1 -> FIFTEEN;
            case 2 -> THIRTY;
            case 3 -> FORTY;
            case 4 -> ADVANTAGE;
            default -> throw new IllegalArgumentException("Invalid points.");
        };
    }

}
