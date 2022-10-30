package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Portal extends Item{
    public Portal(int x, int y) {
        super(Sprite.portal.getFxImage(), x, y);

    }

}
