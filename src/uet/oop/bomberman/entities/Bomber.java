package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    public Bomber(int x, int y, Image img, int speed) {
        super( x, y, img, speed);
        up = down = left = right = false;
    }
    public Bomber() {

    }

    @Override
    public void update() {

    }
}
