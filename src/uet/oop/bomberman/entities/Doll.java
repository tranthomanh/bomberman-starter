package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Doll extends Entity{
    public Doll(int xUnit, int yUnit, Image img, int speed) {
        super(xUnit, yUnit, img, speed);
        type = 1;
    }

    @Override
    public void update() {

    }
}
