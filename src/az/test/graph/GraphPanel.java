package az.test.graph;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GraphPanel extends Panel {
    Image image;
    double[] buffer;
    double[] data;

    public GraphPanel() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                redraw();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image,0, 0,this);
    }

    public void redraw() {
        image = createImage(getWidth(), getHeight());

        Graphics g = image.getGraphics();
        g.setColor(Color.BLACK);

        if (buffer == null) {
            g.setColor(Color.BLACK);
            g.drawLine(0, getHeight(), getWidth(), 0);
            return;
        }

        int x = 0;
        int y = (int)(buffer[0]*getHeight());
        double step = (double)getWidth()/buffer.length;
        for(int i=0; i<buffer.length; i++) {
            int nx = (int) (i*step);
            int ny = (int)(buffer[i]*getHeight());
            g.drawLine(x, y, nx, ny);
            x = nx; y = ny;
        }
        repaint(50);
    }

    public void setData(double[] data) {
        this.buffer = data;
        this.data = data;
        redraw();
    }

    public void setExtents(int start, int end) {
        this.buffer = new double[end-start];
        System.arraycopy(data, start, buffer, 0, end-start);
        redraw();
    }

    public Toolbar createToolbar() {
        return new Toolbar();
    }

    public static class Toolbar extends Panel {
        Button buttonPlus = new Button("+");
        Button buttonMinus = new Button("-");

        public Toolbar() {
            add(buttonPlus);
            add(buttonMinus);
        }

        public void setButtonPlusListener(ActionListener l) {
            buttonPlus.addActionListener(l);
        }
        public void setButtonMinusListener(ActionListener l) {
            buttonMinus.addActionListener(l);
        }
    }
}
