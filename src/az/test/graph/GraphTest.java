package az.test.graph;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphTest extends Frame {

    public GraphTest() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Goodbye");
                System.exit(0);
            }
        });

        System.out.println("Hello");
        GraphPanel graphPanel = new GraphPanel();
        add(graphPanel);
        setSize(300,200);
        graphPanel.setBuffer(createBuffer());
        setVisible(true);
    }

    private double[] createBuffer() {
        final int size = 1000;
        double[] result = new double[size];
        for (int i=0; i<size; i++) {
            result[i] = Math.random();
        }
        return result;
    }

    public static void main(String[] args) {
        new GraphTest();
    }
}
