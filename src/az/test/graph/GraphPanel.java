package az.test.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GraphPanel extends Panel {
    Image image;
    double[] buffer;

    public GraphPanel() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                sizeChanged(getWidth(), getHeight());
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(image,0, 0,this);
    }

    public void sizeChanged(int width, int height) {
        image = createImage(width, height);
        redraw();
    }

    public void redraw() {
        if (image == null) {
            return;
        }

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

    }

    public void setBuffer(double[] buffer) {
        this.buffer = buffer;
        redraw();
    }
}
