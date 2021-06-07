package az.test.string;

import java.util.HashMap;
import java.util.Map;

public class PropertiesReaderTest {
    public static void main(String[] args) {
        new PropertiesReaderTest().run();
    }

    private void run() {
        ByteSource bs = new ByteSource();
        Property p;
        while ((p = readProperty(bs)) != null) {
            if (p.key != null) {
                System.out.println("aquired: '" + p.key + "' = '" + p.value + "'");
            }
        }
    }

    private Property readProperty(ByteSource bs) {
        Indexes i = new Indexes();

        Property result = new Property();
        char[] buffer = new char[1024];
        boolean keyFound = false;

        while(bs.available()) {
            char c = bs.read();
            if (c == '\n' || c == '\r') {
                if (keyFound) {
                    result.value = getString(buffer, i);
                }
                return result;
            } else if (c == '=') {
                result.key = getString(buffer, i);
                keyFound = true;
                i.reset();
            } else {
                if (!Character.isWhitespace(c)) {
                    if (i.headIndex < 0) {
                        i.headIndex = i.index;
                    }
                    i.tailIndex = i.index;
                }
                buffer[i.index++] = c;
            }
        }
        if (i.index > 0) {
            if (keyFound) {
                result.value = getString(buffer, i);
            }
            return result;
        } else {
            return null;
        }
    }

    private String getString(char[] buffer, Indexes i) {
        if (i.headIndex < 0) {
            return null;
        } else {
            return new String(buffer, i.headIndex, i.tailIndex);
        }
    }

    private class Property {
        String key;
        String value;
    }
    private class Indexes {
        int index, headIndex = -1, tailIndex;
        void reset() {
            index = tailIndex = 0;
            headIndex = -1;
        }
    }
}
