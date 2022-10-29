package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Bomber extends Entity {
    public Bomber(int x, int y, Image img, int speed) {
        super( x, y, img, speed);
        solidArea = new Rectangle(4,0,12, Sprite.SCALED_SIZE - 8);
    }
    public Bomber() {
        solidArea = new Rectangle(4,0,12, Sprite.SCALED_SIZE - 8);
    }
    @Override
    public void update(BombermanGame gp) {
        if(isLive == false){
            if(countDead > 0){
                if(countDead > 60)setImage(Sprite.player_dead1.getFxImage());
                else if(countDead > 30)setImage(Sprite.player_dead2.getFxImage());
                else setImage(Sprite.player_dead3.getFxImage());
                countDead --;
            }
        }
        if(gp.bom.isLive && gp.bom.BomCount < 60){
            int posX = gp.bom.getX();
            int posY = gp.bom.getY();
            int x1 = posX - Sprite.SCALED_SIZE;
            int x2 = posX + Sprite.SCALED_SIZE;
            int x3 = posX + 2 * Sprite.SCALED_SIZE;
            int y1 = posY - Sprite.SCALED_SIZE;
            int y2 = posY + Sprite.SCALED_SIZE;
            int y3 = posY + 2 * Sprite.SCALED_SIZE;
            if(posX <= x && x <= x2 && y1 <= y && y <= y3) {
                isLive = false;
                countDead = 90;
            }
            if(x1 <= x && x <= x3 && posY <= y && y <= y2) {
                isLive = false;
                countDead = 90;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if(isLive == false && countDead == 0)return;
        gc.drawImage(img, x, y);
    }
}
