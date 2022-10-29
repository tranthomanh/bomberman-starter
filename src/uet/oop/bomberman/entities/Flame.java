package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class Flame extends Entity{
    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, 0);
        x = xUnit;
        y = yUnit;
    }

    @Override
    public void update(BombermanGame gp) {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
}
