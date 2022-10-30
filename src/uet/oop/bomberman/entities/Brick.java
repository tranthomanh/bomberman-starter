package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;

public class Brick extends Entity{

    public Brick(int xUnit, int yUnit, Image img, BombermanGame pane) {
        super(xUnit, yUnit, img, 0, pane);
        type = 7;
        if(xUnit == 2 && yUnit == 2) System.out.println(pane.items[2][2]);
    }

    @Override
    public void update(BombermanGame gp) {

    }
}
