package uet.oop.bomberman.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class WallPass extends Item{
    public WallPass(int x, int y){
        super(Sprite.powerup_wallpass.getFxImage(), x, y);
    }
}
