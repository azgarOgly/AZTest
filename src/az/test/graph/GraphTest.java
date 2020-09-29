package az.test.graph;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphTest extends Frame {

    private final ConsoleFrame console = new ConsoleFrame();
    private final GraphPanel graphPanel = new GraphPanel();
    private final Scrollbar horizontalScrollbar = new Scrollbar(Scrollbar.HORIZONTAL) {
        @Override
        public void setVisibleAmount(int newAmount) {
            super.setVisibleAmount(newAmount);
            setBlockIncrement(newAmount/2);
        }
    };

    public GraphTest() {
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        GraphPanel.Toolbar toolbar = graphPanel.createToolbar();
        add(toolbar, BorderLayout.NORTH);
        add(graphPanel, BorderLayout.CENTER);
        add(horizontalScrollbar, BorderLayout.SOUTH);

        toolbar.setButtonPlusListener(e -> {
            int visibleAmount = horizontalScrollbar.getVisibleAmount();
            horizontalScrollbar.setVisibleAmount(visibleAmount/2);
            horizontalScrollbar.setValue(horizontalScrollbar.getValue() + visibleAmount/4);
            adjustGraphPanelExtents();
        });
        toolbar.setButtonMinusListener(e -> {
            int visibleAmount = horizontalScrollbar.getVisibleAmount();
            int totalAmount = horizontalScrollbar.getMaximum();
            horizontalScrollbar.setVisibleAmount(Math.min(visibleAmount*2, totalAmount));
            horizontalScrollbar.setValue(Math.max(0, horizontalScrollbar.getValue()-visibleAmount/2));
            adjustGraphPanelExtents();
        });
        horizontalScrollbar.addAdjustmentListener(e -> adjustGraphPanelExtents());

        addMouseWheelListener(e -> console.println(e.toString()));
        graphPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                console.println(e.toString());
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                console.println(e.toString());
            }
        });

        setSize(300,200);
        double[] data = createData();
        horizontalScrollbar.setMaximum(data.length);
        horizontalScrollbar.setVisibleAmount(data.length);
        setVisible(true);

        graphPanel.setData(data);
    }

    private double[] createData() {
        final int size = 1000;
        double[] result = new double[size];
        for (int i=0; i<size; i++) {
            result[i] = Math.random();
        }
        return result;
    }

    private void adjustGraphPanelExtents() {
        int value = horizontalScrollbar.getValue();
        int visibleAmount = horizontalScrollbar.getVisibleAmount();
        graphPanel.setExtents(value, value+visibleAmount);
    }

    public static void main(String[] args) {
        new GraphTest();
    }
}
