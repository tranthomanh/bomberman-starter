package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class Wall extends Entity {

    public Wall(int x, int y, Image img, BombermanGame pane) {
        super(x, y, img, 0, pane);
        type = 6;
    }

    @Override
    public void update(BombermanGame gp) {

    }
}
