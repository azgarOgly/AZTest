package az.test.lights;

import java.awt.Color;
import java.awt.Graphics;

public class EllipseLight implements Light {
    private final Color color;

    public EllipseLight(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void paint(Graphics g, int x, int y, int width, int height) {
        g.setColor(color);
        g.fillOval(x+width/4, y+height/4, width/2, height/2);
    }

    public static class Factory implements LightFactory {
        private final Color color;

        public Factory(Color color) {
            this.color = color;
        }

        @Override
        public Light create() {
            return new EllipseLight(new Color(color.getRGB()));
        }
    }
}
