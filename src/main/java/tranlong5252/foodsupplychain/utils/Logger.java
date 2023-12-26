package tranlong5252.foodsupplychain.utils;

public class Logger {

    public static void info(String message) {
        System.out.println(message);
    }

    public static void severe(Exception exception, String note) {
        System.out.println(exception + ": " + note);
    }


}
