package az.test.lights;

import java.awt.Color;
import java.awt.Graphics;

public class SquareLight implements Light {
    private final Color color;

    public SquareLight(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void paint(Graphics g, int x, int y, int width, int height) {
        g.setColor(color);
        g.fillRect(x+width/8, y+height/8, width/4*3, height/4*3);
    }

    public static class Factory implements Light.LightFactory {
        private final Color color;

        public Factory(Color color) {
            this.color = color;
        }

        @Override
        public Light create() {
            return new SquareLight(new Color(color.getRGB()));
        }
    }
}
