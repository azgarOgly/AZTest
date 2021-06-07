package az.test.string;

public class ReadLineTest {
    public static void main(String[] args) {
        new ReadLineTest().run();
    }

    private void run() {
        ByteSource bs = new ByteSource();
        String s;
        while ((s = readln(bs)) != null) {
            System.out.println("\naquired: '" + s + "'");
        }
    }

    private String readln(ByteSource bs) {
        char[] buffer = new char[1024];
        int index = 0;

        while(bs.available()) {
            char c = bs.read();
            System.out.print(c);
            if (c == '\n' || c == '\r') {
                return new String(buffer, 0, index);
            } else {
                buffer[index++] = c;
            }
        }
        if (index > 0) {
            return new String(buffer, 0, index);
        } else {
            return null;
        }
    }
}
