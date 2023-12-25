package tranlong5252.foodsupplychain.constants;

public enum StatusLevel {
    LOW(1),
    MEDIUM(2),
    HIGH(3);

    private final int value;

    StatusLevel(int value) {
        this.value = value;
    }

    public static StatusLevel getByValue(int value) {
        for (StatusLevel statusLevel : StatusLevel.values()) {
            if (statusLevel.getValue() == value) {
                return statusLevel;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return String.valueOf(value);
    }
}
