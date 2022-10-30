package uet.oop.bomberman.items;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Item {
    protected Image img;
    protected int x, y;
    public boolean isRender = false;

    public Item(Image img, int x, int y) {
        this.img = img;
        this.x = x * Sprite.SCALED_SIZE;
        this.y = y * Sprite.SCALED_SIZE;
    }
    public Item(){

    }
    public void render(GraphicsContext gc){
        if(isRender){
            gc.drawImage(img, x, y);
            System.out.println(x + " " + y);
        }
    }
}
