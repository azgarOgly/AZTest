package az.test.string;

public class ByteSource {

    private static final String CONTENT = "hello!\nGoodbye\n\r key = value\n = another\u0000string\r\nfoobar";
    char[] buffer = CONTENT.toCharArray();
    int index = 0;

    public char read() {
        return buffer[index++];
    }
    public boolean available() {
        return index < buffer.length;
    }
}
