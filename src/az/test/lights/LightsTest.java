package az.test.lights;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LightsTest extends Frame {

    public LightsTest() {
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        add(new LightsPanel(4, 3, new EllipseLight.Factory(Color.LIGHT_GRAY)), BorderLayout.CENTER);

        setSize(300,300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LightsTest();
    }
}
