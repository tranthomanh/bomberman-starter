package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.items.Item;
import uet.oop.bomberman.items.Speed;
import uet.oop.bomberman.items.WallPass;

import java.awt.*;

public class Bomber extends Entity {
    public Bomber(int x, int y, Image img, int speed, BombermanGame pane) {
        super( x, y, img, speed, pane);
        solidArea = new Rectangle(4,0,12, Sprite.SCALED_SIZE - 8);
    }
    public boolean check(Entity entity1, Entity entity2){
        int x1 = entity1.getX();
        int y1 = entity1.getY();
        int x2 = entity2.getX();
        int y2 = entity2.getY();
        if(x1 <= x2 && x2 <= x1 + 32 && y1 <= y2 && y2 <= y1 + 32)return true;
        if(x1 <= x2+32 && x2+32 <= x1 + 32 && y1 <= y2 && y2 <= y1 + 32)return true;
        if(x1 <= x2 && x2 <= x1 + 32 && y1 <= y2+32 && y2+32 <= y1 + 32)return true;
        if(x1 <= x2+32 && x2+32 <= x1 + 32 && y1 <= y2+32 && y2+32 <= y1 + 32)return true;
        return false;

    }
    public boolean check(Bom bomb, int x, int y){
        int posX = bomb.getX();
        int posY = bomb.getY();
        int x1 = posX - Sprite.SCALED_SIZE;
        int x2 = posX + Sprite.SCALED_SIZE;
        int x3 = posX + 2 * Sprite.SCALED_SIZE;
        int y1 = posY - Sprite.SCALED_SIZE;
        int y2 = posY + Sprite.SCALED_SIZE;
        int y3 = posY + 2 * Sprite.SCALED_SIZE;
        if(posX < x && x < x2 && y1 < y && y < y3) {
            return true;
        }
        if(x1 < x && x < x3 && posY < y && y < y2) {
            return true;
        }
        return false;
    }
    public void checkItems(int x, int y){
        if(pane.items[x][y] == null)return;
        Item cur = pane.items[x][y];
        if(cur.isRender == false)return;
        if(cur instanceof Speed){
            speed += 4;
        }
        else if(cur instanceof WallPass){
            pane.isWallPass = true;
        }
        else {
            pane.isWin = true;
        }
        pane.items[x][y] = null;
        pane.itemsList.remove(cur);
    }
    @Override
    public void update(BombermanGame gp) {
        int row = y/32;
        int col = x/32;
        int row2 = (y+31)/32;
        int col2 = x/32;
        int row3 = y/32;
        int col3 = (x + 32)/32;
        int row4 = row2;
        int col4 = col3;
        checkItems(row, col);
        //checkItems(row2, col2);
        //checkItems(row3, col3);
        //checkItems(row4, col4);
        if(isLive == false){
            if(countDead > 0){
                countDead --;
                if(countDead > 60)setImage(Sprite.player_dead1.getFxImage());
                else if(countDead > 30)setImage(Sprite.player_dead2.getFxImage());
                else setImage(Sprite.player_dead3.getFxImage());
            }
            return;
        }

        if(gp.bom.isLive && gp.bom.BomCount < 60){
            if(check(gp.bom, x, y) || check(gp.bom, x + 32, y) || check(gp.bom, x, y + 32) || check(gp.bom, x+32, y+32)){
                isLive = false;
                countDead = 90;
            }
        }
        for(Entity g : gp.dollList){
            if(check(this, g) && g.isLive){
                isLive = false;
                countDead = 90;
                return;
            }
        };
        for(Entity g : gp.minvoList){
            if(check(this, g) && g.isLive){
                isLive = false;
                countDead = 90;
                return;
            }
        };
    }

    @Override
    public void render(GraphicsContext gc) {
        if(isLive == false && countDead == 0)return;
        gc.drawImage(img, x, y);
    }
}
