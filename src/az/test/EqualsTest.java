package az.test;

public class EqualsTest {

    public static void main(String[] args) {
        say("Boolean.TRUE.equals(true)", Boolean.TRUE.equals(true));
        say("Boolean.TRUE.equals(null)", Boolean.TRUE.equals(null));
        say("Boolean.TRUE.equals(Boolean.TRUE)", Boolean.TRUE.equals(Boolean.TRUE));
        say("Boolean.TRUE.equals(FALSE)", Boolean.TRUE.equals(false));
        say("Boolean.TRUE.equals(Long.valueOf(100))", Boolean.TRUE.equals(Long.valueOf(100)));
    }

    private static void say(String text, boolean value) {
        System.out.println(text + " " + value);
    }
}
