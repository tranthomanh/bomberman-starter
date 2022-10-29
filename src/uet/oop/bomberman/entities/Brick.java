package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class Brick extends Entity{

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, 0);
        type = 7;
    }

    @Override
    public void update(BombermanGame gp) {

    }
}
