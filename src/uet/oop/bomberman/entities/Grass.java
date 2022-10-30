package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Grass extends Entity {
    public Grass(int x, int y, Image img, BombermanGame pane) {
        super(x, y, img, 0, pane);
        type = 5;
    }

    @Override
    public void render(GraphicsContext gc) {
        if(miss > 0){
            if(miss >= 40)gc.drawImage(Sprite.brick_exploded.getFxImage(), x, y);
            else if(miss >= 20)gc.drawImage(Sprite.brick_exploded1.getFxImage(), x, y);
            else gc.drawImage(Sprite.brick_exploded2.getFxImage(), x, y);
            miss--;
        }else{
            int row = y/Sprite.SCALED_SIZE;
            int col = x/Sprite.SCALED_SIZE;
            if(pane.items[row][col] != null) {
                System.out.println(row + " " + col);
                pane.items[row][col].isRender = true;
            }
            else gc.drawImage(img,x,y);
        }
    }

    @Override
    public void update(BombermanGame gp) {

    }
}
