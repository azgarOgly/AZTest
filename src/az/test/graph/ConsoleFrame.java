package az.test.graph;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.TextArea;

public class ConsoleFrame extends Frame {

    private final TextArea console = new TextArea();

    public ConsoleFrame() {
        setLayout(new BorderLayout());
        add(console, BorderLayout.CENTER);
        setSize(300, 400);
        setVisible(true);
    }

    public void println(String msg) {
        console.setText(console.getText() + "\n" + msg);
    }
}
