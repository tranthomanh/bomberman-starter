package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Bomber extends Entity {
    public Bomber(int x, int y, Image img, int speed) {
        super( x, y, img, speed);
        solidArea = new Rectangle(4,0,12, Sprite.SCALED_SIZE - 8);
    }
    public Bomber() {
        solidArea = new Rectangle(4,0,12, Sprite.SCALED_SIZE - 8);
    }

    @Override
    public void update() {

    }
}
