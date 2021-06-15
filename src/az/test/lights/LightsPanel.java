package az.test.lights;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LightsPanel extends Panel {
    private final int rows, columns;
    private final Light.LightFactory lightFactory;
    private Light[][] lights;
    Image buffer;

    public LightsPanel(int columns, int rows, Light.LightFactory lightFactory) {
        this.rows = rows;
        this.columns = columns;
        this.lightFactory = lightFactory;

        lights = new Light[columns][rows];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                lights[x][y] = lightFactory.create();
            }
        }

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                resetBuffer();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(buffer, 0, 0, this);
    }

    private void resetBuffer() {
        buffer = createImage(getSize().width, getSize().height);
        repaintImage();
    }
    private void repaintImage() {
        Graphics graphics = buffer.getGraphics();
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, getSize().width, getSize().height);
        int stepX = getSize().width/columns;
        int stepY = getSize().height/rows;
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                lights[x][y].paint(graphics, stepX*x, stepY*y, stepX, stepY);
            }
        }
    }
}
