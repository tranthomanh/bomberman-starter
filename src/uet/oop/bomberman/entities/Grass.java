package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Grass extends Entity {

    public Grass(int x, int y, Image img) {
        super(x, y, img, 0);
        type = 5;
    }

    @Override
    public void update() {

    }
}
