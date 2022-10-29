package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Doll extends Entity{
    int counter = 0;
    int type = 0;
    public Doll(int xUnit, int yUnit, Image img, int speed) {
        super(xUnit, yUnit, img, speed);
        solidArea = new Rectangle(0,  0, Sprite.SCALED_SIZE - 1, Sprite.SCALED_SIZE - 1);
        type = 1;
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
    @Override
    public void update(BombermanGame gp) {
        if(gp.bom.isLive && gp.bom.BomCount < 60){
            if(check(gp.bom, x, y) || check(gp.bom, x + 32, y) || check(gp.bom, x, y + 32) || check(gp.bom, x+32, y+32)){
                isLive = false;
                countDead = 90;
            }
        }
        if(isLive == false){
            if(countDead > 0){
                setImage(Sprite.balloom_dead.getFxImage());
                countDead --;
            }
            else return;
        }
        if(direction == null)direction = "up";
        spriteCounter++;
        if(spriteCounter == 20){
            //System.out.println(getX() + " " + getY());
            spriteCounter = 0;
            counter+=add;
            if(counter == 5)add = -1;
            else if(counter == 0)add = 1;
            collision = false;
            gp.collisionChecker.checkTile(this);
            boolean ok = true;
            if(collision){
                boolean[] b = new boolean[4];
                 b[0] = gp.collisionChecker.canMoveUp(this);
                 b[1] = gp.collisionChecker.canMoveDown(this);
                 b[2] = gp.collisionChecker.canMoveLeft(this);
                 b[3] = gp.collisionChecker.canMoveRight(this);
                ArrayList<Integer> arrayList = new ArrayList<>();
                 for(int i=0; i<4; i++)if(b[i])arrayList.add(i);
                 if(arrayList.size() == 0){
                     ok = false;
                 }
                 else{
                     int num = arrayList.size();
                     int cur = (int)(Math.random() * num);
                     switch (arrayList.get(cur)){
                         case 0:
                             direction = "up";
                             break;
                         case 1:
                             direction = "down";
                             break;
                         case 2:
                             direction = "left";
                             break;
                         case 3:
                             direction = "right";
                             break;
                     }
                 }
            }
            //System.out.println(ok);
            if(ok == false)return;
            switch (direction){
                case "up":
                    setY(-speed);
                    break;
                case "down":
                    setY(speed);
                    break;
                case "left":
                    setX(-speed);
                    break;
                case "right":
                    setX(speed);
                    break;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if(isLive == false)return;
        if(counter == 40){
            counter = 0;
            type = 1 - type;
        }
        counter++;
        if(type == 0){
            Sprite sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, counter , 40);
            gc.drawImage(sprite.getFxImage(), x, y);
        }
        else{
            Sprite sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, counter , 40);
            gc.drawImage(sprite.getFxImage(), x, y);
        }
    }
}
