package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Minvo extends Entity{
    public Minvo(int xUnit, int yUnit, Image img, int speed) {
        super(xUnit, yUnit, img, speed);
        type = 2;
    }

    @Override
    public void update() {

    }
}
