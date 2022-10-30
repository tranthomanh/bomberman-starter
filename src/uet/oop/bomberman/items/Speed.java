package uet.oop.bomberman.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Speed extends Item{
    public Speed(int x, int y){
        super(Sprite.powerup_speed.getFxImage(), x, y);
    }
}
