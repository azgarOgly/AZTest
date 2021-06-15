package az.test.lights;

import java.awt.Color;
import java.awt.Graphics;

public interface Light {
    Color getColor();
    void paint(Graphics g, int x, int y, int width, int height);

    public static interface LightFactory {
        Light create();
    }
}
